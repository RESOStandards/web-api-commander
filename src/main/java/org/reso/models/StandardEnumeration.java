package org.reso.models;

import java.util.List;

public class StandardEnumeration {
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

  private StandardEnumeration() {
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
    StandardEnumeration standardEnumeration = new StandardEnumeration();

    public Builder setLookupField(String lookupField) {
      standardEnumeration.lookupField = lookupField;
      return this;
    }

    public Builder setLookupValue(String lookupValue) {
      standardEnumeration.lookupValue = lookupValue;
      return this;
    }

    public Builder setLookupDisplayName(String lookupDisplayName) {
      standardEnumeration.lookupDisplayName = lookupDisplayName;
      return this;
    }

    public Builder setDefinition(String definition) {
      standardEnumeration.definition = definition;
      return this;
    }

    public Builder setLookupDisplayNameSynonyms(List<String> lookupDisplayNameSynonyms) {
      standardEnumeration.lookupDisplayNameSynonyms = lookupDisplayNameSynonyms;
      return this;
    }

    public Builder setBedes(String bedes) {
      standardEnumeration.bedes = bedes;
      return this;
    }

    public Builder setReferences(List<String> references) {
      standardEnumeration.references = references;
      return this;
    }

    public Builder setElementStatus(String elementStatus) {
      standardEnumeration.elementStatus = elementStatus;
      return this;
    }

    public Builder setLookupId(Integer lookupId) {
      standardEnumeration.lookupId = lookupId;
      return this;
    }

    public Builder setLookupFieldId(Integer lookupFieldId) {
      standardEnumeration.lookupFieldId = lookupFieldId;
      return this;
    }

    public Builder setSpanishLookupField(String spanishLookupField) {
      standardEnumeration.spanishLookupField = spanishLookupField;
      return this;
    }

    public Builder setSpanishLookupValue(String spanishLookupValue) {
      standardEnumeration.spanishLookupValue = spanishLookupValue;
      return this;
    }

    public Builder setStatusChangeDate(String statusChangeDate) {
      standardEnumeration.statusChangeDate = statusChangeDate;
      return this;
    }

    public Builder setRevisedDate(String revisedDate) {
      standardEnumeration.revisedDate = revisedDate;
      return this;
    }

    public Builder setAddedInVersion(String addedInVersion) {
      standardEnumeration.addedInVersion = addedInVersion;
      return this;
    }

    public Builder setWikiPageTitle(String wikiPageTitle) {
      standardEnumeration.wikiPageTitle = wikiPageTitle;
      return this;
    }

    public Builder setWikiPageUrl(String wikiPageUrl) {
      standardEnumeration.wikiPageUrl = wikiPageUrl;
      return this;
    }

    public StandardEnumeration build() {
      return standardEnumeration;
    }
  }
}
