package org.reso.commander.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.communication.ODataClientErrorException;
import org.apache.olingo.client.api.communication.response.ODataResponse;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.core.edm.primitivetype.EdmDate;
import org.apache.olingo.commons.core.edm.primitivetype.EdmDateTimeOffset;
import org.apache.olingo.commons.core.edm.primitivetype.EdmTimeOfDay;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.Commander;
import org.reso.models.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static io.restassured.path.json.JsonPath.from;
import static org.junit.Assert.*;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.TestUtils.DateParts.FRACTIONAL;

public final class TestUtils {
  public static final String JSON_VALUE_PATH = "value";
  public static final String HEADER_ODATA_VERSION = "OData-Version";
  private static final Logger LOG = LogManager.getLogger(LogManager.getContext());

  /**
   * Used to prepare URIs given that the input queryStrings can sometimes contain special characters
   * that need to be ensured that they be processed.
   *
   * @param queryString the queryString to prepare
   * @return the prepared URI with special characters in the queryString processed.
   */
  public static URI prepareUri(final String queryString) {
    /* replace spaces with %20 */
    return URI.create(
        queryString.replace(" ", "%20")
        /* add other handlers here */
    );
  }

  /**
   * Finds the default entity container for the given configuration.
   *
   * @param xmlMetadata XML Metadata to search through
   * @return the default CSDL Container for the given XMLMetadata
   */
  public static CsdlEntityContainer findDefaultEntityContainer(Edm edm, XMLMetadata xmlMetadata) {
    assertNotNull("Edm Cannot be Null!", edm);
    assertNotNull("XMLMetadata Cannot be Null!", xmlMetadata);
    assertNotNull("ERROR: Could not find default EntityContainer for given service root!", edm.getEntityContainer());

    String entityContainerNamespace = edm.getEntityContainer().getNamespace();
    assertNotNull("ERROR: no default EntityContainer namespace could be found", entityContainerNamespace);

    return xmlMetadata.getSchema(entityContainerNamespace).getEntityContainer();
  }

  /**
   * Gets a list of CsdlProperty items for the given entityTypeName.
   *
   * @param xmlMetadata    the metadata to search.
   * @param entityTypeName the name of the entityType (resource) to search for. MUST be in the default EntityContainer.
   * @return a list of CsdlProperty items for the given entityTypeName
   */
  public static List<CsdlProperty> findEntityTypesForEntityTypeName(Edm edm, XMLMetadata xmlMetadata, String entityTypeName) {
    assertNotNull("ERROR: Edm Cannot be Null!", edm);
    assertNotNull("ERROR: XMLMetadata Cannot be Null!", xmlMetadata);
    assertNotNull("ERROR: entityTypeName cannot be null!", entityTypeName);

    CsdlEntityContainer entityContainer = findDefaultEntityContainer(edm, xmlMetadata);
    assertNotNull("ERROR: could not find a default entity container for the given server!", entityContainer);

    if (entityContainer.getEntitySet(entityTypeName) == null) return null;

    CsdlSchema schemaForType = xmlMetadata.getSchema(entityContainer.getEntitySet(entityTypeName).getTypeFQN().getNamespace());

    return schemaForType != null ? schemaForType.getEntityType(entityTypeName).getProperties() : null;
  }

  /**
   * Gets a list of CsdlProperty items for the given entityTypeName.
   *
   * @param xmlMetadata    the metadata to search.
   * @param entityTypeName the name of the entityType to search for. MUST be in the default EntityContainer.
   * @return a list of CsdlProperty items for the given entityTypeName
   */
  public static List<CsdlNavigationProperty> findNavigationPropertiesForEntityTypeName(Edm edm, XMLMetadata xmlMetadata, String entityTypeName) {
    assertNotNull("ERROR: Edm Cannot be Null!", edm);
    assertNotNull("ERROR: XMLMetadata Cannot be Null!", xmlMetadata);
    assertNotNull("ERROR: entityTypeName cannot be null!", entityTypeName);

    CsdlEntityContainer entityContainer = findDefaultEntityContainer(edm, xmlMetadata);
    assertNotNull("ERROR: could not find a default entity container for the given server!", entityContainer);

    CsdlSchema schemaForType = xmlMetadata.getSchema(entityContainer.getEntitySet(entityTypeName).getTypeFQN().getNamespace());

    assertNotNull("ERROR: could not find type corresponding to given type name: " + entityTypeName, schemaForType);

    return schemaForType.getEntityType(entityTypeName).getNavigationProperties();
  }

