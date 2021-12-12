const fs = require('fs').promises;
const { standardMeta } = require('../references/standardMeta');
const { lookupMap } = require('../references/lookupMap.js');

/**
 * Defines the bins template for stats.
 * @returns bins template with all bins initialized to 0.
 */
const getBinsTemplate = () => {
  return {
    eq0: 0,
    gt0: 0,
    gte25: 0,
    gte50: 0,
    gte75: 0,
    eq100: 0
  };
};

/**
 * Defines the totals template for stats.
 * @returns totals template with all bins initialized to 0.
 */
const getTotalsTemplate = () => {
  return { 
    total: getBinsTemplate(), 
    reso: getBinsTemplate(), 
    idx: getBinsTemplate(), 
    local: getBinsTemplate() 
  };
};

/**
 * Defines the availability template for stats. This is the structure of the processed results.
 * @returns availability template with all totals and bins initialized to 0.
 */
const getAvailabilityTemplate = () => {
  return {
    fields: [],
    lookups: [],
    lookupValues: [],
    resources: [],
    availability: {
      fields: getTotalsTemplate(),
      lookups: getTotalsTemplate(),
      resources: {},
      resourcesBinary: {}
    }
  };
};

/**
 * Builds a standard field cache from a list of standard fields.
 * @param {Array} fields an array of standard fields.
 * @returns map of all standard fields addressable by cache[resourceName][fieldName]
 *          or an empty map if there are none.
 */
const createStandardFieldCache = (fields = []) => {
  const resourceFieldCache = {};
  fields.forEach(field => {
    if (!resourceFieldCache[field.resourceName]) {
      resourceFieldCache[field.resourceName] = {};
    }
    resourceFieldCache[field.resourceName][field.fieldName] = field;
  });
  return resourceFieldCache;
};

/**
 * Builds a lookup value cache from a list of individual lookup value items.
 * @param {Array} lookupValues the lookup values to create the cache from.
 * @returns map of all lookups addressable by cache[resourceName][fieldName][lookupValue]
 *          or an empty map if there are none.
 */
const createLookupValueCache = (lookupValues = []) => {
  const resourceFieldLookupCache = {};
  lookupValues.forEach(lookupValue => {
    if (!resourceFieldLookupCache[lookupValue.resourceName]) {
      resourceFieldLookupCache[lookupValue.resourceName] = {};
    }

    if (!resourceFieldLookupCache[lookupValue.resourceName][lookupValue.fieldName]) {
      resourceFieldLookupCache[lookupValue.resourceName][lookupValue.fieldName] = {};
    }

    resourceFieldLookupCache[lookupValue.resourceName][lookupValue.fieldName][lookupValue.lookupValue] = lookupValue;
  });
  return resourceFieldLookupCache;
}

/**
 * Determines whether a given field is an IDX field.
 * TODO: The performance could be improved here in that there's a filter being done on each payloads array. 
 *       There's potential speedup if each payload were turned into a nested property rather than an array.
 * @param {String} resourceName the name of the resource for the field.
 * @param {String} fieldName the name of the field.
 * @param {Object} standardFieldCache a field cache created by createStandardFieldCache().
 * @returns true if the given field is an IDX field, false otherwise.
 */
const isIdxField = (resourceName, fieldName, standardFieldCache = {}) => resourceName && fieldName
  && isResoField(resourceName, fieldName, standardFieldCache) 
  && !!standardFieldCache[resourceName][fieldName].payloads.filter(x => x === "IDX").length > 0;

/**
 * Determines whether a given field is a RESO field.
 * @param {String} resourceName the name of the resource for the field.
 * @param {String} fieldName the name of the field.
 * @param {Object} standardFieldCache a field cache created by createStandardFieldCache().
 * @returns true if the given field is a RESO field, false otherwise.
 */
const isResoField = (resourceName, fieldName, standardFieldCache = {}) => resourceName && fieldName
  && standardFieldCache[resourceName] && !!standardFieldCache[resourceName][fieldName];


/**
 * Determines if a given lookup is a RESO lookup.
 * @param {*} resourceName the name of the resource for the field.
 * @param {*} fieldName the name of the field.
 * @param {*} lookupValue the name of the lookup to test.
 * @param {*} standardFieldCache a field cache created by createStandardFieldCache().
 * @returns the RESO lookup, if found, otherwise null.
 */
