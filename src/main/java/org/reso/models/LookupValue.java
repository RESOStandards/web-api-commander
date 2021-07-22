package org.reso.models;

import java.util.Objects;

public final class LookupValue {
  private final String resourceName;
  private final String fieldName;
  private final String lookupValue;

  public LookupValue(String resourceName, String fieldName, String lookupValue) {
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.lookupValue = lookupValue;
  }

  public String getResourceName() {
    return resourceName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getLookupValue() {
    return lookupValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LookupValue that = (LookupValue) o;
    return resourceName.equals(that.resourceName) &&
        fieldName.equals(that.fieldName) &&
        lookupValue.equals(that.lookupValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceName, fieldName, lookupValue);
  }
}
