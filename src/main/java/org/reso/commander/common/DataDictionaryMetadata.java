package org.reso.commander.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.reso.certification.containers.WebAPITestContainer.EMPTY_STRING;

public class DataDictionaryMetadata {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryMetadata.class);

  public static Boolean isPrimaryKeyField(String resource, String fieldName) {
    return getKeyFieldForResource(resource).contentEquals(fieldName);
  }

  public static String getKeyFieldForResource(String resourceName) {
    switch (resourceName) {
      case "Property":
        return "ListingKey";
      case "Member":
        return "MemberKey";
      case "Office":
        return "OfficeKey";
      case "Contacts":
      case "ContactListingNotes":
        return "ContactKey";
      case "ContactListings":
        return "ContactListingsKey";
      case "HistoryTransactional":
        return "HistoryTransactionalKey";
      case "InternetTracking":
        return "EventKey";
      case "Media":
        return "MediaKey";
      case "OpenHouse":
        return "OpenHouseKey";
      case "OUID":
        return "OrganizationUniqueIdKey";
      case "Prospecting":
        return "ProspectingKey";
      case "Queue":
        return "QueueTransactionKey";
      case "Rules":
        return "RuleKey";
      case "SavedSearch":
        return "SavedSearchKey";
      case "Showing":
        return "ShowingKey";
      case "Teams":
        return "TeamKey";
      case "TeamMembers":
        return "TeamMemberKey";
      case "OtherPhone":
        return "OtherPhoneKey";
      case "PropertyGreenVerification":
        return "GreenBuildingVerificationKey";
      case "PropertyPowerProduction":
        return "PowerProductionKey";
      case "PropertyRooms":
        return "RoomKey";
      case "PropertyUnitTypes":
        return "UnitTypeKey";
      case "SocialMedia":
        return "SocialMediaKey";
      case "Association":
        return "AssociationKey";
      case "EntityEvent":
        return "EntityEventSequence";
      case "LockOrBox":
        return "LockOrBoxKey";
      case "MemberAssociation":
        return "MemberAssociationKey";
      case "OfficeAssociation":
        return "OfficeAssociationKey";
      case "MemberStateLicenses":
        return "MemberStateLicenseKey";
      case "OfficeCorporateLicenses":
        return "OfficeCorporateLicenseKey";
      case "Field":
        return "FieldKey";
      case "Lookup":
        return "LookupKey";
      default:
        LOG.error("Cannot find key name for resource: " + resourceName);
        return EMPTY_STRING;
    }
  }

}
