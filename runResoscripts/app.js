const fs = require('fs');
const fse = require('fs-extra');
const { execSync } = require('child_process');

const { processDataDictionaryResults } = require('./services/postResultsToApi.js');
const { processDataAvailabilityReport } = require('./services/processDataAvailabilityReport.js');

const { COMMANDER_PATH } = require('./batch-config.json');
const CERTIFICATION_RESULTS_PATH = `${COMMANDER_PATH}/build/certification`;

const buildRecipientPath = (providerUoi, recipientUoi) => {
  if (!providerUoi) throw Error('providerUoi is required!');
  if (!recipientUoi) throw Error('recipientUoi is required!');

  return `${providerUoi}/${recipientUoi}`;
};


const createResoscriptBearerTokenConfig = ({uri, token} = config) => '<?xml version="1.0" encoding="utf-8" ?>' +
  '<OutputScript>' +
  '  <ClientSettings>' +
  `    <WebAPIURI>${uri}</WebAPIURI>` +
  '    <AuthenticationType>authorization_code</AuthenticationType>' +
  `    <BearerToken>${token}</BearerToken>` +
  '  </ClientSettings>' +
  '</OutputScript>';

const createResoscriptClientCredentialsConfig = ( { uri, clientCredentials } = config) => '<?xml version="1.0" encoding="utf-8" ?>' +
  '<OutputScript>' +
  '  <ClientSettings>' +
  `    <WebAPIURI>${uri}</WebAPIURI>` +
  '    <AuthenticationType>client_credentials</AuthenticationType>' +
  `    <ClientIdentification>${clientCredentials.clientId}</ClientIdentification>` +
  `    <ClientSecret>${clientCredentials.clientSecret}</ClientSecret>` +
  `    <TokenURI>${clientCredentials.tokenUri}</TokenURI>` +
  `    ${clientCredentials.scope ? '<ClientScope>' + clientCredentials.scope  + '</ClientScope>': ''}` +
  '  </ClientSettings>' +
  '</OutputScript>';

const isClientCredentalsConfig = ( config = { clientCredentials: {} } ) => config.clientCredentials 
  && config.clientCredentials.clientId 
  && config.clientCredentials.clientSecret
  && config.clientCredentials.tokenUri;

const isBearerTokenConfig = ( config = { token: '' } ) => !!config.token;

const buildResoscript = (config={}) => {
  if (isClientCredentalsConfig(config)) {
    return createResoscriptClientCredentialsConfig(config);
  } else if (isBearerTokenConfig(config)) {
    return createResoscriptBearerTokenConfig(config);
  } 

  return null;
}

const runTests = async providerInfo => {
  const CONFIG_FILE = '/path/to/config.json';

  try {
    providerInfo = JSON.parse(fs.readFileSync(CONFIG_FILE));
  } catch (err) {
    throw new Error('Could not read provider info!');
  }

  const { providerUoi, configs } = providerInfo;

  if (!providerUoi) throw new Error('providerUoi is required!');
  if (!configs || !configs.length) throw new Error('configs must contain valid configurations');

  try {
    if (fs.existsSync(providerUoi)) {
      try {
        fs.renameSync(providerUoi, `${providerUoi}-old-${Date.now()}`);
      } catch (err) {
        console.error(err);
        throw new Error('Could not rename directory! Exiting!');
      }
    }

    //create root directory
    fs.mkdirSync(providerUoi);

    const totalTestCount = configs.length;
    let failedTestCount = 0;

    configs.forEach(config => {

      const 
        RECIPIENT_PATH = buildRecipientPath(providerUoi, config.recipientUoi),
        RESOSCRIPT_CONFIG = buildResoscript(config),
        CONFIG_PATH = `${COMMANDER_PATH}/${RECIPIENT_PATH}/config.xml`;

      if (!RESOSCRIPT_CONFIG) throw new Error('There was a problem creating a RESOScript config for recipientUoi: ' + config.recipientUoi);

      //create recipient directory
      fs.mkdirSync(RECIPIENT_PATH);
      fs.writeFileSync(CONFIG_PATH, RESOSCRIPT_CONFIG);

      //run dd tests
      const dataDictionaryResult = execSync(`${COMMANDER_PATH}/gradlew testDataDictionary_1_7 -DpathToRESOScript='${CONFIG_PATH}'`,
        { stdio: ['inherit', 'inherit', 'pipe'] });

      if (dataDictionaryResult && dataDictionaryResult.stderr) {
        console.error('Data Dictionary testing failed for recipientUoi: ' + config.recipientUoi);
        console.error(Error(dataDictionaryResult.stderr));

        //TODO, create error directory with each corresponding log
        process.exitCode = 1;
      }

      //run data availability tests
      const dataAvailabilityResult = execSync(`${COMMANDER_PATH}/gradlew testDataAvailability_1_7 -DpathToRESOScript='${CONFIG_PATH}'`,
        { stdio: ['inherit', 'inherit', 'pipe'] });


      if (dataAvailabilityResult && dataAvailabilityResult.stderr) {
        console.error('Data Dictionary testing failed for recipientUoi: ' + config.recipientUoi);
        console.error(Error(dataAvailabilityResult.stderr));
        process.exitCode = 1;
      }

      const paths = ['results', 'reports', 'cucumberJson'];
      paths.forEach(path => {
        fse.copySync(`${CERTIFICATION_RESULTS_PATH}/${path}`, RECIPIENT_PATH, { overwrite: true }, err => {
          if (err) {
            console.error(err);
          } else {
            console.log(`Copied ${path} to ${RECIPIENT_PATH}`);
          }
        });
      });
    });

    console.log("Testing complete! Tests passed: " + totalTestCount);

  } catch (err) {
    console.error(err)
  }
};

const processDDResult = async (providerUoi, recipientUoi) => 
  await processDataDictionaryResults(providerUoi, recipientUoi, buildRecipientPath(providerUoi, recipientUoi));


// const cliHandler = argv => {
//   argv.command({
//       command: "action",
//       description: "top level command",
//       builder: {
//           command: "bar",
//           description: "child command of foo",
//           builder: function() {
//               console.log("builder barr!");
//           },
//           handler: a => {
//               console.log("handler barr!");
//           }
//       },
//       handler: args => {
//           console.log("handler foo!");
//       }
//   })
//   .demand(1, "must provide a valid command")
//   .help("h")
//   .alias("h", "help")
//   .argv

//   if (runTests) {
//       const { configFilePath } = argv;

//       if (!configFilePath) console.log('configFilePath is required!\nUsage: $ node . --runTests');

//   } else if (processDDResult) {
    
//   } else if (dataAvailabilityEtl) {

//   } else {

//   }
// };

module.exports = {
  runTests,
  processDDResult,
  processDataAvailabilityReport
};