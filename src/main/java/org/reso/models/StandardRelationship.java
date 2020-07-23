package org.reso.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;

import java.util.stream.Stream;

public class StandardRelationship {
  private static final Logger LOG = LogManager.getLogger(StandardRelationship.class);
  private String targetResource;
  private String targetResourceKey;
  private String targetStandardName;
  private Cardinality cardinality;
  private String sourceResource;
  private String sourceResourceKey;

  private StandardRelationship() { }

  @Override
  public String toString() {
    return "StandardRelationship{" +
        "targetResource='" + targetResource + '\'' +
        ", targetResourceKey='" + targetResourceKey + '\'' +
        ", targetStandardName='" + targetStandardName + '\'' +
        ", cardinality=" + cardinality +
        ", sourceResource='" + sourceResource + '\'' +
        ", sourceResourceKey='" + sourceResourceKey + '\'' +
        '}';
  }

  public static class Builder {
    public static StandardRelationship build(String targetResource, String targetResourceKey, String targetStandardName, Cardinality cardinality, String sourceResource, String sourceResourceKey) {

      assert(targetResource != null && targetStandardName != null && cardinality != null && sourceResource != null && sourceResourceKey != null);

      StandardRelationship standardRelationship = new StandardRelationship();

      standardRelationship.setTargetResource(targetResource);
      //targetResourceKey is not required, this is just here as a convenience. Pass null if there's no targetResourceKey
      standardRelationship.setTargetResourceKey(targetResourceKey);
      standardRelationship.setTargetStandardName(targetStandardName);
      standardRelationship.setCardinality(cardinality);
      standardRelationship.setSourceResource(sourceResource);
      standardRelationship.setSourceResourceKey(sourceResourceKey);

      //LOG.info(standardRelationship);
      return standardRelationship;
    }
  }

  public String getTargetResource() {
    return targetResource;
  }

  public void setTargetResource(String targetResource) {
    this.targetResource = targetResource;
  }

  public String getTargetResourceKey() {
    return targetResourceKey;
  }

  public void setTargetResourceKey(String targetResourceKey) {
    //only allow a value or null, and ensure null in the model if blank
    this.targetResourceKey = targetResourceKey != null && targetResourceKey.length() > 0 ? targetResourceKey : null;
  }

  public String getTargetStandardName() {
    return targetStandardName;
  }

  public void setTargetStandardName(String targetStandardName) {
    this.targetStandardName = targetStandardName;
  }

  public Cardinality getCardinality() {
    return cardinality;
  }

  public void setCardinality(Cardinality cardinality) {
    this.cardinality = cardinality;
  }

  public String getSourceResource() {
    return sourceResource;
  }

  public void setSourceResource(String sourceResource) {
    this.sourceResource = sourceResource;
  }

  public String getSourceResourceKey() {
    return sourceResourceKey;
  }

  public void setSourceResourceKey(String sourceResourceKey) {
    this.sourceResourceKey = sourceResourceKey;
  }

  /**
   * Enumerates possible relationship types
   * TODO: add similar for allowed resources from an allowed resources string
   */
  public enum Cardinality {
    ONE_TO_ONE("one-to-one"),
    ONE_TO_MANY("one-to-many"),
    MANY_TO_ONE("many-to-one"),
    MANY_TO_MANY("many-to-many");

    private String relationshipType;

    Cardinality(String relationshipType) {
      this.relationshipType = relationshipType;
    }

    public static Stream<Cardinality> stream() {
      return Stream.of(Cardinality.values());
    }

    public String getRelationshipType() {
      return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
      this.relationshipType = relationshipType;
    }
  };

  public static StandardRelationship deserializeRow(Row row) {
    return null;
  }
}