  /**
   * Compares each item in the string (JSON) payload with the given field name to the asserted value using op.
   *
   * @param payload       JSON payload to compare
   * @param fieldName     fieldName to compare against
   * @param op            binary comparison operator
   * @param assertedValue asserted value
   * @return true if values in the payload match the assertion, false otherwise.
   */
  public static boolean compareStringPayloadToAssertedValue(String payload, String fieldName, String op, String assertedValue) {
    AtomicBoolean result = new AtomicBoolean(false);
    //iterate over the items and count the number of fields with data to determine whether there are data present
    from(payload).getList(JSON_VALUE_PATH, HashMap.class).forEach(item ->
        result.compareAndSet(result.get(), compare((String) item.get(fieldName), op, assertedValue)));
    return result.get();
  }

  /**
   * Compares each item in the string (JSON) payload with the given field name to the asserted value using op.
   *
   * @param payload       JSON payload to compare
   * @param fieldName     fieldName to compare against
   * @param op            binary comparison operator
   * @param assertedValue asserted value
   * @return true if values in the payload match the assertion, false otherwise.
   */
  public static boolean compareIntegerPayloadToAssertedValue(String payload, String fieldName, String op, Integer assertedValue) {
    AtomicBoolean result = new AtomicBoolean(false);
    //iterate over the items and count the number of fields with data to determine whether there are data present
    from(payload).getList(JSON_VALUE_PATH, HashMap.class).forEach(item ->
        result.compareAndSet(result.get(), compare((Integer) item.get(fieldName), op, assertedValue)));
    return result.get();
  }

