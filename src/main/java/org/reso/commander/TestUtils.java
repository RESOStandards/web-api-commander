package org.reso.commander;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.communication.response.ODataRawResponse;
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
import org.reso.models.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static io.restassured.path.json.JsonPath.from;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class TestUtils {
  public static final String JSON_VALUE_PATH = "value";
  public static final String HEADER_ODATA_VERSION = "OData-Version";
  private static final Logger LOG = LogManager.getLogger(TestUtils.class);

  /**
   * Finds the default entity container for the given configuration.
   *
   * @param xmlMetadata XML Metadata to search through
   * @return the default CSDL Container for the given XMLMetadata
   * @throws Exception if required metadata cannot be parsed, an exception will be thrown with an appropriate message.
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
   * @param entityTypeName the name of the entityType to search for. MUST be in the default EntityContainer.
   * @return a list of CsdlProperty items for the given entityTypeName
   * @throws Exception is thrown if the given metadata doesn't contain the given type name.
   */
  public static List<CsdlProperty> findEntityTypesForEntityTypeName(Edm edm, XMLMetadata xmlMetadata, String entityTypeName) {
    assertNotNull("ERROR: Edm Cannot be Null!", edm);
    assertNotNull("ERROR: XMLMetadata Cannot be Null!", xmlMetadata);
    assertNotNull("ERROR: entityTypeName cannot be null!", entityTypeName);

    CsdlEntityContainer entityContainer = findDefaultEntityContainer(edm, xmlMetadata);
    assertNotNull("ERROR: could not find a default entity container for the given server!", entityContainer);

    CsdlSchema schemaForType = xmlMetadata.getSchema(entityContainer.getEntitySet(entityTypeName).getTypeFQN().getNamespace());

    assertNotNull("ERROR: could not find type corresponding to given type name: " + entityTypeName, schemaForType);

    return schemaForType.getEntityType(entityTypeName).getProperties();
  }

  /**
   * Gets a list of CsdlProperty items for the given entityTypeName.
   *
   * @param xmlMetadata    the metadata to search.
   * @param entityTypeName the name of the entityType to search for. MUST be in the default EntityContainer.
   * @return a list of CsdlProperty items for the given entityTypeName
   * @throws Exception is thrown if the given metadata doesn't contain the given type name.
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
   * Returns true if each item in the list is true
   *
   * @param lhs Integer value
   * @param op  an OData binary operator for use for comparisons
   * @param rhs Integer value
   * @return true if lhs op rhs produces true, false otherwise
   */
  public static boolean compare(Integer lhs, String op, Integer rhs) {
    String operator = op.toLowerCase();
    boolean result = false;

    if (operator.contentEquals(Operators.GREATER_THAN)) {
      result = lhs > rhs;
    } else if (operator.contentEquals(Operators.GREATER_THAN_OR_EQUAL)) {
      result = lhs >= rhs;
    } else if (operator.contentEquals(Operators.EQ)) {
      result = lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.NE)) {
      result = !lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.LESS_THAN)) {
      result = lhs < rhs;
    } else if (operator.contentEquals(Operators.LESS_THAN_OR_EQUAL)) {
      result = lhs <= rhs;
    }
    LOG.info("Compare: " + lhs + " " + operator + " " + rhs + " ==> " + result);
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
    String operator = op.toLowerCase();
    boolean result = false;

    if (operator.contentEquals(Operators.CONTAINS)) {
      result = lhs.contains(rhs);
    } else if (operator.contentEquals(Operators.STARTS_WITH)) {
      result = lhs.startsWith(rhs);
    } else if (operator.contentEquals(Operators.ENDS_WITH)) {
      result = lhs.endsWith(rhs);
    } else if (operator.contentEquals(Operators.TO_LOWER)) {
      result = lhs.toLowerCase().equals(rhs);
    } else if (operator.contentEquals(Operators.TO_UPPER)) {
      result = lhs.toUpperCase().equals(rhs);
    }
    LOG.info("Compare: \"" + lhs + "\" " + operator + " \"" + rhs + "\" ==> " + result);
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
    String operator = op.toLowerCase();
    boolean result = false;

    if (operator.contentEquals(Operators.GREATER_THAN)) {
      result = lhs.after(rhs);
    } else if (operator.contentEquals(Operators.GREATER_THAN_OR_EQUAL)) {
      result = lhs.after(rhs) || lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.EQ)) {
      result = lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.NE)) {
      result = !lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.LESS_THAN)) {
      result = lhs.before(rhs);
    } else if (operator.contentEquals(Operators.LESS_THAN_OR_EQUAL)) {
      result = lhs.before(rhs) || lhs.equals(rhs);
    }
    LOG.info("Compare: " + lhs + " " + operator + " " + rhs + " ==> " + result);
    return result;
  }

  /**
   * Year Comparator
   *
   * @param lhs Year to compare
   * @param op  an OData binary operator to use for comparisons
   * @param rhs Timestamp to compare
   * @return true if lhs op rhs, false otherwise
   */
  public static boolean compare(Year lhs, String op, Year rhs) {
    String operator = op.toLowerCase();
    boolean result = false;

    if (operator.contentEquals(Operators.GREATER_THAN)) {
      result = lhs.isAfter(rhs);
    } else if (operator.contentEquals(Operators.GREATER_THAN_OR_EQUAL)) {
      result = lhs.isAfter(rhs) || lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.EQ)) {
      result = lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.NE)) {
      result = !lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.LESS_THAN)) {
      result = lhs.isBefore(rhs);
    } else if (operator.contentEquals(Operators.LESS_THAN_OR_EQUAL)) {
      result = lhs.isBefore(rhs) || lhs.equals(rhs);
    }
    LOG.info("Compare: " + lhs + " " + operator + " " + rhs + " ==> " + result);
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
    String operator = op.toLowerCase();
    boolean result = false;

    if (operator.contentEquals(Operators.GREATER_THAN)) {
      result = lhs.toLocalTime().isAfter(rhs.toLocalTime());
    } else if (operator.contentEquals(Operators.GREATER_THAN_OR_EQUAL)) {
      result = lhs.toLocalTime().isAfter(rhs.toLocalTime()) || lhs.toLocalTime().equals(rhs.toLocalTime());
    } else if (operator.contentEquals(Operators.EQ)) {
      result = lhs.toLocalTime().equals(rhs.toLocalTime());
    } else if (operator.contentEquals(Operators.NE)) {
      result = !lhs.toLocalTime().equals(rhs.toLocalTime());
    } else if (operator.contentEquals(Operators.LESS_THAN)) {
      result = lhs.toLocalTime().isBefore(rhs.toLocalTime());
    } else if (operator.contentEquals(Operators.LESS_THAN_OR_EQUAL)) {
      result = lhs.toLocalTime().isBefore(rhs.toLocalTime()) || lhs.toLocalTime().equals(rhs.toLocalTime());
    }
    LOG.info("Compare: " + lhs + " " + operator + " " + rhs + " ==> " + result);
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
    String operator = op.toLowerCase();
    boolean result = false;

    if (operator.contentEquals(Operators.GREATER_THAN)) {
      result = lhs.after(rhs);
    } else if (operator.contentEquals(Operators.GREATER_THAN_OR_EQUAL)) {
      result = lhs.after(rhs) || lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.EQ)) {
      result = lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.NE)) {
      result = !lhs.equals(rhs);
    } else if (operator.contentEquals(Operators.LESS_THAN)) {
      result = lhs.before(rhs);
    } else if (operator.contentEquals(Operators.LESS_THAN_OR_EQUAL)) {
      result = lhs.before(rhs) || lhs.equals(rhs);
    }
    LOG.info("Compare: " + lhs + " " + operator + " " + rhs + " ==> " + result);
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
   * Returns the String data contained within a given ODataRawResponse.
   *
   * @param oDataRawResponse the response to convert.
   * @return the response stream as a string.
   */
  public static String getResponseData(ODataRawResponse oDataRawResponse) {
    return convertInputStreamToString(oDataRawResponse.getRawResponse());
  }

  public static String getHeaderData(String key, Header[] headers) {
    String data = null;

    for (Header header : headers) {
      if (header.getName().toLowerCase().contains(key.toLowerCase())) {
        data = header.getValue();
      }
    }
    return data;
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
   * Parses the given edmDateString into a Java Timestamp.
   *
   * @param edmDateString the date string to convert.
   * @return the corresponding Timestamp value.
   * @throws EdmPrimitiveTypeException thrown if given value cannot be parsed.
   */
  public static Timestamp parseTimestampFromEdmDateString(String edmDateString) throws EdmPrimitiveTypeException {
    return EdmDate.getInstance().valueOfString(edmDateString, true, null, null, null, null, Timestamp.class);
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
    return EdmDateTimeOffset.getInstance().valueOfString(edmDateTimeOffsetString, true, null, null, null, null, Date.class);
  }

  /***
   * Tries to parse datePart from the given Object value
   * @param datePart the timestamp part, "Year", "Month", "Day", etc. to try and parse
   * @param value the value to try and parse
   * @return the Integer portion of the date if successful, otherwise throws an Exception
   * @exception throws an exception if value cannot be parsed into a date.
   */
  public static Integer getDatePart(String datePart, Object value) throws EdmPrimitiveTypeException {
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
  public static Integer getTimestampPart(String timestampPart, Object value) throws EdmPrimitiveTypeException {
    //Turns nanoseconds into two most significant 2 digits for fractional comparisons
    Integer ADJUSTMENT_FACTOR = 10000000;
    OffsetDateTime offsetDateTime = OffsetDateTime.parse(value.toString());

    if (timestampPart.equals(DateParts.YEAR)) {
      return offsetDateTime.getYear();
    } else if (timestampPart.equals(DateParts.MONTH)) {
      return offsetDateTime.getMonthValue();
    } else if (timestampPart.equals(DateParts.DAY)) {
      return offsetDateTime.getDayOfMonth();
    } else if (timestampPart.equals(DateParts.HOUR)) {
      return offsetDateTime.getHour();
    } else if (timestampPart.equals(DateParts.MINUTE)) {
      return offsetDateTime.getMinute();
    } else if (timestampPart.equals(DateParts.SECOND)) {
      return offsetDateTime.getSecond();
    } else if (timestampPart.equals(DateParts.FRACTIONAL)) {
      return offsetDateTime.getNano() / ADJUSTMENT_FACTOR;
    } else {
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
    InputStreamReader isReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(isReader);
    StringBuilder sb = new StringBuilder();
    String str;
    try {
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
      LOG.error("ERROR: Cannot Convert the value in "
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
        LOG.error("ERROR: Cannot Convert the value in " + fieldValue.get() + " to a Timestamp value!!" + tex);
      }
    });
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
        LESS_THAN = "lt",
        LESS_THAN_OR_EQUAL = "le",
        CONTAINS = "contains",
        ENDS_WITH = "endswith",
        STARTS_WITH = "startswith",
        TO_LOWER = "tolower",
        TO_UPPER = "toupper";
  }

  public static class DateParts {
    public static final String
        YEAR = "year",
        MONTH = "month",
        DAY = "day",
        HOUR = "hour",
        MINUTE = "minute",
        SECOND = "second",
        FRACTIONAL = "fractional";
  }
}

