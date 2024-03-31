package org.reso.commander.common;

public class DataDictionaryMetadata {
  public static Boolean isPrimaryKeyField(String resource, String fieldName) {
    return getKeyFieldForResource(resource).contentEquals(fieldName);
  }

  private static String keyifyResourceName(String resourceName) {
    if (resourceName == null || resourceName.trim().isEmpty()) {
      return "<Undefined Resource Name>";
    }
    return resourceName.trim() + "Key";
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
      case "MemberAssociation":
      case "OfficeAssociation":
        return "AssociationKey";
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
      case "PropertyPowerStorage":
        return "PowerStorageKey";
      default:
        return keyifyResourceName(resourceName);
    }
  }
}
