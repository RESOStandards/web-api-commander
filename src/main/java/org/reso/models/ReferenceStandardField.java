package org.reso.models;

import java.util.List;

/**
 *  Defines a standard field according to the Data Dictionary specification
 *  Matches the format of the Data Dictionary worksheet:
 *  @see <a href="https://docs.google.com/spreadsheets/d/1_59Iqr7AQ51rEFa7p0ND-YhJjEru8gY-D_HM1yy5c6w/edit#gid=544946974" />
 *
 */
public class ReferenceStandardField {
  private String resourceName;
  private String standardName;
  private String displayName;
  private String definition;
  private List<String> groups;
  private String simpleDataType;
  private String sourceResource;
  private Integer suggestedMaxLength;
  private Integer suggestedMaxPrecision;
  private List<String> synonyms;
  private String frenchCanadianDisplayName;
  private String spanishDisplayName;
  private String elementStatus;
  private String recordId;
  private String lookupStatus;
  private String lookupName;
  private Boolean repeatingElement;
  private List<String> propertyTypes;
  private List<String> payloads;
  private String statusChangeDate;
  private String revisedDate;
  private String addedInVersion;
  private String wikiPageTitle;
  private String wikiPageUrl;
  private Integer wikiPageId;
  private String bedes;


  private ReferenceStandardField() { /* private constructor use Builder instead */ }

  public Boolean isMultipleEnumeration() {
    final String DATA_TYPE_NAME = "String List, Multi";
    return getSimpleDataType().trim().contentEquals(DATA_TYPE_NAME);
  }

  public Boolean isSingleEnumeration() {
    final String DATA_TYPE_NAME = "String List, Single";
    return getSimpleDataType().trim().contentEquals(DATA_TYPE_NAME);
  }

  public Boolean isExpansion() {
    return getSourceResource() != null && getSourceResource().trim().length() > 0;
  }

  public String getStandardName() {
    return standardName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getResourceName(){
    return resourceName;
  }

  public String getDefinition() {
    return definition;
  }

  public List<String> getGroups() {
    return groups;
  }

  public String getSimpleDataType() {
    return simpleDataType;
  }

  public String getSourceResource() {
    return sourceResource;
  }

  public Integer getSuggestedMaxLength() {
    return suggestedMaxLength;
  }

  public List<String> getSynonyms() {
    return synonyms;
  }

  public String getElementStatus() {
    return elementStatus;
  }

  public String getBedes() {
    return bedes;
  }

  public String getRecordId() {
    return recordId;
  }

  public String getLookupStatus() {
    return lookupStatus;
  }

  public String getLookupName() {
    String parsedLookupName = lookupName
        .replace("<n/a>", "")
        .replace("Lookups", "").trim();

    if (parsedLookupName.length() == 0) {
      return null;
    } else {
      return parsedLookupName;
    }
  }

  public Integer getSuggestedMaxPrecision() {
    return suggestedMaxPrecision;
  }

  public Boolean getRepeatingElement() {
    return repeatingElement;
  }

  public List<String> getPropertyTypes() {
    return propertyTypes;
  }

  public List<String> getPayloads() {
    return payloads;
  }

  public String getSpanishDisplayName() {
    return spanishDisplayName;
  }

  public String getFrenchCanadianDisplayName() {
    return frenchCanadianDisplayName;
  }

  public String getStatusChangeDate() {
    return statusChangeDate;
  }

  public String getRevisedDate() {
    return revisedDate;
  }

  public String getAddedInVersion() {
    return addedInVersion;
  }

  public String getWikiPageTitle() {
    return wikiPageTitle;
  }

  public String getWikiPageUrl() {
    return wikiPageUrl;
  }

  public Integer getWikiPageId() {
    return wikiPageId;
  }

  public void setResourceName() {
    this.resourceName = resourceName;
  }

  public static class Builder {
    ReferenceStandardField referenceStandardField = new ReferenceStandardField();

    public Builder setStandardName(String standardName) {
      referenceStandardField.standardName = standardName;
      return this;
    }

    public Builder setDisplayName(String displayName) {
      referenceStandardField.displayName = displayName;
      return this;
    }

    public Builder setResourceName(String resourceName) {
      referenceStandardField.resourceName = resourceName;
      return this;
    }

    public Builder setDefinition(String definition) {
      referenceStandardField.definition = definition;
      return this;
    }

    public Builder setGroups(List<String> groups) {
      referenceStandardField.groups = groups;
      return this;
    }

    public Builder setSimpleDataType(String simpleDataType) {
      referenceStandardField.simpleDataType = simpleDataType;
      return this;
    }

    public Builder setSuggestedMaxLength(Integer suggestedMaxLength) {
      referenceStandardField.suggestedMaxLength = suggestedMaxLength;
      return this;
    }

    public Builder setSynonyms(List<String> synonyms) {
      referenceStandardField.synonyms = synonyms;
      return this;
    }

    public Builder setElementStatus(String elementStatus) {
      referenceStandardField.elementStatus = elementStatus;
      return this;
    }

    public Builder setBedes(String bedes) {
      referenceStandardField.bedes = bedes;
      return this;
    }

    public Builder setRecordId(String recordId) {
      referenceStandardField.recordId = recordId;
      return this;
    }

    public Builder setLookupName(String lookupName) {
      referenceStandardField.lookupName = lookupName;
      return this;
    }

    public Builder setLookupStatus(String lookupStatus) {
      referenceStandardField.lookupStatus = lookupStatus;
      return this;
    }

    public Builder setSuggestedMaxPrecision(Integer suggestedMaxPrecision) {
      referenceStandardField.suggestedMaxPrecision = suggestedMaxPrecision;
      return this;
    }

    public Builder setRepeatingElement(Boolean repeatingElement) {
      referenceStandardField.repeatingElement = repeatingElement;
      return this;
    }

    public Builder setPropertyTypes(List<String> propertyTypes) {
      referenceStandardField.propertyTypes = propertyTypes;
      return this;
    }

    public Builder setPayloads(List<String> payloads) {
      referenceStandardField.payloads = payloads;
      return this;
    }

    public Builder setSpanishDisplayName(String spanishDisplayName) {
      referenceStandardField.spanishDisplayName = spanishDisplayName;
      return this;
    }

    public Builder setStatusChangeDate(String statusChangeDate) {
      referenceStandardField.statusChangeDate = statusChangeDate;
      return this;
    }

    public Builder setRevisedDate(String revisedDate) {
      referenceStandardField.revisedDate = revisedDate;
      return this;
    }

    public Builder setAddedInVersion(String addedInVersion) {
      referenceStandardField.addedInVersion = addedInVersion;
      return this;
    }

    public Builder setWikiPageTitle(String wikiPageTitle) {
      referenceStandardField.wikiPageTitle = wikiPageTitle;
      return this;
    }

    public Builder setWikiPageURL(String wikiPageUrl) {
      referenceStandardField.wikiPageUrl = wikiPageUrl;
      return this;
    }

    public Builder setWikiPageID(Integer wikiPageId) {
      referenceStandardField.wikiPageId = wikiPageId;
      return this;
    }

    public Builder setSourceResource(String sourceResource) {
      referenceStandardField.sourceResource = sourceResource;
      return this;
    }

    public Builder setFrenchCanadianDisplayName(String frenchCanadianDisplayName) {
      referenceStandardField.frenchCanadianDisplayName = frenchCanadianDisplayName;
      return this;
    }

    public ReferenceStandardField build() {
      return referenceStandardField;
    }
  }
}
