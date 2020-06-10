package org.reso.models;

import java.util.List;

public class DataDictionaryRow {
  private String standardName;
  private String displayName;
  private String parentResourceName;
  private String definition;
  private List<String> groups;
  private String simpleDataType;
  private Integer suggestedMaxLength;
  private String synonym;
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
  private String payloads;
  private String spanishStandardName;
  private String statusChangeDate;
  private String revisedDate;
  private String addedInVersion;
  private String wikiPageTitle;
  private String wikiPageURL;
  private Integer wikiPageID;


  public static class Builder {
    DataDictionaryRow dataDictionaryRow = new DataDictionaryRow();

    public Builder setStandardName(String standardName) {
      dataDictionaryRow.setStandardName(standardName);
      return this;
    }

    public Builder setDisplayName(String displayName) {
      dataDictionaryRow.setDisplayName(displayName);
      return this;
    }

    public Builder setParentResourceName(String parentResourceName) {
      dataDictionaryRow.setParentResourceName(parentResourceName);
      return this;
    }

    public Builder setDefinition(String definition) {
      dataDictionaryRow.setDefinition(definition);
      return this;
    }

    public Builder setGroups(List<String> groups) {
      dataDictionaryRow.setGroups(groups);
      return this;
    }

    public Builder setSimpleDataType(String dataType) {
      dataDictionaryRow.setSimpleDataType(dataType);
      return this;
    }

    public Builder setSuggestedMaxLength(Integer suggestedMaxLength) {
      dataDictionaryRow.setSuggestedMaxLength(suggestedMaxLength);
      return this;
    }

    public Builder setSynonym(String synonym) {
      dataDictionaryRow.setSynonym(synonym);
      return this;
    }

    public Builder setElementStatus(String status) {
      dataDictionaryRow.setElementStatus(status);
      return this;
    }

    public Builder setBedes(String bedes) {
      dataDictionaryRow.setBedes(bedes);
      return this;
    }

    public Builder setCertificationLevel(String certificationLevel) {
      dataDictionaryRow.setCertificationLevel(certificationLevel);
      return this;
    }

    public Builder setRecordId(Integer recordId) {
      dataDictionaryRow.setRecordId(recordId);
      return this;
    }

    public Builder setLookup(String lookup) {
      dataDictionaryRow.setLookup(lookup);
      return this;
    }

    public Builder setLookupStatus(String lookupStatus) {
      dataDictionaryRow.setLookupStatus(lookupStatus);
      return this;
    }

    public Builder setCollection(String collection) {
      dataDictionaryRow.setCollection(collection);
      return this;
    }

    public Builder setSuggestedMaxPrecision(Integer suggestedMaxPrecision) {
      dataDictionaryRow.setSuggestedMaxPrecision(suggestedMaxPrecision);
      return this;
    }

    public Builder setRepeatingElement(Boolean isRepeatingElement) {
      dataDictionaryRow.setRepeatingElement(isRepeatingElement);
      return this;
    }

    public Builder setPropertyType(List<String> propertyTypes) {
      dataDictionaryRow.setPropertyTypes(propertyTypes);
      return this;
    }

    public Builder setPayloads(String payloads) {
      dataDictionaryRow.setPayloads(payloads);
      return this;
    }

    public Builder setSpanishStandardName(String spanishStandardName) {
      dataDictionaryRow.setSpanishStandardName(spanishStandardName);
      return this;
    }

    public Builder setStatusChangeDate(String statusChangeDate) {
      dataDictionaryRow.setStatusChangeDate(statusChangeDate);
      return this;
    }

    public Builder setRevisedDate(String revisedDate) {
      dataDictionaryRow.setRevisedDate(revisedDate);
      return this;
    }

    public Builder setAddedInVersion(String addedInVersion) {
      dataDictionaryRow.setAddedInVersion(addedInVersion);
      return this;
    }

    public Builder setWikiPageTitle(String wikiPageTitle) {
      dataDictionaryRow.setWikiPageTitle(wikiPageTitle);
      return this;
    }

    public Builder setWikiPageURL(String wikiPageURL) {
      dataDictionaryRow.setWikiPageURL(wikiPageURL);
      return this;
    }

    public Builder setWikiPageID(Integer wikiPageID) {
      dataDictionaryRow.setWikiPageID(wikiPageID);
      return this;
    }

    public DataDictionaryRow build() {
      return dataDictionaryRow;
    }
  }

  private DataDictionaryRow() { /* private constructor use Builder instead */ }

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

  public String getParentResourceName() {
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
    this.groups = groups;
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

  public String getSynonym() {
    return synonym;
  }

  public void setSynonym(String synonym) {
    this.synonym = synonym;
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
    this.propertyTypes = propertyTypes;
  }

  public String getPayloads() {
    return payloads;
  }

  public void setPayloads(String payloads) {
    this.payloads = payloads;
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
}
