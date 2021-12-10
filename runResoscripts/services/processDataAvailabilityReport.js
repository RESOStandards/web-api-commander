const fs = require('fs').promises;
const { standardMeta } = require('../references/standardMeta');
const { lookupMap } = require('../references/lookupMap.js');

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

const getTotalsTemplate = () => {
  return { total: getBinsTemplate(), reso: getBinsTemplate(), idx: getBinsTemplate(), total: getBinsTemplate() };
};

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

const isIdxField = (resourceName, fieldName, standardFieldCache = {}) => resourceName && fieldName
  && isResoField(resourceName, fieldName, standardFieldCache) && !!standardFieldCache[resourceName][fieldName].payloads.filter(x => x === "IDX").length > 0;

const isResoField = (resourceName, fieldName, standardFieldCache = {}) => resourceName && fieldName
  && standardFieldCache[resourceName] && !!standardFieldCache[resourceName][fieldName];

const findResoLookup = (resourceName, fieldName, lookupValue, standardFieldCache = {}) => {
  if (resourceName && fieldName && standardFieldCache[resourceName] && standardFieldCache[resourceName][fieldName]) {
    const field = standardFieldCache[resourceName][fieldName];

    if (field.simpleDataType.includes('String List') && field.type.includes('.')) {
      const lookupName = field.type.substring(field.type.lastIndexOf('.') + 1, field.type.length);
      const lookup = lookupMap[lookupName] && lookupMap[lookupName].find(x => x.lookupValue === lookupValue || x.lookupDisplayName === lookupValue);
      //TODO: turn the lookup map into its own inverted hash by lookup values and display names
      return lookup ? { lookupName, lookup } : null;
    }
  }
  return null;
};

const processDataAvailabilityReport = async pathToDataAvailabilityReport => {
  const startTime = new Date();
  try {
    const availablityReport = JSON.parse(await fs.readFile(pathToDataAvailabilityReport, 'utf8'));
    await fs.writeFile('./availability-processed.json', JSON.stringify(await process(availablityReport)));
    console.log("Time taken: ", new Date() - startTime, "ms");
  } catch (err) {
    console.error(err);
  }
}

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

const computeBooleanBins = bins => {
  const booleanBins = {};
  Object.entries(bins).forEach( ([bin, value]) => booleanBins[bin] = !!value);
  return booleanBins;
}

const computeAvailabilityFromDiscreteBins = (discreteBins=getBinsTemplate(), resourceSampleCount=0) => {
  if (!resourceSampleCount) return discreteBins;

  const availabilities = {};
  Object.entries(discreteBins).forEach(([binName, value]) => availabilities[binName] = 1.0 * value / resourceSampleCount);
  return availabilities;
};

const process = async availablityReport => {
  //iterate over each field and lookup and compute their availabilities
  const { resources, fields, lookups, lookupValues } = availablityReport;

  const transformed = getAvailabilityTemplate();
  const standardFieldCache = createStandardFieldCache(standardMeta.fields);

  const resourceCounts = {};
  resources.forEach(resource => resourceCounts[resource.resourceName] = resource.numRecordsFetched);

  const processedLookupValues = [], lookupValueCache = createLookupValueCache(lookupValues);
  
  //binary resource availability cache
  const resourcesBinary = {};

  //process fields
  const processedFields = fields.map(field => {
    const availability = resourceCounts[field.resourceName] !== 0 ? 1.0 * field.frequency / resourceCounts[field.resourceName] : 0;

    const fieldBins = computeBins(availability, getBinsTemplate());

    //update field availability
    transformed.availability.fields.total = computeBins(availability, transformed.availability.fields.total);

    //update binary resource bins
    if (!resourcesBinary[field.resourceName]) resourcesBinary[field.resourceName] = { fields: getTotalsTemplate(), lookups: getTotalsTemplate() };
    resourcesBinary[field.resourceName].fields.total = computeBins(availability, resourcesBinary[field.resourceName].fields.total);


    if (isResoField(field.resourceName, field.fieldName, standardFieldCache)) {
      transformed.availability.fields.reso = computeBins(availability, transformed.availability.fields.reso);
      resourcesBinary[field.resourceName].fields.reso = computeBins(availability, resourcesBinary[field.resourceName].fields.reso);

      if (isIdxField(field.resourceName, field.fieldName, standardFieldCache)) {
        transformed.availability.fields.total = computeBins(availability, transformed.availability.fields.total);
        resourcesBinary[field.resourceName].fields.idx = computeBins(availability, resourcesBinary[field.resourceName].fields.idx);
      }
    } else {
      transformed.availability.fields.local = computeBins(availability, transformed.availability.fields.local);
      resourcesBinary[field.resourceName].fields.local = computeBins(availability, resourcesBinary[field.resourceName].fields.local);
    }

    //process lookup values
    const lookupsForField = lookupValueCache[field.resourceName] && lookupValueCache[field.resourceName][field.fieldName];
    if (lookupsForField) {
      
      processedLookupValues.push(Object.values(lookupsForField).map(lookupValue => {
        if (lookupValue.lookupValue !== 'null' && lookupValue.lookupValue !== 'NULL_VALUE') {
          const lookupAvailability = !!lookupValue.frequency && !!resourceCounts[field.resourceName]
            ? 1.0 * lookupValue.frequency / resourceCounts[field.resourceName] : 0;
          
          const lookupBins = computeBins(lookupAvailability, getBinsTemplate());

          transformed.availability.lookups.total = computeBins(availability, transformed.availability.lookups.total);
          resourcesBinary[field.resourceName].lookups.total = computeBins(availability, resourcesBinary[field.resourceName].lookups.total);

          if (findResoLookup(lookupValue.resourceName, lookupValue.fieldName, lookupValue.lookupValue, standardFieldCache)) {
            if (isResoField(lookupValue.resourceName, lookupValue.fieldName, standardFieldCache)) {
              transformed.availability.lookups.reso = computeBins(availability, transformed.availability.lookups.reso);
              resourcesBinary[field.resourceName].lookups.reso = computeBins(availability, resourcesBinary[field.resourceName].lookups.reso);
              if (isIdxField(lookupValue.resourceName, lookupValue.fieldName, standardFieldCache)) {
                transformed.availability.lookups.idx = computeBins(availability, transformed.availability.lookups.idx);
                resourcesBinary[field.resourceName].lookups.idx = computeBins(availability, resourcesBinary[field.resourceName].lookups.idx);
              }
            }
          } else {
            transformed.availability.lookups.local = computeBins(availability, transformed.availability.lookups.local);
            resourcesBinary[field.resourceName].lookups.local = computeBins(availability, resourcesBinary[field.resourceName].lookups.local);
          }
          return { ...lookupValue, lookupAvailability, ...computeBooleanBins(lookupBins) };
        }
      }));
    }

    return { ...field, availability, ...computeBooleanBins(fieldBins) };
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



module.exports = {
  processDataAvailabilityReport
};