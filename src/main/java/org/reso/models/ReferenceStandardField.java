package org.reso.models;

import java.util.List;

/**
 *
 */
public class ReferenceStandardField {
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


  private ReferenceStandardField() { /* private constructor use Builder instead */ }

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

  public String getLookupName() {
    String lookupName = getLookup()
        .replace("<n/a>", "")
        .replace("Lookups", "").trim();

    if (lookupName.length() == 0) return null;

    return lookupName;
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
    ReferenceStandardField referenceStandardField = new ReferenceStandardField();

    public Builder setStandardName(String standardName) {
      referenceStandardField.standardName = standardName;
      return this;
    }

    public Builder setDisplayName(String displayName) {
      referenceStandardField.displayName = displayName;
      return this;
    }

    public Builder setParentResourceName(String parentResourceName) {
      referenceStandardField.parentResourceName = parentResourceName;
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

    public Builder setCertificationLevel(String certificationLevel) {
      referenceStandardField.certificationLevel = certificationLevel;
      return this;
    }

    public Builder setRecordId(Integer recordId) {
      referenceStandardField.recordId = recordId;
      return this;
    }

    public Builder setLookup(String lookup) {
      referenceStandardField.lookup = lookup;
      return this;
    }

    public Builder setLookupStatus(String lookupStatus) {
      referenceStandardField.lookupStatus = lookupStatus;
      return this;
    }

    public Builder setCollection(String collection) {
      referenceStandardField.collection = collection;
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

    public Builder setSpanishStandardName(String spanishStandardName) {
      referenceStandardField.spanishStandardName = spanishStandardName;
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

    public ReferenceStandardField build() {
      return referenceStandardField;
    }
  }
}
