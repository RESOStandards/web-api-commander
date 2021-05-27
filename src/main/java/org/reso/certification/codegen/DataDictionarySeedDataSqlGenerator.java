package org.reso.certification.codegen;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.commander.common.Utils;
import org.reso.models.DataGenerator;
import org.reso.models.ReferenceStandardField;
import org.reso.models.ReferenceStandardLookup;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_DATA_TYPES.*;

/**
 * From: https://mariadb.com/kb/en/how-to-quickly-insert-data-into-mariadb/
 *
 * ALTER TABLE table_name DISABLE KEYS;
 * BEGIN;
 * ... inserting data with INSERT or LOAD DATA ....
 * COMMIT;
 * ALTER TABLE table_name ENABLE KEYS;
 *
 * SET @@session.unique_checks = 0;
 * SET @@session.foreign_key_checks = 0;
 *
 * SET @@global.innodb_autoinc_lock_mode = 2;
 *
 * Then use this to import the data:
 *
 * mysqlimport --use-threads=<numThreads> database text-file-name [text-file-name...]
 */
public class DataDictionarySeedDataSqlGenerator {
  private static final Logger LOG = LogManager.getLogger(DataDictionarySeedDataSqlGenerator.class);
  private DDCacheProcessor processor;

  /**
   * Cache of fields and their data generators by resource
   */
  private final static AtomicReference<Map<String, Map<String, DataGenerator.FieldDataGenerator>>> dataGeneratorResourceFieldMap
      = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  /**
   * Cache of standard fields from the current Data Dictionary worksheet
   */
  private final static AtomicReference<Map<String, List<ReferenceStandardField>>> referenceStandardFieldCache
      = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  /**
   * Cache of keys by resource name
   */
  private final static AtomicReference<Map<String, String>> keyCache
      = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));


  /**
   * TODO: add a standard relationships cache so keys can be sampled from the keyCache for related records
   */



  public DataDictionarySeedDataSqlGenerator() {
    LOG.info("Creating standard field cache...");
    DDCacheProcessor processor = new DDCacheProcessor();
    DataDictionaryCodeGenerator generator = new DataDictionaryCodeGenerator(processor);
    generator.processWorksheets();
    LOG.info("Standard field cache created!");

    this.processor = processor;

    //build a cache of the Dictionary standard fields
    referenceStandardFieldCache.set(processor.getFieldCache());

    //build a cache of Data Dictionary generators
    DataGenerator dataGenerator = DataGenerator.deserialize();
    dataGenerator.getResourceInfo().forEach(resourceInfo -> {
      dataGeneratorResourceFieldMap.get().putIfAbsent(resourceInfo.getResourceName(), new LinkedHashMap<>());
      dataGenerator.getFields().forEach(fieldDataGenerator ->
          dataGeneratorResourceFieldMap.get().get(resourceInfo.getResourceName()).put(fieldDataGenerator.getFieldName(), fieldDataGenerator));
    });

    //extract counts for each resource
    final Map<String, Integer> resourceCounts = dataGenerator.getResourceInfo().stream()
        .collect(Collectors.toMap(DataGenerator.ResourceInfo::getResourceName, DataGenerator.ResourceInfo::getRecordCount));

    //iterate over each resource in the Data Dictionary and generate n items from it, where n is the recordCount
    //in the resourceInfo section of the data generator reference file
    referenceStandardFieldCache.get().keySet().forEach(resourceName -> {
      LOG.info("Processing " + resourceName + " resource...");
      LOG.info(generateRowInsertStatements(resourceName, referenceStandardFieldCache.get().get(resourceName), resourceCounts.get(resourceName)));
    });
  }

  /**
   * INSERT INTO tbl_name (a,b,c)
   * VALUES(1,2,3), (4,5,6), (7,8,9);
   *
   * TODO: this function needs to have the lookups split out and handled in their own insert statement generator
   *
   * @param resourceName
   * @param referenceStandardFields
   * @param numStatements
   * @return
   */
  final String generateRowInsertStatements(String resourceName, List<ReferenceStandardField> referenceStandardFields, Integer numStatements) {
    final String tableName = DDLProcessor.buildDbTableName(resourceName);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ALTER TABLE ").append(tableName).append(" DISABLE KEYS;\n");
    stringBuilder.append("BEGIN;\n");
    stringBuilder.append("INSERT INTO ").append(tableName);
    stringBuilder.append(" (");
    stringBuilder.append(referenceStandardFields.stream().map(ReferenceStandardField::getStandardName)
        .collect(Collectors.joining(", ")));
    stringBuilder.append(") VALUES");

    for (int statementCount = 0; statementCount < numStatements; statementCount++) {
      stringBuilder.append("\n\t(");
      stringBuilder.append(referenceStandardFields.stream().map(this::generateValues).collect(Collectors.joining(", ")));
      stringBuilder.append(")");

      //add commas between values only if we're not at the last item
      if (statementCount < numStatements - 1) stringBuilder.append(", ");
    }

    stringBuilder.append(";\n");
    stringBuilder.append("COMMIT;\n");
    stringBuilder.append("ALTER TABLE " + tableName + " ENABLE KEYS;\n\n");

    return stringBuilder.toString();
  }

  final String generateValues(ReferenceStandardField referenceStandardField) {
    //now that row has been processed, extract field type and assemble the template
    switch (referenceStandardField.getSimpleDataType()) {
      case NUMBER:
        return generateNumber(referenceStandardField);
      case STRING_LIST_SINGLE:
        return generateStringListSingle(referenceStandardField);
      case STRING:
        return generateString(referenceStandardField);
      case BOOLEAN:
        return generateBoolean(referenceStandardField);
      case STRING_LIST_MULTI:
        return generateStringListMulti(referenceStandardField).toString();
      case DATE:
        return generateDate(referenceStandardField);
      case TIMESTAMP:
        return generateTimestamp(referenceStandardField);
      default:
        if (referenceStandardField.getSimpleDataType() != null)
          LOG.debug("Data type: " + referenceStandardField.getSimpleDataType() + " is not supported!");

    }
    return null;
  }

  String generateNumber(ReferenceStandardField referenceStandardField) {
    return referenceStandardField.getSuggestedMaxPrecision() != null
        ? generateDecimal(referenceStandardField) : generateInteger(referenceStandardField);
  }

  String generateInteger(ReferenceStandardField referenceStandardField) {
    final int MAX_INTEGER_POWER = 5;
    int maxPower = Math.min(referenceStandardField.getSuggestedMaxLength(), MAX_INTEGER_POWER);
    return String.valueOf(Faker.instance().number().numberBetween(0, (int)Math.pow(10, maxPower)));
  }

  String generateDecimal(ReferenceStandardField referenceStandardField) {
    final int MAX_INTEGER_POWER = 6;
    int maxPower = Math.min(referenceStandardField.getSuggestedMaxLength(), MAX_INTEGER_POWER);
    return String.valueOf(Faker.instance().number()
        .randomDouble(referenceStandardField.getSuggestedMaxPrecision(), 0, (int)Math.pow(10, maxPower)));
  }

  String generateBoolean(ReferenceStandardField referenceStandardField) {
    return String.valueOf(new Random().nextBoolean()).toUpperCase();
  }

  String generateStringListSingle(ReferenceStandardField referenceStandardField) {
    List<String> possibleChoices;
    List<String> customExamples = dataGeneratorResourceFieldMap.get().get(referenceStandardField.getParentResourceName()).get(referenceStandardField.getStandardName()) != null
        ? dataGeneratorResourceFieldMap.get().get(referenceStandardField.getParentResourceName()).get(referenceStandardField.getStandardName()).getCustomExamples() : null;
    int numElements;

    if (processor.getEnumerations().containsKey(referenceStandardField.getLookupStandardName())) {
      possibleChoices = processor.getEnumerations().get(referenceStandardField.getLookupStandardName()).stream()
          .map(ReferenceStandardLookup::getLookupValue).collect(Collectors.toList());
    } else if (customExamples != null && customExamples.size() > 0) {
      possibleChoices = customExamples;
    } else {
      possibleChoices = new ArrayList<>();
      possibleChoices.add(Faker.instance().chuckNorris().fact());
    }

    Collections.shuffle(possibleChoices);
    return wrapInQuotes(possibleChoices.get(0));
  }

  static String wrapInQuotes(String item) {
    return "\"" + item + "\"";
  }

  List<String> generateStringListMulti(ReferenceStandardField referenceStandardField) {
    List<String> possibleChoices;
    List<String> customExamples = dataGeneratorResourceFieldMap.get().get(referenceStandardField.getParentResourceName()).get(referenceStandardField.getStandardName()) != null
        ? dataGeneratorResourceFieldMap.get().get(referenceStandardField.getParentResourceName()).get(referenceStandardField.getStandardName()).getCustomExamples() : null;
    int numElements, randomSize = 0;
    Set<String> enumNames = new LinkedHashSet<>();

    if (processor.getEnumerations().containsKey(referenceStandardField.getLookupStandardName())) {
      numElements = processor.getEnumerations().get(referenceStandardField.getLookupStandardName()).size();
      randomSize = ThreadLocalRandom.current().nextInt(0, numElements);
      possibleChoices = processor.getEnumerations().get(referenceStandardField.getLookupStandardName()).stream()
          .map(ReferenceStandardLookup::getLookupValue).collect(Collectors.toList());
    } else if (customExamples != null && customExamples.size() > 0) {
      randomSize = ThreadLocalRandom.current().nextInt(customExamples.size());
      possibleChoices = customExamples;
    } else {
      possibleChoices = new ArrayList<>();
      possibleChoices.add(Faker.instance().buffy().quotes());
    }

    new LinkedHashSet<>(randomSize);

    for(int numEnums = 0; numEnums < randomSize; numEnums++) {
      Collections.shuffle(possibleChoices);
      if (possibleChoices.size() > 0) {
        enumNames.add(wrapInQuotes(possibleChoices.get(0)));
        possibleChoices.remove(0);
      }
    }
    return new ArrayList<>(enumNames);
  }

  /**
   * TODO: determine whether we need to be able to go both ways on dates on demand.
   * For example, it might make sense to have open house dates in the future.
   * This method currently only generates past dates.
   * @param referenceStandardField
   * @return
   */
  String generateDate(ReferenceStandardField referenceStandardField) {
    long numDays = new Random().nextInt(5 * 365); //max 5 years back
    return wrapInQuotes(Utils.getIsoDate(OffsetDateTime.now().minus(numDays, ChronoUnit.DAYS)));
  }

  /**
   * The only time a string will be generated will be when there is a custom example
   * @param referenceStandardField
   * @return
   */
  String generateString(ReferenceStandardField referenceStandardField) {
    List<String> customExamples = dataGeneratorResourceFieldMap.get().get(referenceStandardField.getParentResourceName()).get(referenceStandardField.getStandardName()) != null
        ? dataGeneratorResourceFieldMap.get().get(referenceStandardField.getParentResourceName()).get(referenceStandardField.getStandardName()).getCustomExamples() : null;

    String value;

    if (customExamples != null && customExamples.size() > 0) {
      value = customExamples.get(new Random().nextInt(customExamples.size()));
    } else {
      value = Faker.instance().buffy().quotes();
    }

    if (value != null) {
      value = wrapInQuotes(value);
    }

    return value;
  }

  String generateTimestamp(ReferenceStandardField referenceStandardField) {
    long numDays = new Random().nextInt(5 * 365); //max 5 years back
    return wrapInQuotes(Utils.getIsoTimestamp(OffsetDateTime.now().minus(numDays, ChronoUnit.DAYS)));
  }

}