const findResoLookup = (resourceName, fieldName, lookupValue, standardFieldCache = {}) => {
  if (resourceName && fieldName && standardFieldCache[resourceName] && standardFieldCache[resourceName][fieldName]) {
    const field = standardFieldCache[resourceName][fieldName];

    if (field && field.simpleDataType.includes('String List') && field.type.includes('.')) {
      const lookupName = field.type.substring(field.type.lastIndexOf('.') + 1, field.type.length);
      const lookup = lookupMap[lookupName] && lookupMap[lookupName].find(x => x.lookupValue === lookupValue || x.lookupDisplayName === lookupValue);
      //TODO: turn the lookup map into its own inverted hash by lookup values and display names
      return lookup ? { lookupName, lookup } : null;
    }
  }
  return null;
};

/**
 * Computes availability from existing bins.
 * @param {Number} availability the current availability value.
 * @param {Object} bins existing bins containing past availability values.
 * @returns a new object following the getBinsTemplate structure that contains updated availabilities for each bin.
 */
const computeBins = (availability, bins) => {
  if (!bins) return getBinsTemplate();
  return {
    eq0: availability === 0 ? bins.eq0 + 1 : bins.eq0 || 0,
    gt0: availability > 0 ? bins.gt0 + 1 : bins.gt0 || 0,
    gte25: availability >= 0.25 ? bins.gte25 + 1 : bins.gte25 || 0,
    gte50: availability >= 0.5 ? bins.gte50 + 1 : bins.gte50 || 0,
    gte75: availability >= 0.75 ? bins.gte75 + 1 : bins.gte75 || 0,
    eq100: availability === 1 ? bins.eq100 + 1 : bins.eq100 || 0
  }
};

/**
 * Translates existing numeric bins into booleans. 
 * @param {Object} bins existing bins object.
 * @returns the resulting bins object with values transformed to booleans.
 */
const computeBooleanBins = bins => {
  const booleanBins = {};
  Object.entries(bins).forEach( ([bin, value]) => booleanBins[bin] = !!value);
  return booleanBins;
}

/**
 * Computes availability from discrete bins, meaning ones with integer values (tallies).
 * @param {Object} discreteBins bins using the getBinsTemplate() structure with integer availability values.
 * @param {Number} resourceSampleCount the count of the number of sampled records for a given resource.
 * @returns a bins object with the decimal availabilities computed.
 */
const computeAvailabilityFromDiscreteBins = (discreteBins=getBinsTemplate(), resourceSampleCount=0) => {
  if (!resourceSampleCount) return discreteBins;

  const availabilities = {};
  Object.entries(discreteBins).forEach(([binName, value]) => availabilities[binName] = 1.0 * value / resourceSampleCount);
  return availabilities;
};


/**
 * Processes a RESO Data Availability Report and creates aggregates and rollups. 
 * TODO: individual totals calculations could be tidied up a bit.
 * @param {Object} availablityReport the RESO availability report JSON to process.
 * @returns a JSON availability report with the appropriate rollups and aggregates. 
 */
