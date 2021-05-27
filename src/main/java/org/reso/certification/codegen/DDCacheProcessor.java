package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.models.ReferenceStandardField;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DDCacheProcessor extends WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(DDCacheProcessor.class);
  Map<String, List<ReferenceStandardField>> fieldCache = new LinkedHashMap<>();

  private void addToFieldCache(ReferenceStandardField field) {
    fieldCache.putIfAbsent(field.getParentResourceName(), new LinkedList<>());
    fieldCache.get(field.getParentResourceName()).add(field);
  }

  public Map<String, List<ReferenceStandardField>> getFieldCache() {
    return fieldCache;
  }

  public static Map<String, List<ReferenceStandardField>> buildCache() {
    LOG.info("Creating standard field cache...");
    DDCacheProcessor cacheProcessor = new DDCacheProcessor();
    DataDictionaryCodeGenerator generator = new DataDictionaryCodeGenerator(cacheProcessor);
    generator.processWorksheets();
    LOG.info("Standard field cache created!");
    return cacheProcessor.getFieldCache();
  }

  public static DataDictionaryCodeGenerator getGeneratorInstance() {
    DDCacheProcessor cacheProcessor = new DDCacheProcessor();
    return new DataDictionaryCodeGenerator(cacheProcessor);
  }

  @Override
  void processNumber(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processStringListSingle(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processString(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processBoolean(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processStringListMulti(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processDate(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processTimestamp(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processCollection(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void generateOutput() {
    //no output
  }
}
