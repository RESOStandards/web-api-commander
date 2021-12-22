package org.reso.models;

import java.util.Date;

public class LookupResourceItem {
  private final String lookupKey;
  private final String lookupName;
  private final String lookupValue;
  private final String lookupStandardName;
  private final String legacyODataValue;
  private final Date modificationTimestamp;

  public LookupResourceItem(String lookupKey, String lookupName, String lookupValue,
                            String lookupStandardName, String legacyODataValue, Date modificationTimestamp) {
    this.lookupKey = lookupKey;
    this.lookupName = lookupName;
    this.lookupValue = lookupValue;
    this.lookupStandardName = lookupStandardName;
    this.legacyODataValue = legacyODataValue;
    this.modificationTimestamp = modificationTimestamp;
  }

  public String getLookupKey() {
    return lookupKey;
  }

  public String getLookupName() {
    return lookupName;
  }

  public String getLookupValue() {
    return lookupValue;
  }

  public String getLookupStandardName() {
    return lookupStandardName;
  }

  public String getLegacyODataValue() {
    return legacyODataValue;
  }

  public Date getModificationTimestamp() {
    return modificationTimestamp;
  }
}