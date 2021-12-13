package org.reso.commander.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.reso.certification.containers.WebAPITestContainer.EMPTY_STRING;
import static org.reso.commander.common.DataDictionaryMetadata.v1_7.WELL_KNOWN_RESOURCE_KEYS.*;

public class DataDictionaryMetadata {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryMetadata.class);

  public static final class v1_7 {
    //TODO: clean up
    public static final Set<String> WELL_KNOWN_RESOURCES = new LinkedHashSet<>(Arrays.asList(
        PROPERTY,
        MEMBER,
        OFFICE,
        CONTACTS,
        CONTACT_LISTINGS,
        HISTORY_TRANSACTIONAL,
        INTERNET_TRACKING,
        MEDIA,
        OPEN_HOUSE,
        OUID,
        PROSPECTING,
        QUEUE,
        RULES,
        SAVED_SEARCH,
        SHOWING,
        TEAMS,
        TEAM_MEMBERS,
        CONTACT_LISTING_NOTES,
        OTHER_PHONE,
        PROPERTY_GREEN_VERIFICATION,
        PROPERTY_POWER_PRODUCTION,
        PROPERTY_ROOMS,
        PROPERTY_UNIT_TYPES,
        SOCIAL_MEDIA,
        FIELD,
        LOOKUP
    ));
    public static final String LOOKUP_FIELDS_AND_VALUES = "Lookup Fields and Values";

    //TODO: clean up
    public static class WELL_KNOWN_RESOURCE_KEYS {
      public static final String
          PROPERTY = "Property",
          MEMBER = "Member",
          OFFICE = "Office",
          CONTACTS = "Contacts",
          CONTACT_LISTINGS = "ContactListings",
          HISTORY_TRANSACTIONAL = "HistoryTransactional",
          INTERNET_TRACKING = "InternetTracking",
          MEDIA = "Media",
          OPEN_HOUSE = "OpenHouse",
          OUID = "OUID",
          PROSPECTING = "Prospecting",
          QUEUE = "Queue",
          RULES = "Rules",
          SAVED_SEARCH = "SavedSearch",
          SHOWING = "Showing",
          TEAMS = "Teams",
          TEAM_MEMBERS = "TeamMembers",
          CONTACT_LISTING_NOTES = "ContactListingNotes",
          OTHER_PHONE = "OtherPhone",
          PROPERTY_GREEN_VERIFICATION = "PropertyGreenVerification",
          PROPERTY_POWER_PRODUCTION = "PropertyPowerProduction",
          PROPERTY_ROOMS = "PropertyRooms",
          PROPERTY_UNIT_TYPES = "PropertyUnitTypes",
          SOCIAL_MEDIA = "SocialMedia",
          FIELD = "Field",
          LOOKUP = "Lookup";
    }

    public static Boolean isPrimaryKeyField(String resource, String fieldName) {
      return getKeyFieldForResource(resource).contentEquals(fieldName);
    }

    public static String getKeyFieldForResource(String resourceName) {
      switch (resourceName) {
        case PROPERTY:
          return "ListingKey";
        case MEMBER:
          return "MemberKey";
        case OFFICE:
          return "OfficeKey";
        case CONTACTS:
        case CONTACT_LISTING_NOTES:
          return "ContactKey";
        case CONTACT_LISTINGS:
          return "ContactListingsKey";
        case HISTORY_TRANSACTIONAL:
          return "HistoryTransactionalKey";
        case INTERNET_TRACKING:
          return "EventKey";
        case MEDIA:
          return "MediaKey";
        case OPEN_HOUSE:
          return "OpenHouseKey";
        case OUID:
          return "OrganizationUniqueIdKey";
        case PROSPECTING:
          return "ProspectingKey";
        case QUEUE:
          return "QueueTransactionKey";
        case RULES:
          return "RuleKey";
        case SAVED_SEARCH:
          return "SavedSearchKey";
        case SHOWING:
          return "ShowingKey";
        case TEAMS:
          return "TeamKey";
        case TEAM_MEMBERS:
          return "TeamMemberKey";
        case OTHER_PHONE:
          return "OtherPhoneKey";
        case PROPERTY_GREEN_VERIFICATION:
          return "GreenBuildingVerificationKey";
        case PROPERTY_POWER_PRODUCTION:
          return "PowerProductionKey";
        case PROPERTY_ROOMS:
          return "RoomKey";
        case PROPERTY_UNIT_TYPES:
          return "UnitTypeKey";
        case SOCIAL_MEDIA:
          return "SocialMediaKey";
        case FIELD:
          return "FieldKey";
        case LOOKUP:
          return "LookupKey";
        default:
          LOG.error("Cannot find key name for resource: " + resourceName);
          return EMPTY_STRING;
      }
    }

    public static Boolean isPrimaryKeyNumericField(String resource, String fieldName) {
      return getKeyNumericFieldForResource(resource).contentEquals(fieldName);
    }

    public static String getKeyNumericFieldForResource(String resourceName) {
      switch (resourceName) {
        case PROPERTY:
          return "ListingKeyNumeric";
        case MEMBER:
          return "MemberKeyNumeric";
        case OFFICE:
          return "OfficeKeyNumeric";
        case CONTACTS:
        case CONTACT_LISTING_NOTES:
          return "ContactKeyNumeric";
        case CONTACT_LISTINGS:
          return "ContactListingsKeyNumeric";
        case HISTORY_TRANSACTIONAL:
          return "HistoryTransactionalKeyNumeric";
        case INTERNET_TRACKING:
          return "EventKeyNumeric";
        case MEDIA:
          return "MediaKeyNumeric";
        case OPEN_HOUSE:
          return "OpenHouseKeyNumeric";
        case OUID:
          return "OrganizationUniqueIdKeyNumeric";
        case PROSPECTING:
          return "ProspectingKeyNumeric";
        case QUEUE:
          return "QueueTransactionKeyNumeric";
        case RULES:
          return "RuleKeyNumeric";
        case SAVED_SEARCH:
          return "SavedSearchKeyNumeric";
        case SHOWING:
          return "ShowingKeyNumeric";
        case TEAMS:
          return "TeamKeyNumeric";
        case TEAM_MEMBERS:
          return "TeamMemberKeyNumeric";
        case OTHER_PHONE:
          return "OtherPhoneKeyNumeric";
        case PROPERTY_GREEN_VERIFICATION:
          return "GreenBuildingVerificationKeyNumeric";
        case PROPERTY_POWER_PRODUCTION:
          return "PowerProductionKeyNumeric";
        case PROPERTY_ROOMS:
          return "RoomKeyNumeric";
        case PROPERTY_UNIT_TYPES:
          return "UnitTypeKeyNumeric";
        case SOCIAL_MEDIA:
          return "SocialMediaKeyNumeric";
        default:
          LOG.error("Cannot find key name for resource: " + resourceName);
          return EMPTY_STRING;
      }
    }
  }
}
