package org.reso.models;

import java.util.List;

public class ReferenceStandardLookup {
  private String lookupField;
  private String lookupValue;
  private String lookupDisplayName;
  private String definition;
  List<String> lookupDisplayNameSynonyms;
  private String bedes;
  private List<String> references;
  private String elementStatus;
  private Integer lookupId;
  private Integer lookupFieldId;
  private String spanishLookupField;
  private String spanishLookupValue;
  private String statusChangeDate;
  private String revisedDate;
  private String addedInVersion;
  private String wikiPageTitle;
  private String wikiPageUrl;

  private ReferenceStandardLookup() {
    //default constructor is private, use Builder instead
  }

  public String getLookupField() {
    return lookupField;
  }

  public String getLookupValue() {
    return lookupValue;
  }

  public String getLookupDisplayName() {
    return lookupDisplayName;
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

  public Integer getLookupId() {
    return lookupId;
  }

  public Integer getLookupFieldId() {
    return lookupFieldId;
  }

  public String getSpanishLookupField() {
    return spanishLookupField;
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

    public Builder setLookupField(String lookupField) {
      referenceStandardLookup.lookupField = lookupField;
      return this;
    }

    public Builder setLookupValue(String lookupValue) {
      referenceStandardLookup.lookupValue = lookupValue;
      return this;
    }

    public Builder setLookupDisplayName(String lookupDisplayName) {
      referenceStandardLookup.lookupDisplayName = lookupDisplayName;
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

    public Builder setLookupId(Integer lookupId) {
      referenceStandardLookup.lookupId = lookupId;
      return this;
    }

    public Builder setLookupFieldId(Integer lookupFieldId) {
      referenceStandardLookup.lookupFieldId = lookupFieldId;
      return this;
    }

    public Builder setSpanishLookupField(String spanishLookupField) {
      referenceStandardLookup.spanishLookupField = spanishLookupField;
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
