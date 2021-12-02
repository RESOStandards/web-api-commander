const fs = require('fs').promises;
const { standardMeta } = require('../references/standardMeta');
const { lookupMap } = require('../references/lookupMap');

const PATH_TO_SAMPLE_REPORT = '../samples/data-availability-report.utahre.json';

//TODO: move to consts file
const TRANSFORMED_TEMPLATE_OBJECT = {
  fields: [],
  lookups: [],
  lookupValues: [],
  resources: [],
  availability: {
    fields: {
      total: {
        eq0 : 0,
        gt0 : 0,
        gte25 : 0,
        gte50 : 0,
        gte75 : 0,
        eq100 : 0
      },
      reso: {
        eq0 : 0,
        gt0 : 0,
        gte25 : 0,
        gte50 : 0,
        gte75 : 0,
        eq100 : 0
      },
      local: { 
        eq0 : 0,
        gt0 : 0,
        gte25 : 0,
        gte50 : 0,
        gte75 : 0,
        eq100 : 0
      },
      idx: {
        eq0 : 0,
        gt0 : 0,
        gte25 : 0,
        gte50 : 0,
        gte75 : 0,
        eq100 : 0
      }
    },
    lookups : {
      total: {
        eq0 : 0,
        gt0 : 0,
        gte25 : 0,
        gte50 : 0,
        gte75 : 0,
        eq100 : 0
      },
      reso: {
        eq0 : 0,
        gt0 : 0,
        gte25 : 0,
        gte50 : 0,
        gte75 : 0,
        eq100 : 0
      },
      local: { 
        eq0 : 0,
        gt0 : 0,
        gte25 : 0,
        gte50 : 0,
        gte75 : 0,
        eq100 : 0
      },
      idx: {
        eq0 : 0,
        gt0 : 0,
        gte25 : 0,
        gte50 : 0,
        gte75 : 0,
        eq100 : 0
      }
    },
    resources: {},
    resourcesBinary: {}
  }
};

const createStandardFieldCache = (fields=[]) => {
  const resourceFieldCache = {};
  fields.forEach(field => {
      if (!resourceFieldCache[field.resourceName]) {
        resourceFieldCache[field.resourceName] = {};
      }
      resourceFieldCache[field.resourceName][field.fieldName] = field;
  });
  return resourceFieldCache;
};

const isIdxField = (field, fieldCache={}) => field && field.resourceName && field.fieldName
  && isResoField(field, fieldCache) && fieldCache[field.resourceName][field.fieldName].payloads.filter(x => x === "IDX");

const isResoField = (field, fieldCache={}) => field && field.resourceName && field.fieldName 
  && fieldCache[field.resourceName] && fieldCache[field.resourceName][field.fieldName];

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

const createLookupCache = (lookups=[]) => {
  const resourceFieldLookupCache = {};
  lookups.forEach(lookup => {
    
  });
}


const process = async availablityReport => {
  //iterate over each field and lookup and compute their availabilities
  const { resources, fields, lookups, lookupValues } = availablityReport;

  const transformed = TRANSFORMED_TEMPLATE_OBJECT;

  const standardFieldCache = createStandardFieldCache(standardMeta.fields);

  const resourceCounts = {};
  resources.forEach(resource => resourceCounts[resource.resourceName] = resource.numRecordsFetched);

  const calculateFieldAvailability = field => resourceCounts[field.resourceName] !== 0 ? 1.0 * field.frequency / resourceCounts[field.resourceName] : 0;  
  const calculateLookupAvailability = lookup => 0;

  const updateBins = (bins, availability) => {
    return {
      eq0: availability === 0 ? bins.eq0 += 1 : bins.eq0 || 0,
      gt0: availability > 0 ? bins.gt0 += 1 : bins.gt0 || 0,
      gte25: availability >= 0.25 ? bins.gte25 += 1 : bins.gte25 || 0,
      gte50: availability >= 0.5 ? bins.gte50 += 1 : bins.gte50 || 0,
      gte75: availability >= 0.75 ? bins.gte75 += 1 : bins.gte75 || 0,
      eq100: availability === 1 ? bins.eq100 += 1 : bins.eq100 || 0
    }
  };

  const processedFields = fields.map(field => {
    const availability = calculateFieldAvailability(field);

    transformed.availability.fields.total = updateBins(transformed.availability.fields.total, availability);
    if (isResoField(field, standardFieldCache)) {
      transformed.availability.fields.reso = updateBins(transformed.availability.fields.reso, availability);
      if (isIdxField(field, standardFieldCache)) {
        transformed.availability.fields.idx = updateBins(transformed.availability.fields.idx, availability);
      }
    } else {
      transformed.availability.fields.local = updateBins(transformed.availability.fields.local, availability);
    }
        
    return { ...field, availability };
  });

  const processedLookups = resources.map(lookup => {
    const availability = calculateLookupAvailability();
  });

  transformed.resources.push(resources);
  transformed.fields.push(processedFields);

  return transformed;
};

module.exports = {
  processDataAvailabilityReport
};