package org.reso.commander.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.reso.certification.containers.WebAPITestContainer.EMPTY_STRING;

public class DataDictionaryMetadata {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryMetadata.class);

  public static Boolean isPrimaryKeyField(String resource, String fieldName) {
    return getKeyFieldForResource(resource).contentEquals(fieldName);
  }

  private static String keyifyResourceName(String resourceName) {
    if (resourceName == null || resourceName.trim().isEmpty()) {
      return resourceName;
    }
    return resourceName + "Key";
  }

  public static String getKeyFieldForResource(String resourceName) {
    switch (resourceName) {
      case "Property":
        return "ListingKey";
      case "Contacts":
      case "ContactListingNotes":
        return "ContactKey";
      case "InternetTracking":
        return "EventKey";
      case "InternetTrackingSummary":
        return "ListingId";
      case "OUID":
        return "OrganizationUniqueIdKey";
      case "Queue":
        return "QueueTransactionKey";
      case "PropertyGreenVerification":
        return "GreenBuildingVerificationKey";
      case "PropertyRooms":
        return "RoomKey";
      case "PropertyUnitTypes":
        return "UnitTypeKey";
      case "EntityEvent":
        return "EntityEventSequence";
      case "LockOrBox":
        return "LockOrBoxKey";
      case "MemberAssociation":
      case "OfficeAssociation":
        return "AssociationKey";
      case "MemberStateLicense":
        return "MemberStateLicenseKey";
      case "OfficeCorporateLicense":
        return "OfficeCorporateLicenseKey";
      case "TransactionManagement":
        return "TransactionKey";
      case "Rules":
        return "RuleKey";
      case "Teams":
        return "TeamKey";
      case "TeamMembers":
        return "TeamMemberKey";
      case "PropertyPowerProduction":
        return "PowerProductionKey";
      default:
        return keyifyResourceName(resourceName);
    }
  }
}