  /**
   * Compares each item in the string (JSON) payload with the given field name to the asserted value using op.
   *
   * @param payload       JSON payload to compare
   * @param fieldName     fieldName to compare against
   * @param op            binary comparison operator
   * @param assertedValue asserted value
   * @return true if values in the payload match the assertion, false otherwise.
   */
  public static boolean compareTimestampPayloadToAssertedValue(String payload, String fieldName, String op, String assertedValue) {
    AtomicBoolean result = new AtomicBoolean(false);
    //iterate over the items and count the number of fields with data to determine whether there are data present
    from(payload).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
      try {
        result.compareAndSet(result.get(), compare(
            parseTimestampFromEdmDateTimeOffsetString((String) item.get(fieldName)), op,
            parseTimestampFromEdmDateTimeOffsetString(assertedValue)));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });
    return result.get();
  }

  /**
   * Compares each item in the string (JSON) payload with the given field name to the asserted value using op.
   *
   * @param payload       JSON payload to compare
   * @param fieldName     fieldName to compare against
   * @param op            binary comparison operator
   * @param assertedValue asserted value
   * @return true if values in the payload match the assertion, false otherwise.
   */
  public static boolean compareTimestampPayloadToAssertedDatePartValue(String payload, String datePart, String fieldName, String op, Integer assertedValue) {
    AtomicBoolean result = new AtomicBoolean(false);

    from(payload).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
      try {
        result.compareAndSet(result.get(), compare(getTimestampPart(datePart, item.get(fieldName)), op, assertedValue));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });
    return result.get();
  }

  //And "month" data in Date Field "Parameter_DateField" "eq" "Parameter_MonthValue"

  /**
   * Compares each item in the string (JSON) payload with the given field name to the asserted value using op.
   *
   * @param payload       JSON payload to compare
   * @param fieldName     fieldName to compare against
   * @param op            binary comparison operator
   * @param assertedValue asserted value
   * @return true if values in the payload match the assertion, false otherwise.
   */
  public static boolean compareDatePayloadToAssertedDatePartValue(String payload, String datePart, String fieldName, String op, Integer assertedValue) {
    AtomicBoolean result = new AtomicBoolean(false);

    from(payload).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
      try {
        result.compareAndSet(result.get(), compare(getDatePart(datePart, item.get(fieldName)), op, assertedValue));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });
    return result.get();
  }

  /**
   * Compares each item in the string (JSON) payload with the given field name to the asserted value using op.
   *
   * @param payload       JSON payload to compare
   * @param fieldName     fieldName to compare against
   * @param op            binary comparison operator
   * @param assertedValue asserted value
   * @return true if values in the payload match the assertion, false otherwise.
   */
  public static boolean compareFractionalSecondsPayloadToAssertedValue(String payload, String fieldName, String op, Double assertedValue) {
    final Double CONVERSION_FACTOR = 1000000.0;

    AtomicBoolean result = new AtomicBoolean(false);
    AtomicReference<Integer> timestampPart = new AtomicReference<>(null);
    AtomicReference<Double> fractionalSeconds = new AtomicReference<>(null);

    from(payload).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
      try {
        timestampPart.set(getTimestampPart(FRACTIONAL, item.get(fieldName)));
        if (timestampPart.get() != null) fractionalSeconds.set(timestampPart.get() / CONVERSION_FACTOR);

        result.set(compare(fractionalSeconds.get(), op, assertedValue));

      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });
    return result.get();
  }

  /**
   * Compares each item in the string (JSON) payload with the given field name to the asserted value using op.
   *
   * @param payload       JSON payload to compare
   * @param fieldName     collection-based fieldName to compare against
   * @param op            collection-based operator (any or all)
   * @param assertedValue asserted value
   * @return true if values in the payload match the assertion, false otherwise.
   */
  public static boolean compareCollectionPayloadToAssertedValue(String payload, String fieldName, String op, String assertedValue) {
    AtomicBoolean result = new AtomicBoolean(true);
    final String ANY = "any", ALL = "all";

    //iterate over the items apply the given collection op
    from(payload).getList(JSON_VALUE_PATH, ObjectNode.class).forEach(item -> {
      if (op.contentEquals(ANY)) {
        result.set(result.get() && testAnyOperator(item, fieldName, assertedValue));
      } else if (op.contentEquals(ALL)) {
        result.set(result.get() && testAllOperator(item, fieldName, assertedValue));
      }
    });
    return result.get();
  }

  public static boolean testAnyOperator(ObjectNode item, String fieldName, String assertedValue) {
    List<String> values = new ArrayList<>();
    item.get(fieldName).elements().forEachRemaining(element -> values.add(element.asText()));
    return values.stream().anyMatch(value -> value.contentEquals(assertedValue));
  }

  public static boolean testAllOperator(ObjectNode item, String fieldName, String assertedValue) {
    List<String> values = new ArrayList<>();
    item.get(fieldName).elements().forEachRemaining(element -> values.add(element.asText()));
    return values.stream().allMatch(value -> value.contentEquals(assertedValue));
  }


  /**
   * Returns true if each item in the list is true
   *
   * @param lhs Integer value
   * @param op  an OData binary operator for use for comparisons
   * @param rhs Integer value
   * @return true if lhs op rhs produces true, false otherwise
   */
  public static boolean compare(Integer lhs, String op, Integer rhs) {
    boolean result = false;

    switch (op.toLowerCase()) {
      case Operators.GREATER_THAN:
        result = lhs != null && rhs != null && lhs > rhs;
        break;
      case Operators.GREATER_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs > rhs);
        break;
      case Operators.EQ:
        result = Objects.equals(lhs, rhs);
        break;
      case Operators.NE:
        result = !Objects.equals(lhs, rhs);
        break;
      case Operators.LESS_THAN:
        result = lhs != null && rhs != null && lhs < rhs;
        break;
      case Operators.LESS_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs < rhs);
        break;
      default:
        LOG.debug("Unknown Operator: " + op);
        break;
    }
    LOG.info("Compare: " + lhs + " " + op + " " + rhs + " ==> " + result);
    return result;
  }

  /**
   * Returns true if each item in the list is true
   *
   * @param lhs Integer value
   * @param op  an OData binary operator for use for comparisons
   * @param rhs Integer value
   * @return true if lhs op rhs produces true, false otherwise
   */
  public static boolean compare(String lhs, String op, String rhs) {
    boolean result = false;

    switch (op.toLowerCase()) {
      case Operators.CONTAINS:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs.contains(rhs));
        break;
      case Operators.STARTS_WITH:
        result = lhs != null && rhs != null && lhs.startsWith(rhs);
        break;
      case Operators.ENDS_WITH:
        result = lhs != null && rhs != null && lhs.endsWith(rhs);
        break;
      case Operators.TO_LOWER:
        result = lhs != null && lhs.toLowerCase().contentEquals(rhs);
        break;
      case Operators.TO_UPPER:
        result = lhs != null && lhs.toUpperCase().contentEquals(rhs);
        break;
      default:
        LOG.debug("Unknown Operator: " + op);
        break;
    }
    LOG.info("Compare: \"" + lhs + "\" " + op + " \"" + rhs + "\" ==> " + result);
    return result;
  }

  /**
   * Timestamp Comparator
   *
   * @param lhs Timestamp to compare
   * @param op  an OData binary operator to use for comparisons
   * @param rhs Timestamp to compare
   * @return true if lhs op rhs, false otherwise
   */
  public static boolean compare(Timestamp lhs, String op, Timestamp rhs) {
    boolean result = false;

    switch (op.toLowerCase()) {
      case Operators.GREATER_THAN:
        result = lhs != null && rhs != null && lhs.after(rhs);
        break;
      case Operators.GREATER_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs.after(rhs));
        break;
      case Operators.EQ:
        result = Objects.equals(lhs, rhs);
        break;
      case Operators.NE:
        result = !Objects.equals(lhs, rhs);
        break;
      case Operators.LESS_THAN:
        result = lhs != null && rhs != null && lhs.before(rhs);
        break;
      case Operators.LESS_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs.before(rhs));
        break;
      default:
        LOG.debug("Unknown Operator: " + op);
        break;
    }
    LOG.info("Compare: " + lhs + " " + op + " " + rhs + " ==> " + result);
    return result;
  }

  /**
   * Time Comparator
   *
   * @param lhs Time to compare
   * @param op  an OData binary operator to use for comparisons
   * @param rhs Time to compare
   * @return true if lhs op rhs, false otherwise
   */
  public static boolean compare(Time lhs, String op, Time rhs) {
    boolean result = false;

    switch (op.toLowerCase()) {
      case Operators.GREATER_THAN:
        result = lhs != null && rhs != null && lhs.toLocalTime().isAfter(rhs.toLocalTime());
        break;
      case Operators.GREATER_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || lhs.toLocalTime().isAfter(rhs.toLocalTime()) || lhs.toLocalTime().equals(rhs.toLocalTime());
        break;
      case Operators.EQ:
        result = Objects.equals(lhs, rhs) || lhs.toLocalTime().equals(rhs.toLocalTime());
        break;
      case Operators.NE:
        result = !Objects.equals(lhs, rhs) || !lhs.toLocalTime().equals(rhs.toLocalTime());
        break;
      case Operators.LESS_THAN:
        result = lhs != null && rhs != null && lhs.toLocalTime().isBefore(rhs.toLocalTime());
        break;
      case Operators.LESS_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || lhs.toLocalTime().isBefore(rhs.toLocalTime()) || lhs.toLocalTime().equals(rhs.toLocalTime());
        break;
      default:
        LOG.debug("Unknown Operator: " + op);
        break;
    }
    LOG.info("Compare: " + lhs + " " + op + " " + rhs + " ==> " + result);
    return result;
  }

  /**
   * Date Comparator
   *
   * @param lhs Date to compare
   * @param op  an OData binary operator to use for comparisons
   * @param rhs Date to compare
   * @return true if lhs op rhs, false otherwise
   */
  public static boolean compare(Date lhs, String op, Date rhs) {
    boolean result = false;

    switch (op.toLowerCase()) {
      case Operators.GREATER_THAN:
        result = lhs != null && lhs.after(rhs);
        break;
      case Operators.GREATER_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs.after(rhs));
        break;
      case Operators.EQ:
        result = Objects.equals(lhs, rhs);
        break;
      case Operators.NE:
        result = !Objects.equals(lhs, rhs);
        break;
      case Operators.LESS_THAN:
        result = lhs != null && lhs.before(rhs);
        break;
      case Operators.LESS_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs.before(rhs));
        break;
      default:
        LOG.debug("Unknown Operator: " + op);
        break;
    }
    LOG.info("Compare: " + lhs + " " + op + " " + rhs + " ==> " + result);
    return result;
  }

  /**
   * Compares Double values, taking nulls into account
   *
   * @param lhs the left value
   * @param op  a binary comparison operator
   * @param rhs the right value
   * @return true if lhs 'op' rhs is true, false otherwise
   */
  public static boolean compare(Double lhs, String op, Double rhs) {
    boolean result = false;

    switch (op.toLowerCase()) {
      case Operators.GREATER_THAN:
        //TODO: consider switching to compare()
        result = lhs != null && rhs != null && lhs > rhs;
        break;
      case Operators.GREATER_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs > rhs);
        break;
      case Operators.EQ:
        result = Objects.equals(lhs, rhs);
        break;
      case Operators.NE:
        result = !Objects.equals(lhs, rhs);
        break;
      case Operators.LESS_THAN:
        result = lhs != null && rhs != null && lhs < rhs;
        break;
      case Operators.LESS_THAN_OR_EQUAL:
        result = Objects.equals(lhs, rhs) || (lhs != null && rhs != null && lhs < rhs);
        break;
      default:
        LOG.debug("Unknown Operator: " + op);
        break;
    }
    LOG.info("Compare: " + lhs + " " + op + " " + rhs + " ==> " + result);
    return result;
  }

  /**
   * Tests the given string to see if it's valid JSON
   *
   * @param jsonString the JSON string to test the validity of
   * @return true if valid, false otherwise. Throws {@link IOException}
   */
  public static boolean isValidJson(String jsonString) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.readTree(jsonString);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Helper method to find headers with a given key in an an array of headers
   *
   * @param key     the header to get
   * @param headers an array containing Header objects
   * @return the value of the header with key, or null
   */
  public static String getHeaderData(String key, Collection<Header> headers) {
    String data = null;

    for (Header header : headers) {
      if (header.getName().toLowerCase().contains(key.toLowerCase())) {
        data = header.getValue();
      }
    }
    return data;
  }

  /**
   * Helper method to unpack headers from a raw OData response
   *
   * @param key           the header to get
   * @param oDataResponse the OData raw response from the request
   * @return the value of the header with key, or null
   */
  public static String getHeaderData(String key, ODataResponse oDataResponse) {
    if (key == null || oDataResponse.getHeader(key) == null) return null;
    ArrayList<String> result = new ArrayList<>(oDataResponse.getHeader(key));

    if (result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }

  }

  /**
   * Parses the given edmDateTimeOffsetString into a Java Instant (the type expected by the Olingo type converter).
   *
   * @param edmDateTimeOffsetString string representation of an Edm DateTimeOffset to parse.
   * @return the corresponding Instant value.
   * @throws EdmPrimitiveTypeException thrown if given value cannot be parsed.
   */
  public static Timestamp parseTimestampFromEdmDateTimeOffsetString(String edmDateTimeOffsetString) throws EdmPrimitiveTypeException {
    return EdmDateTimeOffset.getInstance().valueOfString(edmDateTimeOffsetString, true, null, null, null, null, Timestamp.class);
  }

  /**
   * Parses the given edmDateString into a Java Time.
   *
   * @param edmTimeOfDayOffsetString the date string to convert.
   * @return the corresponding Time value.
   * @throws EdmPrimitiveTypeException thrown if given value cannot be parsed.
   */
  public static Time parseTimeOfDayFromEdmTimeOfDayString(String edmTimeOfDayOffsetString) throws EdmPrimitiveTypeException {
    return EdmTimeOfDay.getInstance().valueOfString(edmTimeOfDayOffsetString, true, null, null, null, null, Time.class);
  }

  /**
   * Parses the given DateTimeOffsetString into a Java Time.
   *
   * @param edmDateTimeOffsetString the DateTimeOffsetString to parse.
   * @return the corresponding Time value.
   * @throws EdmPrimitiveTypeException thrown if given value cannot be parsed.
   */
  public static Time parseTimeOfDayFromEdmDateTimeOffsetString(String edmDateTimeOffsetString) throws EdmPrimitiveTypeException {
    return EdmDateTimeOffset.getInstance().valueOfString(edmDateTimeOffsetString, true, null, null, null, null, Time.class);
  }

  /**
   * Parses the given edmDateString into a Java Date
   *
   * @param edmDateString the date string to convert.
   * @return the corresponding Date value.
   * @throws EdmPrimitiveTypeException thrown if given value cannot be parsed.
   */
  public static Date parseDateFromEdmDateString(String edmDateString) throws EdmPrimitiveTypeException {
    return EdmDate.getInstance().valueOfString(edmDateString, true, null, null, null, null, Date.class);
  }

  /**
   * Parses the given edmDateTimeOffsetString into a Java Date
   *
   * @param edmDateTimeOffsetString string representation of an Edm DateTimeOffset to parse.
   * @return the corresponding Date value.
   * @throws EdmPrimitiveTypeException thrown if given value cannot be parsed.
   */
  public static Date parseDateFromEdmDateTimeOffsetString(String edmDateTimeOffsetString) throws EdmPrimitiveTypeException {
    return EdmDate.getInstance().valueOfString(edmDateTimeOffsetString, true, null, null, null, null, Date.class);
  }

  /***
   * Tries to parse datePart from the given Object value
   * @param datePart the timestamp part, "Year", "Month", "Day", etc. to try and parse
   * @param value the value to try and parse
   * @return the Integer portion of the date if successful, otherwise throws an Exception
   * @exception EdmPrimitiveTypeException an exception if value cannot be parsed into a date.
   */
  public static Integer getDatePart(String datePart, Object value) throws EdmPrimitiveTypeException {
    if (value == null) return null;

    LocalDate date = LocalDate.parse(parseDateFromEdmDateString(value.toString()).toString());
    switch (datePart) {
      case DateParts.YEAR:
        return date.getYear();
      case DateParts.MONTH:
        return date.getMonthValue();
      case DateParts.DAY:
        return date.getDayOfMonth();
      default:
        return null;
    }
  }

  /***
   * Tries to parse datePart from the given Object value
   * @param timestampPart the timestamp part, "Year", "Month", "Day", etc. to try and parse
   * @param value the value to try and parse
   * @return the Integer portion of the date if successful, otherwise throws an Exception
   */
  public static Integer getTimestampPart(String timestampPart, Object value) throws DateTimeParseException {
    if (timestampPart == null || value == null) return null;

    ZonedDateTime dateTime = ZonedDateTime.parse((String) value, DateTimeFormatter.ISO_DATE_TIME);

    switch (timestampPart) {
      case DateParts.YEAR:
        return dateTime.getYear();
      case DateParts.MONTH:
        return dateTime.getMonthValue();
      case DateParts.DAY:
        return dateTime.getDayOfMonth();
      case DateParts.HOUR:
        return dateTime.getHour();
      case DateParts.MINUTE:
        return dateTime.getMinute();
      case DateParts.SECOND:
        return dateTime.getSecond();
      case FRACTIONAL:
        return dateTime.toInstant().get(ChronoField.MICRO_OF_SECOND);
      default:
        return null;
    }
  }

  /**
   * Converts the given inputStream to a string.
   *
   * @param inputStream the input stream to convert.
   * @return the string value contained in the stream.
   */
  public static String convertInputStreamToString(InputStream inputStream) {
    try {
      InputStreamReader isReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8.name());
      BufferedReader reader = new BufferedReader(isReader);
      StringBuilder sb = new StringBuilder();
      String str;
      while ((str = reader.readLine()) != null) {
        sb.append(str);
      }
      return sb.toString();
    } catch (Exception ex) {
      LOG.error("Error in convertInputStreamToString: " + ex);
    }
    return null;
  }

  /**
   * For each parameterFieldName value in responseData, assertTrue(value op timestamp)
   *
   * @param parameterFieldName     the field name containing data
   * @param op                     an OData binary operator to be used for comparsions
   * @param parameterAssertedValue value to be used for comparisons
   * @param responseData           string containing JSON response data
   */
  public static void assertDateTimeOffset(String parameterFieldName, String op, String parameterAssertedValue, String responseData, Settings settings) {
    AtomicReference<Timestamp> assertedValue = new AtomicReference<>();
    try {
      assertedValue.set(parseTimestampFromEdmDateTimeOffsetString(Settings.resolveParametersString(parameterAssertedValue, settings)));
      assertDateTimeOffset(parameterFieldName, op, assertedValue.get(), responseData, settings);
    } catch (EdmPrimitiveTypeException tex) {
      fail("ERROR: Cannot Convert the value in "
          + Settings.resolveParametersString(parameterAssertedValue, settings) + " to a Timestamp value!!" + tex);
    }
  }

  /**
   * For each parameterFieldName value in responseData, assertTrue(value op timestamp)
   *
   * @param parameterFieldName the field name containing data
   * @param op                 an OData binary operator to be used for comparsions
   * @param timestamp          value to be used for comparisons
   * @param responseData       string containing JSON response data
   */
  public static void assertDateTimeOffset(String parameterFieldName, String op, Timestamp timestamp, String responseData, Settings settings) {
    LOG.info("Asserted time is: " + timestamp);
    String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
    AtomicReference<Timestamp> fieldValue = new AtomicReference<>();

    from(responseData).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
      try {
        fieldValue.set(parseTimestampFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
        assertTrue(compare(fieldValue.get(), op, timestamp));
      } catch (EdmPrimitiveTypeException tex) {
        fail("ERROR: Cannot Convert the value in " + fieldValue.get() + " to a Timestamp value!!" + tex);
      }
    });
  }

  /**
   * Asserts that metadata in the given container are valid. Fetches metadata if not present in the container.
   * @param container a test container with a valid config that metadata can be fetched into
   */
  public static void assertValidXMLMetadata(WebAPITestContainer container) {
    try {
      if (!container.getHaveMetadataBeenRequested()) {
        //will lazy-load metadata from the server if not yet requested
        container.fetchXMLMetadata();
      }
      container.validateMetadata();
      assertTrue("XML Metadata at the given service root is not valid! " + container.getServiceRoot(),
          container.getIsValidXMLMetadata());
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Asserts that the given container has XML Metadata that contains an Entity Data Model (Edm)
   * @param container the container with XML metadata to validate
   */
  public static void assertXmlMetadataContainsEdm(WebAPITestContainer container) {
    container.setEdm(Commander.deserializeEdm(container.getXMLResponseData(), container.getCommander().getClient()));
    assertNotNull(getDefaultErrorMessage("Edm de-serialized to an empty object!"), container.getEdm());
  }

  /**
   * Asserts that the Edm in the given container are valid
   * @param container the container with the XML Metadata to check
   */
  public static void assertValidEdm(WebAPITestContainer container) {
    assertTrue("Edm Metadata at the given service root is not valid! " + container.getServiceRoot(),
        container.getIsValidEdm());
  }

  /**
   * Asserts that XML Metadata are retrieved from the server
   * @param container the container to retrieve metadata with
   */
  public static void assertXMLMetadataAreRequestedFromTheServer(WebAPITestContainer container) {
    assertNotNull(container);
    assertNotNull("Commander is null!", container.getCommander());

    if (!container.getHaveMetadataBeenRequested()) {
      final String serviceRoot = Settings.resolveParametersString(container.getServiceRoot(), container.getSettings());
      assertEquals(getDefaultErrorMessage("given service root doesn't match the one configured in the Commander"),
          serviceRoot,
          container.getCommander().getServiceRoot());

      try {
        assertNotNull(getDefaultErrorMessage("could not retrieve valid XML Metadata for given service root:", serviceRoot),
            container.fetchXMLMetadata());

      } catch (ODataClientErrorException cex) {
        container.setResponseCode(cex.getStatusLine().getStatusCode());
        fail(getDefaultErrorMessage(cex));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    }
  }

  /**
   * Asserts that the XML Response in the given container is valid XML
   * @param container the container with the XML response to validate
   */
  public static void assertXMLResponseIsValidXML(WebAPITestContainer container) {
    assertNotNull(getDefaultErrorMessage("no XML Response data were found!"), container.getXMLResponseData());
    container.validateXMLMetadataXML();
    assertTrue(getDefaultErrorMessage("invalid XML response!"), container.getIsValidXMLMetadataXML());
  }

  /**
   * Asserts that the XML metadata in the given container has a valid service document
   * @param container the container with XML Metadata to validate
   */
  public static void assertXMLMetadataHasValidServiceDocument(WebAPITestContainer container) {
    try {
      assertNotNull("ERROR: could not find default entity container for given service root: " +
          container.getServiceRoot(), container.getEdm().getEntityContainer());
      LOG.info("Found Default Entity Container: '" + container.getEdm().getEntityContainer().getNamespace() + "'");
    } catch (ODataClientErrorException cex) {
      container.setResponseCode(cex.getStatusLine().getStatusCode());
      fail(cex.toString());
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Asserts that valid Metadata have been retrieved. Fetches metadata if not present.
   * @param container a test container to validate
   */
  public static void assertValidMetadataHaveBeenRetrieved(WebAPITestContainer container) {
    try {
      //NOTE: this is here so that tests may be run individually
      if (!container.getHaveMetadataBeenRequested()) {
        container.fetchXMLMetadata();
        container.validateMetadata();
      }
      assertTrue(getDefaultErrorMessage("Valid metadata could not be retrieved from the server! Please check the log for more information."),
          container.hasValidMetadata());
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Contains the list of supported operators for use in query expressions.
   */
  public static class Operators {
      public static final String
          AND = "and",
          OR = "or",
          NE = "ne",
          EQ = "eq",
          GREATER_THAN = "gt",
          GREATER_THAN_OR_EQUAL = "ge",
          HAS = "has",
          LESS_THAN = "lt",
          LESS_THAN_OR_EQUAL = "le",
          CONTAINS = "contains",
          ENDS_WITH = "endswith",
          STARTS_WITH = "startswith",
          TO_LOWER = "tolower",
          TO_UPPER = "toupper";
  }

  public static final class DateParts {
    public static final String
        YEAR = "year",
        MONTH = "month",
        DAY = "day",
        HOUR = "hour",
        MINUTE = "minute",
        SECOND = "second",
        FRACTIONAL = "fractional";
  }

  public static final class TypeMappings {

    public static final class DataDictionaryTypes {
      public static final String
          STRING = "String",
          DATE = "Date",
          DECIMAL = "Decimal",
          INTEGER = "Integer",
          BOOLEAN = "Boolean",
          SINGLE_ENUM = "Single Enumeration",
          MULTI_ENUM = "Multiple Enumeration",
          TIMESTAMP = "Timestamp";
    }

    public static final class ODataTypes {
      public static final String
          STRING = "Edm.String",
          STRING_COLLECTION = "Collection(Edm.String)",
          DATE = "Edm.Date",
          DECIMAL = "Edm.Decimal",
          DOUBLE = "Edm.Double",
          INT16 = "Edm.Int16",
          INT32 = "Edm.Int32",
          INT64 = "Edm.Int64",
          BOOLEAN = "Edm.Boolean",
          DATETIME_OFFSET = "Edm.DateTimeOffset";
    }
  }
}

