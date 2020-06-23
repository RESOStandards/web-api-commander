package org.reso.models;

import java.util.List;

/**
 *
 */
public class StandardField {
  private String standardName;
  private String displayName;
  private String parentResourceName;
  private String definition;
  private List<String> groups;
  private String simpleDataType;
  private Integer suggestedMaxLength;
  private List<String> synonyms;
  private String elementStatus;
  private String bedes;
  private String certificationLevel;
  private Integer recordId;
  private String lookupStatus;
  private String lookup;
  private String collection;
  private Integer suggestedMaxPrecision;
  private Boolean repeatingElement;
  private List<String> propertyTypes;
  private List<String> payloads;
  private String spanishStandardName;
  private String statusChangeDate;
  private String revisedDate;
  private String addedInVersion;
  private String wikiPageTitle;
  private String wikiPageUrl;
  private Integer wikiPageId;


  private StandardField() { /* private constructor use Builder instead */ }

  public Boolean isMultipleEnumeration() {
    final String DATA_TYPE_NAME = "String List, Multi";
    return getSimpleDataType().trim().contentEquals(DATA_TYPE_NAME);
  }

  public Boolean isSingleEnumeration() {
    final String DATA_TYPE_NAME = "String List, Single";
    return getSimpleDataType().trim().contentEquals(DATA_TYPE_NAME);
  }

  public String getStandardName() {
    return standardName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getParentResourceName(){
    return parentResourceName;
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

  public String getCertificationLevel() {
    return certificationLevel;
  }

  public Integer getRecordId() {
    return recordId;
  }

  public String getLookupStatus() {
    return lookupStatus;
  }

  public String getLookup() {
    return lookup;
  }

  public String getLookupStandardName() {
    return getLookup().replace("Lookups", "").trim();
  }

  public String getCollection() {
    return collection;
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

  public String getSpanishStandardName() {
    return spanishStandardName;
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

  public void setParentResourceName(String parentResourceName) {
    this.parentResourceName = parentResourceName;
  }

  public static class Builder {
    StandardField standardField = new StandardField();

    public Builder setStandardName(String standardName) {
      standardField.standardName = standardName;
      return this;
    }

    public Builder setDisplayName(String displayName) {
      standardField.displayName = displayName;
      return this;
    }

    public Builder setParentResourceName(String parentResourceName) {
      standardField.parentResourceName = parentResourceName;
      return this;
    }

    public Builder setDefinition(String definition) {
      standardField.definition = definition;
      return this;
    }

    public Builder setGroups(List<String> groups) {
      standardField.groups = groups;
      return this;
    }

    public Builder setSimpleDataType(String simpleDataType) {
      standardField.simpleDataType = simpleDataType;
      return this;
    }

    public Builder setSuggestedMaxLength(Integer suggestedMaxLength) {
      standardField.suggestedMaxLength = suggestedMaxLength;
      return this;
    }

    public Builder setSynonyms(List<String> synonyms) {
      standardField.synonyms = synonyms;
      return this;
    }

    public Builder setElementStatus(String elementStatus) {
      standardField.elementStatus = elementStatus;
      return this;
    }

    public Builder setBedes(String bedes) {
      standardField.bedes = bedes;
      return this;
    }

    public Builder setCertificationLevel(String certificationLevel) {
      standardField.certificationLevel = certificationLevel;
      return this;
    }

    public Builder setRecordId(Integer recordId) {
      standardField.recordId = recordId;
      return this;
    }

    public Builder setLookup(String lookup) {
      standardField.lookup = lookup;
      return this;
    }

    public Builder setLookupStatus(String lookupStatus) {
      standardField.lookupStatus = lookupStatus;
      return this;
    }

    public Builder setCollection(String collection) {
      standardField.collection = collection;
      return this;
    }

    public Builder setSuggestedMaxPrecision(Integer suggestedMaxPrecision) {
      standardField.suggestedMaxPrecision = suggestedMaxPrecision;
      return this;
    }

    public Builder setRepeatingElement(Boolean repeatingElement) {
      standardField.repeatingElement = repeatingElement;
      return this;
    }

    public Builder setPropertyTypes(List<String> propertyTypes) {
      standardField.propertyTypes = propertyTypes;
      return this;
    }

    public Builder setPayloads(List<String> payloads) {
      standardField.payloads = payloads;
      return this;
    }

    public Builder setSpanishStandardName(String spanishStandardName) {
      standardField.spanishStandardName = spanishStandardName;
      return this;
    }

    public Builder setStatusChangeDate(String statusChangeDate) {
      standardField.statusChangeDate = statusChangeDate;
      return this;
    }

    public Builder setRevisedDate(String revisedDate) {
      standardField.revisedDate = revisedDate;
      return this;
    }

    public Builder setAddedInVersion(String addedInVersion) {
      standardField.addedInVersion = addedInVersion;
      return this;
    }

    public Builder setWikiPageTitle(String wikiPageTitle) {
      standardField.wikiPageTitle = wikiPageTitle;
      return this;
    }

    public Builder setWikiPageURL(String wikiPageUrl) {
      standardField.wikiPageUrl = wikiPageUrl;
      return this;
    }

    public Builder setWikiPageID(Integer wikiPageId) {
      standardField.wikiPageId = wikiPageId;
      return this;
    }

    public StandardField build() {
      return standardField;
    }
  }
}
