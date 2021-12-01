const fs = require('fs').promises;
const { standardMeta: REFERENCE_METADATA } = require('../references/standardMeta');
const { lookupMap: LOOKUP_MAP } = require('../references/lookupMap');

const PATH_TO_SAMPLE_REPORT = '';

const processDataAvailabilityReport = async pathToDataAvailabilityReport => {
  try {
    
    const availablityReport = JSON.parse(await fs.readFile(pathToDataAvailabilityReport, 'utf8'));
    await fs.writeFile('./availability-processed.json', JSON.stringify(await process(availablityReport)));

  } catch (err) {
    console.error(err);
  }
}

const process = async availablityReport => {
  //iterate over each field and lookup and compute their availabilities
  const { resources, fields, lookups, lookupValues } = availablityReport;

  const transformed = {
    fields: [],
    lookups: [],
    lookupValues: [],
    resources: [],
    availability: {
      fields: {},
      lookups: {},
      resources: {},
      resourcesBinary: {}
    }
  };

  const resourceCounts = {};
  resources.forEach(resource => resourceCounts[resource.resourceName] = resource.numRecordsFetched);

  const calculateFieldAvailability = fields.map(field => {
    return {...field, 
      availability: resourceCounts[field.resourceName] !== 0 ? 1.0 * field.frequency / resourceCounts[field.resourceName] : 0
    }
  });

  transformed.resources.push(resources);
  transformed.fields.push(calculateFieldAvailability);



  return transformed;
};

module.exports = {
  processDataAvailabilityReport
};