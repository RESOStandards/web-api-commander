const fs = require('fs').promises;
const { standardMeta } = require('../references/standardMeta');
const { lookupMap } = require('../references/lookupMap.js');

//TODO: move to consts file
const 
  BINS_TEMPLATE = {
    eq0: 0,
    gt0: 0,
    gte25: 0,
    gte50: 0,
    gte75: 0,
    eq100: 0
  },
  AVAILABILITY_TEMPLATE = {
  fields: [],
  lookups: [],
  lookupValues: [],
  resources: [],
  availability: {
    fields: {
      total: BINS_TEMPLATE,
      reso: BINS_TEMPLATE,
      local: BINS_TEMPLATE,
      idx: BINS_TEMPLATE
    },
    lookups: {
      total: BINS_TEMPLATE,
      reso: BINS_TEMPLATE,
      local: BINS_TEMPLATE,
      idx: BINS_TEMPLATE
    },
    resources: {},
    resourcesBinary: {}
  }
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
      return lookup ? {lookupName, lookup} : null;
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
  if (!bins) return BINS_TEMPLATE;
  return {
    eq0: availability === 0 ? bins.eq0 + 1 : bins.eq0 || 0,
    gt0: availability > 0 ? bins.gt0 + 1 : bins.gt0 || 0,
    gte25: availability >= 0.25 ? bins.gte25 + 1 : bins.gte25 || 0,
    gte50: availability >= 0.5 ? bins.gte50 + 1 : bins.gte50 || 0,
    gte75: availability >= 0.75 ? bins.gte75 + 1 : bins.gte75 || 0,
    eq100: availability === 1 ? bins.eq100 + 1 : bins.eq100 || 0
  }
};

const process = async availablityReport => {
  //iterate over each field and lookup and compute their availabilities
  const { resources, fields, lookups, lookupValues } = availablityReport;

  const transformed = AVAILABILITY_TEMPLATE;

  const standardFieldCache = createStandardFieldCache(standardMeta.fields);

  const resourceCounts = {};
  resources.forEach(resource => resourceCounts[resource.resourceName] = resource.numRecordsFetched);

  const processedLookupValues = [], lookupValueCache = createLookupValueCache(lookupValues);

  const processedFields = fields.map(field => {
    const availability = resourceCounts[field.resourceName] !== 0 ? 1.0 * field.frequency / resourceCounts[field.resourceName] : 0;

    transformed.availability.fields.total = computeBins(availability, transformed.availability.fields.total);

    if (isResoField(field.resourceName, field.fieldName, standardFieldCache)) {
      transformed.availability.fields.reso = computeBins(availability, transformed.availability.fields.reso);
      if (isIdxField(field.resourceName, field.fieldName, standardFieldCache)) {
        transformed.availability.fields.idx = computeBins(availability, transformed.availability.fields.idx);
      }
    } else {
      transformed.availability.fields.local = computeBins(availability, transformed.availability.fields.local);
    }

    const lookupsForField = lookupValueCache[field.resourceName] && lookupValueCache[field.resourceName][field.fieldName];
    if (lookupsForField) {
      //process lookup values
      processedLookupValues.push(Object.values(lookupsForField).map(lookupValue => {
        if (lookupValue.lookupValue !== 'null' && lookupValue.lookupValue !== 'NULL_VALUE') {
          const lookupAvailability =  !!field.frequency && !!lookupValue.frequency && !!resourceCounts[field.resourceName]
                                ? 1.0 * lookupValue.frequency / (field.frequency / resourceCounts[field.resourceName]) : 0;
          transformed.availability.lookups.total = computeBins(availability, transformed.availability.lookups.total);
        
          if (findResoLookup(lookupValue.resourceName, lookupValue.fieldName, lookupValue.lookupValue, standardFieldCache)) {
            if (isResoField(lookupValue.resourceName, lookupValue.fieldName, standardFieldCache)) {
              transformed.availability.lookups.reso = computeBins(availability, transformed.availability.lookups.reso);
              if (isIdxField(lookupValue.resourceName, lookupValue.fieldName, standardFieldCache)) {
                transformed.availability.lookups.idx = computeBins(availability, transformed.availability.lookups.idx);
              }
            }
          } else {
            transformed.availability.lookups.local = computeBins(availability, transformed.availability.lookups.local);        
          } 
          return { ...lookupValue, lookupAvailability };
        }
      }));
    }
    
    return { ...field, availability };
  });

  transformed.resources = resources;
  transformed.fields = processedFields;
  transformed.lookups = lookups;
  transformed.lookupValues = processedLookupValues;

  return transformed;
}

module.exports = {
  processDataAvailabilityReport
};