const process = async availablityReport => {
  //iterate over each field and lookup and compute their availabilities
  const { resources, fields, lookups, lookupValues } = availablityReport;

  const transformed = getAvailabilityTemplate();
  const standardFieldCache = createStandardFieldCache(standardMeta.fields);

  const resourceCounts = {};
  resources.forEach(resource => resourceCounts[resource.resourceName] = resource.numRecordsFetched);

  const processedFields = [], processedLookupValues = [], lookupValueCache = createLookupValueCache(lookupValues);
  
  //binary resource availability cache
  const resourcesBinary = {};

  //process fields
  fields.forEach(field => {
    const availability = resourceCounts[field.resourceName] !== 0 ? 1.0 * field.frequency / resourceCounts[field.resourceName] : 0;
    const fieldBins = computeBins(availability, getBinsTemplate());

    //update field availability
    transformed.availability.fields.total = computeBins(availability, transformed.availability.fields.total);

    //add totals template for this resource name if it doesn't already exist
    if (!resourcesBinary[field.resourceName]) {
      resourcesBinary[field.resourceName] = { fields: getTotalsTemplate(), lookups: getTotalsTemplate() };
    }
    //update binary resource bins
    resourcesBinary[field.resourceName].fields.total = computeBins(availability, resourcesBinary[field.resourceName].fields.total);

    if (isResoField(field.resourceName, field.fieldName, standardFieldCache)) {
      //update RESO totals
      transformed.availability.fields.reso = computeBins(availability, transformed.availability.fields.reso);
      resourcesBinary[field.resourceName].fields.reso = computeBins(availability, resourcesBinary[field.resourceName].fields.reso);

      if (isIdxField(field.resourceName, field.fieldName, standardFieldCache)) {
        //update IDX totals
        transformed.availability.fields.idx = computeBins(availability, transformed.availability.fields.idx);
        resourcesBinary[field.resourceName].fields.idx = computeBins(availability, resourcesBinary[field.resourceName].fields.idx);
      }
    } else {
      //otherwise, update local totals
      transformed.availability.fields.local = computeBins(availability, transformed.availability.fields.local);
      resourcesBinary[field.resourceName].fields.local = computeBins(availability, resourcesBinary[field.resourceName].fields.local);
    }

    //only process if there are lookups for this field
    const lookupsForField = lookupValueCache[field.resourceName] && lookupValueCache[field.resourceName][field.fieldName];
    
    if (lookupsForField) {
      Object.values(lookupsForField).forEach(lookupValue => {
        if (lookupValue.lookupValue !== 'null' && lookupValue.lookupValue !== 'NULL_VALUE') {
          const lookupAvailability = !!lookupValue.frequency && !!resourceCounts[field.resourceName]
            ? 1.0 * lookupValue.frequency / resourceCounts[field.resourceName] : 0;
          
          const lookupBins = computeBins(lookupAvailability, getBinsTemplate());

          transformed.availability.lookups.total = computeBins(availability, transformed.availability.lookups.total);
          resourcesBinary[field.resourceName].lookups.total = computeBins(availability, resourcesBinary[field.resourceName].lookups.total);

          if (isResoField(lookupValue.resourceName, lookupValue.fieldName, standardFieldCache) && 
                findResoLookup(lookupValue.resourceName, lookupValue.fieldName, lookupValue.lookupValue, standardFieldCache)) {
            
            transformed.availability.lookups.reso = computeBins(availability, transformed.availability.lookups.reso);
            resourcesBinary[field.resourceName].lookups.reso = computeBins(availability, resourcesBinary[field.resourceName].lookups.reso);
            if (isIdxField(lookupValue.resourceName, lookupValue.fieldName, standardFieldCache)) {
              transformed.availability.lookups.idx = computeBins(availability, transformed.availability.lookups.idx);
              resourcesBinary[field.resourceName].lookups.idx = computeBins(availability, resourcesBinary[field.resourceName].lookups.idx);
            }
          
          } else {
            transformed.availability.lookups.local = computeBins(availability, transformed.availability.lookups.local);
            resourcesBinary[field.resourceName].lookups.local = computeBins(availability, resourcesBinary[field.resourceName].lookups.local);
          }

          processedLookupValues.push({ ...lookupValue, lookupAvailability, ...computeBooleanBins(lookupBins) });
        }
      });
    }

    if (!!field) {
      processedFields.push({ ...field, availability, ...computeBooleanBins(fieldBins) });
    }
  });

  transformed.resources = resources;
  transformed.fields = processedFields;
  transformed.lookups = lookups;
  transformed.lookupValues = processedLookupValues;
  transformed.availability.resourcesBinary = resourcesBinary;

  //compute resource availability rollups from the discrete bins
  const resourceAvailability = {};
  transformed.availability.resources = Object.entries(resourcesBinary).forEach(([resourceName, value]) => {
    if (!resourceAvailability[resourceName]) resourceAvailability[resourceName] = {};

    const { fields = getTotalsTemplate(), lookups = getTotalsTemplate() } = value;
    const resourceCount = resourceCounts[resourceName] || 0;

    resourceAvailability[resourceName].fields = {
      total: computeAvailabilityFromDiscreteBins(fields.total, resourceCount),
      reso: computeAvailabilityFromDiscreteBins(fields.reso, resourceCount),
      idx: computeAvailabilityFromDiscreteBins(fields.idx, resourceCount),
      local: computeAvailabilityFromDiscreteBins(fields.local, resourceCount)
    };

    resourceAvailability[resourceName].lookups = {
      total: computeAvailabilityFromDiscreteBins(lookups.total, resourceCount),
      reso: computeAvailabilityFromDiscreteBins(lookups.reso, resourceCount),
      idx: computeAvailabilityFromDiscreteBins(lookups.idx, resourceCount),
      local: computeAvailabilityFromDiscreteBins(lookups.local, resourceCount)
    };
  });

  transformed.availability.resources = resourceAvailability;

  return transformed;
}

/**
 * Processes a RESO data availability report at the given path and writes it to a local file
 * in the current path called 'availability-processed.json'.
 * @param {String} pathToDataAvailabilityReport the path to the data availability report to process.
 */
 const processDataAvailabilityReport = async pathToDataAvailabilityReport => {
  try {
    const availablityReport = JSON.parse(await fs.readFile(pathToDataAvailabilityReport, 'utf8'));
    const startTime = new Date();
    await fs.writeFile('./availability-processed.json', JSON.stringify(await process(availablityReport)));
    console.log("Time taken: ", new Date() - startTime, "ms");
  } catch (err) {
    console.error(err);
  }
}

module.exports = {
  processDataAvailabilityReport
};