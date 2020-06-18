package org.reso.commander.common;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.reso.commander.common.DataDictionaryMetadata.v1_7.WELL_KNOWN_KEYS.*;
import static org.reso.commander.common.DataDictionaryMetadata.v1_7.WELL_KNOWN_KEYS.SOCIAL_MEDIA;

public class DataDictionaryMetadata {
    public static final class v1_7 {
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
                SOCIAL_MEDIA
        ));
        public static final String LOOKUP_FIELDS_AND_VALUES = "Lookup Fields and Values";

        public static class WELL_KNOWN_KEYS {
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
                    SOCIAL_MEDIA = "SocialMedia";
        }
    }
}
