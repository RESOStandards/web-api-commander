package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.commander.common.Utils;
import org.reso.models.ReferenceStandardLookup;
import org.reso.models.ReferenceStandardField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static org.reso.certification.containers.WebAPITestContainer.EMPTY_STRING;
import static org.reso.certification.containers.WebAPITestContainer.SINGLE_SPACE;

public class BDDProcessor extends WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(BDDProcessor.class);
  private static final String FEATURE_EXTENSION = ".feature";
  private static final int EXAMPLES_PADDING_AMOUNT = 6;

  public BDDProcessor(String version) {
    super(version);
  }

  @Override
  void processNumber(ReferenceStandardField row) {
    resourceTemplates.get(row.getResourceName()).append(BDDTemplates.buildNumberTest(row));
  }

  @Override
  void processStringListSingle(ReferenceStandardField row) {
    resourceTemplates.get(row.getResourceName()).append(BDDTemplates.buildStringListSingleTest(row));
  }

  @Override
  void processString(ReferenceStandardField row) {
    resourceTemplates.get(row.getResourceName()).append(BDDTemplates.buildStringTest(row));
  }

  @Override
  void processBoolean(ReferenceStandardField row) {
    resourceTemplates.get(row.getResourceName()).append(BDDTemplates.buildBooleanTest(row));
  }

  @Override
  void processStringListMulti(ReferenceStandardField row) {
    resourceTemplates.get(row.getResourceName()).append(BDDTemplates.buildStringListMultiTest(row));
  }

  @Override
  void processDate(ReferenceStandardField row) {
    resourceTemplates.get(row.getResourceName()).append(BDDTemplates.buildDateTest(row));
  }

  @Override
  void processTimestamp(ReferenceStandardField row) {
    resourceTemplates.get(row.getResourceName()).append(BDDTemplates.buildTimestampTest(row));
  }

  @Override
  void processExpansion(ReferenceStandardField field) {
    //TODO: DD 2.0
  }

  @Override
  void generateOutput() {
    LOG.info("Using reference worksheet: " + getReferenceWorksheet(this.getVersion()));
    LOG.info("Generating BDD .feature files for the following resources: " + resourceTemplates.keySet());
    resourceTemplates.forEach((resourceName, buffer) -> {
      //put in local directory rather than relative to where the input file is
      Utils.createFile(getDirectoryName(), resourceName.toLowerCase() + FEATURE_EXTENSION,
          BDDTemplates.buildHeaderInfo(resourceName, startTimestamp, getVersion()) + buffer.toString());
    });
  }

  public static final class BDDTemplates {
    /**
     * Contains various templates used for test generation
     * TODO: add a formatter rather than using inline spaces
     */
    public static String buildHeaderInfo(String resourceName, String generatedTimestamp, String version) {
      if (resourceName == null) return null;
      if (generatedTimestamp == null) generatedTimestamp = Utils.getTimestamp(new Date());
      return
          "# This file was autogenerated on: " + generatedTimestamp + "\n" +
              "Feature: " + resourceName + "\n\n" +
              "  Background:\n" +
              "    Given a RESOScript or Metadata file are provided\n" +
              "    When a RESOScript file is provided\n" +
              "    Then Client Settings and Parameters can be read from the RESOScript\n" +
              "    And a test container was successfully created from the given RESOScript file\n" +
              "    And the test container uses an Authorization Code or Client Credentials for authentication\n" +
              "    And valid metadata were retrieved from the server\n" +
              "    When a metadata file is provided\n" +
              "    Then a test container was successfully created from the given metadata file\n" +
              "    And valid metadata are loaded into the test container\n";
    }

    /**
     * Builds a list of tags for the given field
     *
     * @param field the field whose tags to extract
     * @return an array list containing tags on specific fields when they are present
     */
    private static ArrayList<String> buildTags(ReferenceStandardField field) {
      ArrayList<String> tags = new ArrayList<>();

      //use this to add each field name tag
      //tags.add(field.getStandardName());

      if (field.getResourceName() != null && field.getResourceName().length() > 0) {
        tags.add(field.getResourceName());
      }

      tags.addAll(field.getPropertyTypes());
      tags.addAll(field.getPayloads());

      return tags;
    }

    public static String padLeft(String s, int n) {
      String[] padding = new String[n];
      Arrays.fill(padding, " ");
      return String.join("", padding) + s;
    }

    private static String generateSynonymsMarkup(ReferenceStandardField field) {
      String template = EMPTY_STRING;

      if (field.getSynonyms().size() > 0) {
        template += "    Given that the following synonyms for \"" + field.getStandardName()
            + "\" DO NOT exist in the \"" + field.getResourceName() + "\" metadata\n" +
            field.getSynonyms().stream()
                .map(synonym -> padLeft("| " + synonym + " |\n", EXAMPLES_PADDING_AMOUNT)).collect(Collectors.joining());
      }
      return template;
    }

    public static String buildBooleanTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;

      return "\n  " + buildTags(field).stream().map(tag -> "@" + tag).collect(Collectors.joining(SINGLE_SPACE)) + "\n" +
          "  Scenario: " + field.getStandardName() + "\n" +
          generateSynonymsMarkup(field) +
          "    When \"" + field.getStandardName() + "\" exists in the \"" + field.getResourceName() + "\" metadata\n" +
          "    Then \"" + field.getStandardName() + "\" MUST be \"Boolean\" data type\n";
    }

    public static String buildDateTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;

      return "\n  " + buildTags(field).stream().map(tag -> "@" + tag).collect(Collectors.joining(SINGLE_SPACE)) + "\n" +
          "  Scenario: " + field.getStandardName() + "\n" +
          generateSynonymsMarkup(field) +
          "    When \"" + field.getStandardName() + "\" exists in the \"" + field.getResourceName() + "\" metadata\n" +
          "    Then \"" + field.getStandardName() + "\" MUST be \"Date\" data type\n";
    }

    /**
     * Provides special routing for Data Dictionary numeric types, which may be Integer or Decimal
     *
     * @param field the numeric field to build type markup for
     * @return a string containing specific markup for the given field
     */
    public static String buildNumberTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;

      if (field.getSuggestedMaxPrecision() != null) return buildDecimalTest(field);
      else return buildIntegerTest(field);
    }

    public static String buildDecimalTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;
      String template =
          "\n  " + buildTags(field).stream().map(tag -> "@" + tag).collect(Collectors.joining(SINGLE_SPACE)) + "\n" +
              "  Scenario: " + field.getStandardName() + "\n" +
              generateSynonymsMarkup(field) +
              "    When \"" + field.getStandardName() + "\" exists in the \"" + field.getResourceName() + "\" metadata\n" +
              "    Then \"" + field.getStandardName() + "\" MUST be \"Decimal\" data type\n";

      //TODO Length is actually scale for Decimal fields by the DD! :/
      if (field.getSuggestedMaxLength() != null)
        template +=
            "    And \"" + field.getStandardName() + "\" precision SHOULD be equal to the RESO Suggested Max Precision of " +
                field.getSuggestedMaxLength() + "\n";

      //TODO Precision is actually Scale for Decimal fields by the DD! :/
      if (field.getSuggestedMaxPrecision() != null)
        template +=
            "    And \"" + field.getStandardName() + "\" scale SHOULD be equal to the RESO Suggested Max Scale of " +
                field.getSuggestedMaxPrecision() + "\n";

      return template;
    }

    public static String buildIntegerTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;

      return "\n  " + buildTags(field).stream().map(tag -> "@" + tag).collect(Collectors.joining(SINGLE_SPACE)) + "\n" +
          "  Scenario: " + field.getStandardName() + "\n" +
          generateSynonymsMarkup(field) +
          "    When \"" + field.getStandardName() + "\" exists in the \"" + field.getResourceName() + "\" metadata\n" +
          "    Then \"" + field.getStandardName() + "\" MUST be \"Integer\" data type\n";
    }

    private static String buildStandardEnumerationMarkup(String lookupName) {
      if (standardEnumerationsMap.containsKey(lookupName)) {
        StringBuilder markup = new StringBuilder();

        for (ReferenceStandardLookup lookup : standardEnumerationsMap.get(lookupName)) {
          markup
              .append(padLeft("| ", EXAMPLES_PADDING_AMOUNT))
              .append(lookup.getLookupValue()).append(" | ")
              .append(lookup.getLegacyODataValue()).append(" |\n");
        }
        if (markup.length() > 0) markup.insert(0,  padLeft("| lookupValue | lookupDisplayName |\n", EXAMPLES_PADDING_AMOUNT));
        return markup.toString();
      }

      LOG.info("No enumerations found for lookupName: " + lookupName);
      return EMPTY_STRING;
    }

    public static String buildStringListMultiTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;

      return
          "\n  " + buildTags(field).stream().map(tag -> "@" + tag).collect(Collectors.joining(SINGLE_SPACE)) + "\n" +
              "  Scenario: " + field.getStandardName() + "\n" +
              generateSynonymsMarkup(field) +
              "    When \"" + field.getStandardName() + "\" exists in the \"" + field.getResourceName() + "\" metadata\n" +
              "    Then \"" + field.getStandardName() + "\" MUST be \"Multiple Enumeration\" data type\n";
    }

    public static String buildStringListSingleTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;

      return
          "\n  " + buildTags(field).stream().map(tag -> "@" + tag).collect(Collectors.joining(SINGLE_SPACE)) + "\n" +
              "  Scenario: " + field.getStandardName() + "\n" +
              generateSynonymsMarkup(field) +
              "    When \"" + field.getStandardName() + "\" exists in the \"" + field.getResourceName() + "\" metadata\n" +
              "    Then \"" + field.getStandardName() + "\" MUST be \"Single Enumeration\" data type\n";
    }

    public static String buildStringTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;
      String template =
          "\n  " + buildTags(field).stream().map(tag -> "@" + tag).collect(Collectors.joining(SINGLE_SPACE)) + "\n" +
              "  Scenario: " + field.getStandardName() + "\n" +
              generateSynonymsMarkup(field) +
              "    When \"" + field.getStandardName() + "\" exists in the \"" + field.getResourceName() + "\" metadata\n" +
              "    Then \"" + field.getStandardName() + "\" MUST be \"String\" data type\n";

      if (field.getSuggestedMaxLength() != null)
        template +=
            "    And \"" + field.getStandardName() + "\" length SHOULD be equal to the RESO Suggested Max Length of " + field.getSuggestedMaxLength() + "\n";

      return template;
    }

    public static String buildTimestampTest(ReferenceStandardField field) {
      if (field == null) return EMPTY_STRING;

      return "\n  " + buildTags(field).stream().map(tag -> "@" + tag).collect(Collectors.joining(SINGLE_SPACE)) + "\n" +
          "  Scenario: " + field.getStandardName() + "\n" +
          generateSynonymsMarkup(field) +
          "    When \"" + field.getStandardName() + "\" exists in the \"" + field.getResourceName() + "\" metadata\n" +
          "    Then \"" + field.getStandardName() + "\" MUST be \"Timestamp\" data type\n";
    }
  }
}
