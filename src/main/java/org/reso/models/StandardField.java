package org.reso.models;

import java.util.ArrayList;
import java.util.List;

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
  private String wikiPageURL;
  private Integer wikiPageID;


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

  public void setStandardName(String standardName) {
    this.standardName = standardName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getParentResourceName(){
    return parentResourceName;
  }

  public void setParentResourceName(String parentResourceName) {
    this.parentResourceName = parentResourceName;
  }

  public String getDefinition() {
    return definition;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(List<String> groups) {
    this.groups = groups == null ? new ArrayList<>() : groups;
  }

  public String getSimpleDataType() {
    return simpleDataType;
  }

  public void setSimpleDataType(String simpleDataType) {
    this.simpleDataType = simpleDataType;
  }

  public Integer getSuggestedMaxLength() {
    return suggestedMaxLength;
  }

  public void setSuggestedMaxLength(Integer suggestedMaxLength) {
    this.suggestedMaxLength = suggestedMaxLength;
  }

  public List<String> getSynonyms() {
    return synonyms;
  }

  public void setSynonyms(List<String> synonyms) {
    this.synonyms = synonyms == null ? new ArrayList<>() : synonyms;
  }

  public String getElementStatus() {
    return elementStatus;
  }

  public void setElementStatus(String elementStatus) {
    this.elementStatus = elementStatus;
  }

  public String getBedes() {
    return bedes;
  }

  public void setBedes(String bedes) {
    this.bedes = bedes;
  }

  public String getCertificationLevel() {
    return certificationLevel;
  }

  public void setCertificationLevel(String certificationLevel) {
    this.certificationLevel = certificationLevel;
  }

  public Integer getRecordId() {
    return recordId;
  }

  public void setRecordId(Integer recordId) {
    this.recordId = recordId;
  }

  public String getLookupStatus() {
    return lookupStatus;
  }

  public void setLookupStatus(String lookupStatus) {
    this.lookupStatus = lookupStatus;
  }

  public String getLookup() {
    return lookup;
  }

  public String getLookupStandardName() {
    return getLookup().replace("Lookups", "").trim();
  }

  public void setLookup(String lookup) {
    this.lookup = lookup;
  }

  public String getCollection() {
    return collection;
  }

  public void setCollection(String collection) {
    this.collection = collection;
  }

  public Integer getSuggestedMaxPrecision() {
    return suggestedMaxPrecision;
  }

  public void setSuggestedMaxPrecision(Integer suggestedMaxPrecision) {
    this.suggestedMaxPrecision = suggestedMaxPrecision;
  }

  public Boolean getRepeatingElement() {
    return repeatingElement;
  }

  public void setRepeatingElement(Boolean repeatingElement) {
    this.repeatingElement = repeatingElement;
  }

  public List<String> getPropertyTypes() {
    return propertyTypes;
  }

  public void setPropertyTypes(List<String> propertyTypes) {
    this.propertyTypes = propertyTypes == null ? new ArrayList<>() : propertyTypes;
  }

  public List<String> getPayloads() {
    return payloads;
  }

  public void setPayloads(List<String> payloads) {
    this.payloads = payloads == null ? new ArrayList<>() : payloads;
  }

  public String getSpanishStandardName() {
    return spanishStandardName;
  }

  public void setSpanishStandardName(String spanishStandardName) {
    this.spanishStandardName = spanishStandardName;
  }

  public String getStatusChangeDate() {
    return statusChangeDate;
  }

  public void setStatusChangeDate(String statusChangeDate) {
    this.statusChangeDate = statusChangeDate;
  }

  public String getRevisedDate() {
    return revisedDate;
  }

  public void setRevisedDate(String revisedDate) {
    this.revisedDate = revisedDate;
  }

  public String getAddedInVersion() {
    return addedInVersion;
  }

  public void setAddedInVersion(String addedInVersion) {
    this.addedInVersion = addedInVersion;
  }

  public String getWikiPageTitle() {
    return wikiPageTitle;
  }

  public void setWikiPageTitle(String wikiPageTitle) {
    this.wikiPageTitle = wikiPageTitle;
  }

  public String getWikiPageURL() {
    return wikiPageURL;
  }

  public void setWikiPageURL(String wikiPageURL) {
    this.wikiPageURL = wikiPageURL;
  }

  public Integer getWikiPageID() {
    return wikiPageID;
  }

  public void setWikiPageID(Integer wikiPageID) {
    this.wikiPageID = wikiPageID;
  }

  public static class Builder {
    StandardField standardField = new StandardField();

    public Builder setStandardName(String standardName) {
      standardField.setStandardName(standardName);
      return this;
    }

    public Builder setDisplayName(String displayName) {
      standardField.setDisplayName(displayName);
      return this;
    }

    public Builder setParentResourceName(String parentResourceName) {
      standardField.setParentResourceName(parentResourceName);
      return this;
    }

    public Builder setDefinition(String definition) {
      standardField.setDefinition(definition);
      return this;
    }

    public Builder setGroups(List<String> groups) {
      standardField.setGroups(groups);
      return this;
    }

    public Builder setSimpleDataType(String dataType) {
      standardField.setSimpleDataType(dataType);
      return this;
    }

    public Builder setSuggestedMaxLength(Integer suggestedMaxLength) {
      standardField.setSuggestedMaxLength(suggestedMaxLength);
      return this;
    }

    public Builder setSynonyms(List<String> synonyms) {
      standardField.setSynonyms(synonyms);
      return this;
    }

    public Builder setElementStatus(String status) {
      standardField.setElementStatus(status);
      return this;
    }

    public Builder setBedes(String bedes) {
      standardField.setBedes(bedes);
      return this;
    }

    public Builder setCertificationLevel(String certificationLevel) {
      standardField.setCertificationLevel(certificationLevel);
      return this;
    }

    public Builder setRecordId(Integer recordId) {
      standardField.setRecordId(recordId);
      return this;
    }

    public Builder setLookup(String lookup) {
      standardField.setLookup(lookup);
      return this;
    }

    public Builder setLookupStatus(String lookupStatus) {
      standardField.setLookupStatus(lookupStatus);
      return this;
    }

    public Builder setCollection(String collection) {
      standardField.setCollection(collection);
      return this;
    }

    public Builder setSuggestedMaxPrecision(Integer suggestedMaxPrecision) {
      standardField.setSuggestedMaxPrecision(suggestedMaxPrecision);
      return this;
    }

    public Builder setRepeatingElement(Boolean isRepeatingElement) {
      standardField.setRepeatingElement(isRepeatingElement);
      return this;
    }

    public Builder setPropertyTypes(List<String> propertyTypes) {
      standardField.setPropertyTypes(propertyTypes);
      return this;
    }

    public Builder setPayloads(List<String> payloads) {
      standardField.setPayloads(payloads);
      return this;
    }

    public Builder setSpanishStandardName(String spanishStandardName) {
      standardField.setSpanishStandardName(spanishStandardName);
      return this;
    }

    public Builder setStatusChangeDate(String statusChangeDate) {
      standardField.setStatusChangeDate(statusChangeDate);
      return this;
    }

    public Builder setRevisedDate(String revisedDate) {
      standardField.setRevisedDate(revisedDate);
      return this;
    }

    public Builder setAddedInVersion(String addedInVersion) {
      standardField.setAddedInVersion(addedInVersion);
      return this;
    }

    public Builder setWikiPageTitle(String wikiPageTitle) {
      standardField.setWikiPageTitle(wikiPageTitle);
      return this;
    }

    public Builder setWikiPageURL(String wikiPageURL) {
      standardField.setWikiPageURL(wikiPageURL);
      return this;
    }

    public Builder setWikiPageID(Integer wikiPageID) {
      standardField.setWikiPageID(wikiPageID);
      return this;
    }

    public StandardField build() {
      return standardField;
    }
  }
}
