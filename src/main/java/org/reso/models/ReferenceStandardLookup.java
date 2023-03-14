package org.reso.models;

import java.util.List;

public class ReferenceStandardLookup {
  private String lookupName;
  private String lookupValue;
  private String legacyODataValue;
  private String definition;
  List<String> lookupDisplayNameSynonyms;
  private String bedes;
  private List<String> references;
  private String elementStatus;
  private String lookupId;
  private String lookupNameId;
  private String frenchCanadianLookupValue;
  private String spanishLookupValue;
  private String statusChangeDate;
  private String revisedDate;
  private String addedInVersion;
  private String wikiPageTitle;
  private String wikiPageUrl;

  private ReferenceStandardLookup() {
    //default constructor is private, use Builder instead
  }

  public String getLookupName() {
    return lookupName;
  }

  public String getLookupValue() {
    return lookupValue;
  }

  public String getLegacyODataValue() {
    return legacyODataValue;
  }

  public String getDefinition() {
    return definition;
  }

  public List<String> getLookupDisplayNameSynonyms() {
    return lookupDisplayNameSynonyms;
  }

  public String getBedes() {
    return bedes;
  }

  public List<String> getReferences() {
    return references;
  }

  public String getElementStatus() {
    return elementStatus;
  }

  public String getLookupId() {
    return lookupId;
  }

  public String getLookupNameId() {
    return lookupNameId;
  }

  public String getFrenchCanadianLookupValue() {
    return frenchCanadianLookupValue;
  }

  public String getSpanishLookupValue() {
    return spanishLookupValue;
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

  public static class Builder {
    ReferenceStandardLookup referenceStandardLookup = new ReferenceStandardLookup();

    public Builder setLookupName(String lookupName) {
      referenceStandardLookup.lookupName = lookupName;
      return this;
    }

    public Builder setLookupValue(String lookupValue) {
      referenceStandardLookup.lookupValue = lookupValue;
      return this;
    }

    public Builder setLegacyODataValue(String legacyODataValue) {
      referenceStandardLookup.legacyODataValue = legacyODataValue;
      return this;
    }

    public Builder setDefinition(String definition) {
      referenceStandardLookup.definition = definition;
      return this;
    }

    public Builder setLookupDisplayNameSynonyms(List<String> lookupDisplayNameSynonyms) {
      referenceStandardLookup.lookupDisplayNameSynonyms = lookupDisplayNameSynonyms;
      return this;
    }

    public Builder setBedes(String bedes) {
      referenceStandardLookup.bedes = bedes;
      return this;
    }

    public Builder setReferences(List<String> references) {
      referenceStandardLookup.references = references;
      return this;
    }

    public Builder setElementStatus(String elementStatus) {
      referenceStandardLookup.elementStatus = elementStatus;
      return this;
    }

    public Builder setLookupId(String lookupId) {
      referenceStandardLookup.lookupId = lookupId;
      return this;
    }

    public Builder setLookupNameId(String lookupNameId) {
      referenceStandardLookup.lookupNameId = lookupNameId;
      return this;
    }

    public Builder setFrenchCanadianLookupValue(String frenchCanadianLookupValue) {
      referenceStandardLookup.frenchCanadianLookupValue = frenchCanadianLookupValue;
      return this;
    }

    public Builder setSpanishLookupValue(String spanishLookupValue) {
      referenceStandardLookup.spanishLookupValue = spanishLookupValue;
      return this;
    }

    public Builder setStatusChangeDate(String statusChangeDate) {
      referenceStandardLookup.statusChangeDate = statusChangeDate;
      return this;
    }

    public Builder setRevisedDate(String revisedDate) {
      referenceStandardLookup.revisedDate = revisedDate;
      return this;
    }

    public Builder setAddedInVersion(String addedInVersion) {
      referenceStandardLookup.addedInVersion = addedInVersion;
      return this;
    }

    public Builder setWikiPageTitle(String wikiPageTitle) {
      referenceStandardLookup.wikiPageTitle = wikiPageTitle;
      return this;
    }

    public Builder setWikiPageUrl(String wikiPageUrl) {
      referenceStandardLookup.wikiPageUrl = wikiPageUrl;
      return this;
    }

    public ReferenceStandardLookup build() {
      return referenceStandardLookup;
    }
  }
}
