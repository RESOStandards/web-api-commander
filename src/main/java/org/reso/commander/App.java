package org.reso.commander;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.certification.codegen.BDDProcessor;
import org.reso.certification.codegen.DataDictionaryCodeGenerator;
import org.reso.certification.codegen.EDMXProcessor;
import org.reso.models.ClientSettings;
import org.reso.models.Request;
import org.reso.models.Settings;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import static org.reso.commander.Commander.*;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.Utils.getTimestamp;

/**
 * Entry point of the RESO Web API Commander, which is a command line OData client that uses the Java Olingo
 * Client Library to handle OData. Currently, the following forms
 * of Auth are supported:
 * <p>
 * - Bearer Tokens
 * - Client Credentials (in-progress)
 * <p>
 * Exposes several different actions for working with OData-based WebAPI servers.
 * This application is structured so that the App class is an OData WebAPI consumer
 * using the Commander class, which contains the actual methods for working with OData.
 * <p>
 * For usage, see README.
 * For documentation, see /build/docs
 * <p>
 */
public class App {

  private static final Logger LOG = LogManager.getLogger(App.class);

  private static final String OUTPUT_DIR = "user.dir";

  public static void main(String[] params) {
    // create the command line parser
    CommandLineParser parser = new org.apache.commons.cli.DefaultParser();
    String metadata = null;

    //available Commander variables
    String serviceRoot = null, bearerToken = null, clientId = null, clientSecret = null,
        authorizationUri = null, tokenUri = null, redirectUri = null, scope = null;
    String inputFilename, outputFile, uri;
    boolean useEdmEnabledClient;
    int pageLimit, pageSize;

    //created with the commanderBuilder throughout the initialization body
    Commander commander;

    //create a new Commander builder to be used throughout the initialization process
    Commander.Builder commanderBuilder = new Commander.Builder();

    try {
      // parser for command line arguments
      CommandLine cmd = parser.parse(APP_OPTIONS.getOptions(), params);

      // pre-load command line options for later use //
      useEdmEnabledClient = cmd.hasOption(APP_OPTIONS.USE_EDM_ENABLED_CLIENT);
      inputFilename = cmd.getOptionValue(APP_OPTIONS.INPUT_FILE, null);
      outputFile = cmd.getOptionValue(APP_OPTIONS.OUTPUT_FILE, null);
      uri = cmd.getOptionValue(APP_OPTIONS.URI, null);
      ContentType contentType = Commander.getContentType(cmd.getOptionValue(APP_OPTIONS.CONTENT_TYPE, null));

      // using the edmEnabledClient requires the serviceRoot for schema validation, which is performed
      // against the payload each time the request is made when enabled.
      if (useEdmEnabledClient && !(cmd.hasOption(APP_OPTIONS.SERVICE_ROOT) || cmd.hasOption(APP_OPTIONS.ACTIONS.RUN_RESOSCRIPT))) {
        printErrorMsgAndExit("\nERROR: --" + APP_OPTIONS.SERVICE_ROOT + " is required with the --" + APP_OPTIONS.USE_EDM_ENABLED_CLIENT + " option!");
      }

      //if we're running a batch, initialize variables from the settings file rather than from command line options
      Settings settings = null;

      LOG.debug("Service Root is:" + commanderBuilder.serviceRoot);

      //If the RESOScript option was passed, then the correct commander instance should exist at this point
      if (cmd.hasOption(APP_OPTIONS.ACTIONS.RUN_RESOSCRIPT)) {
        LOG.debug("Loading RESOScript: " + inputFilename);
        settings = Settings.loadFromRESOScript(new File(inputFilename));
        LOG.debug("RESOScript loaded successfully!");

        commanderBuilder.serviceRoot(settings.getClientSettings().get(ClientSettings.SERVICE_ROOT));

        //TODO: add base64 un-encode when applicable
        commanderBuilder.bearerToken(settings.getClientSettings().get(ClientSettings.BEARER_TOKEN));
        if (commanderBuilder.bearerToken != null && commanderBuilder.bearerToken.length() > 0) {
          LOG.debug("Bearer token loaded... first 4 characters:"
              + commanderBuilder.bearerToken.substring(0, Math.max(commanderBuilder.bearerToken.length(), 4)));
        }

        commanderBuilder.clientId(settings.getClientSettings().get(ClientSettings.CLIENT_IDENTIFICATION));
        commanderBuilder.clientSecret(settings.getClientSettings().get(ClientSettings.CLIENT_SECRET));
        commanderBuilder.tokenUri(settings.getClientSettings().get(ClientSettings.TOKEN_URI));
        commanderBuilder.scope(settings.getClientSettings().get(ClientSettings.CLIENT_SCOPE));


        //create Commander instance
        commander = commanderBuilder.build();

        int numRequests = settings.getRequests().size();

        LOG.info(REPORT_DIVIDER);
        LOG.info("Web API Commander Starting... Press <ctrl+c> at any time to exit.");
        LOG.info(REPORT_DIVIDER);

        LOG.info("Running " + numRequests + " Request(s)");
        LOG.info("RESOScript: " + inputFilename);
        LOG.info(REPORT_DIVIDER + "\n\n");

        //put in local directory rather than relative to where the input file is
        String directoryName = System.getProperty(OUTPUT_DIR),
            outputPath = inputFilename
                .substring(inputFilename.contains(File.separator) ? inputFilename.lastIndexOf(File.separator) : 0)
                .replace(RESOSCRIPT_EXTENSION, "") + "-" + getTimestamp(new Date());

        String resolvedUrl = null;

        Request request = null;

        //this is an integer so it can be nullable in cases where we don't care about the response code assertion
        Integer responseCode = null;

        String outputFilePath;

        for (int i = 0; i < numRequests; i++) {
          try {
            request = settings.getRequestsAsList().get(i);

            //TODO: create dynamic JUnit (or similar) test runner
            LOG.info(REPORT_DIVIDER_SMALL);
            LOG.info("Request: #" + (i + 1));
            LOG.info(REPORT_DIVIDER_SMALL);

            if (request.getRequestId() != null && request.getRequestId().length() > 0) {
              LOG.info("Request Id: " + request.getRequestId());
            }

            resolvedUrl = Settings.resolveParameters(request, settings).getUrl();
            LOG.info("Resolved URL: " + resolvedUrl);

            //only run tests if they have URLs that resolve
            if (resolvedUrl != null && resolvedUrl.length() > 0 && request.getOutputFile() != null && request.getOutputFile().length() > 0) {

              outputFilePath = directoryName + outputPath + File.separator + request.getOutputFile();
              LOG.info("Output File: " + outputFilePath);

              //get the response code from the request
              responseCode = commander.saveGetRequest(resolvedUrl, outputFilePath);
              request.setHttpResponseCode(responseCode);
              LOG.info("Response Code: " + responseCode);

            } else {
              LOG.info("Request " + request.getRequestId() + " has an empty URL. Skipping...");
            }
          } catch (Exception ex) {
            LOG.error("Exception thrown in RUN_RESOSCRIPT Action. Exception is: \n" + ex.toString());
            LOG.error("Stack trace:");
            Arrays.stream(ex.getStackTrace()).forEach(stackTraceElement -> LOG.error(stackTraceElement.toString()));
          }
          LOG.info("\n  ");
        }

        LOG.info(REPORT_DIVIDER);
        LOG.info("RESOScript Complete!");
        LOG.info(REPORT_DIVIDER + "\n");

        System.exit(OK); //terminate the program after the batch completes
      } else {
        /* otherwise, not a batch request...
         proceed with the selected command-line option */

        commanderBuilder.serviceRoot(cmd.getOptionValue(APP_OPTIONS.SERVICE_ROOT, null));
        commanderBuilder.bearerToken(cmd.getOptionValue(APP_OPTIONS.BEARER_TOKEN, null));
        commanderBuilder.clientId(cmd.getOptionValue(APP_OPTIONS.CLIENT_ID, null));
        commanderBuilder.clientSecret(cmd.getOptionValue(APP_OPTIONS.CLIENT_SECRET, null));
        commanderBuilder.tokenUri(cmd.getOptionValue(ClientSettings.TOKEN_URI, null));
        commanderBuilder.scope(cmd.getOptionValue(ClientSettings.CLIENT_SCOPE, null));
        commanderBuilder.useEdmEnabledClient(cmd.hasOption(APP_OPTIONS.USE_EDM_ENABLED_CLIENT));

        //create Commander instance
        commander = commanderBuilder.build();

        if (cmd.hasOption(APP_OPTIONS.ACTIONS.GET_METADATA)) {
          APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.GET_METADATA);
          commander.saveXmlMetadataResponseToFile(outputFile);
        } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.GENERATE_METADATA_REPORT)) {
          APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.GENERATE_METADATA_REPORT);
          LOG.info(generateMetadataReport(deserializeEdmFromPath(inputFilename, commander.getClient()), inputFilename));
        } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.VALIDATE_METADATA)) {
          APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.VALIDATE_METADATA);

          System.exit(validateMetadata(commander, inputFilename) ? OK : NOT_OK);

        } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.SAVE_GET_REQUEST)) {
          APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.SAVE_GET_REQUEST);

          commander.saveGetRequest(uri, outputFile);

        } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.GENERATE_DD_ACCEPTANCE_TESTS)) {
          try {
            DataDictionaryCodeGenerator generator = new DataDictionaryCodeGenerator(new BDDProcessor());
            generator.processWorksheets();
          } catch (Exception ex) {
            LOG.error(getDefaultErrorMessage(ex));
          }
        } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.GENERATE_REFERENCE_EDMX)) {
          try {
            DataDictionaryCodeGenerator generator = new DataDictionaryCodeGenerator(new EDMXProcessor());
            generator.processWorksheets();
          } catch (Exception ex) {
            LOG.error(getDefaultErrorMessage(ex));
          }
        } else {
          printHelp(APP_OPTIONS.getOptions());
        }
      }
    } catch (ParseException exp) {
      LOG.error("\nERROR: Parse Exception, Commander cannot continue! " + exp.getMessage());
      System.exit(NOT_OK);
    }
  }

  private static boolean validateMetadata(Commander commander, String inputFilename) {

    if (commander == null) {
      LOG.error("Null instance of Commander passed to validateMetadata. Cannot continue...");
      return false;
    }

    /**
     * Validates the metadata in inputFilename in the following ways:
     *    - de-serializes it into a native Edm object, which will fail if given metadata isn't valid
     *    - verifies whether the given EDMX file is a valid service document
     *    - verifies whether the given EDMX file is in version 4 format
     *    - calls the Olingo library's metadata validation method.
     *
     *    See Commander#validateMetadata for more info.
     */
    LOG.info("Checking Metadata for validity...");
    try {
      if (commander.validateMetadata(inputFilename)) {
        LOG.info("Valid Metadata!");
        return true;
      }
    } catch (Exception ex) {
      LOG.error(ex);
    }
    LOG.error("Invalid Metadata!");
    return false;
  }

  /**
   * Prints an error message and the help for the application, then exits with 1.
   *
   * @param msg the message to print
   */
  private static void printErrorMsgAndExit(String msg) {
    LOG.error("\n\n" + msg + "\n");
    printHelp(APP_OPTIONS.getOptions());
    System.exit(NOT_OK);
  }

  /**
   * Prints help
   *
   * @param options the options to print help for.
   */
  private static void printHelp(Options options) {
    new HelpFormatter().printHelp("java -jar web-api-commander", options);
  }

  /**
   * Generates totals report from aggregates.
   *
   * @param totalRequestCount total request count
   * @param numSucceeded      num requests succeeded
   * @param numFailed         num requests failed
   * @param numSkipped        num requests skipped
   * @param numIncomplete     num requests incomplete
   * @return a string containing the report.
   */
  private static String generateTotalsReport(int totalRequestCount, int numSucceeded, int numFailed, int numSkipped, int numIncomplete) {
    StringBuilder reportBuilder = new StringBuilder();
    reportBuilder.append("\n\tTotal:            ").append(String.format("%1$4s", totalRequestCount));
    reportBuilder.append("\n\tSucceeded:        ").append(String.format("%1$4s", numSucceeded)).append(totalRequestCount > 0 ? " (" + String.format("%.2f", (100.0 * numSucceeded) / totalRequestCount) + "%)" : "");
    reportBuilder.append("\n\tFailed:           ").append(String.format("%1$4s", numFailed)).append(totalRequestCount > 0 ? " (" + String.format("%.2f", (100.0 * numFailed) / totalRequestCount) + "%)" : "");
    reportBuilder.append("\n\tSkipped:          ").append(String.format("%1$4s", numSkipped)).append(totalRequestCount > 0 ? " (" + String.format("%.2f", (100.0 * numSkipped) / totalRequestCount) + "%)" : "");
    reportBuilder.append("\n\tIncomplete:       ").append(String.format("%1$4s", numIncomplete)).append(totalRequestCount > 0 ? " (" + String.format("%.2f", (100.0 * numIncomplete) / totalRequestCount) + "%)" : "");
    return reportBuilder.toString();
  }

  /**
   * Helps with various app options used by the main application when processing input from the command line.
   */
  private static class APP_OPTIONS {

    //parameter names
    static String SERVICE_ROOT = "serviceRoot";
    static String BEARER_TOKEN = "bearerToken";
    static String CLIENT_ID = "clientId";
    static String CLIENT_SECRET = "clientSecret";
    static String INPUT_FILE = "inputFile";
    static String OUTPUT_FILE = "outputFile";
    static String URI = "uri";
    static String ENTITY_NAME = "entityName";
    static String USE_EDM_ENABLED_CLIENT = "useEdmEnabledClient";
    static String CONTENT_TYPE = "contentType";
    static String HELP = "help";

    /**
     * Validates options for the various actions exposed in App.
     * <p>
     * TODO: determine if there's a way this can be handled using Commons Command.
     *
     * @param cmd    a reference to the current Command instance
     * @param action one of APP_OPTIONS.ACTIONS, representing the action to be performed
     *               <p>
     *               If the given action doesn't validate, then an error message will be printed and the application will exit.
     */
    static void validateAction(CommandLine cmd, String action) {
      String validationResponse = null;

      if (action.matches(ACTIONS.RUN_RESOSCRIPT)) {
        validationResponse = validateOptions(cmd, INPUT_FILE);
      } else if (action.matches(ACTIONS.GET_METADATA)) {
        if (cmd.hasOption(CLIENT_ID) || cmd.hasOption(CLIENT_SECRET)) {
          validationResponse = validateOptions(cmd, SERVICE_ROOT, CLIENT_ID, CLIENT_SECRET, OUTPUT_FILE);
        } else {
          validationResponse = validateOptions(cmd, SERVICE_ROOT, BEARER_TOKEN, OUTPUT_FILE);
        }
      } else if (action.matches(ACTIONS.GENERATE_METADATA_REPORT)) {
        validationResponse = validateOptions(cmd, INPUT_FILE);
      } else if (action.matches(ACTIONS.VALIDATE_METADATA)) {
        validationResponse = validateOptions(cmd, INPUT_FILE);
      } else if (action.matches(ACTIONS.SAVE_GET_REQUEST)) {
        if (cmd.hasOption(CLIENT_ID) || cmd.hasOption(CLIENT_SECRET)) {
          validationResponse = validateOptions(cmd, CLIENT_ID, CLIENT_SECRET, URI, OUTPUT_FILE);
        } else {
          validationResponse = validateOptions(cmd, BEARER_TOKEN, URI, OUTPUT_FILE);
        }
      }
      if (validationResponse != null) {
        printErrorMsgAndExit("ERROR: the following options are required when using " + action
            + "\n" + validationResponse + "\n\n");
      }
    }

    /**
     * Validates options passed to the command line
     *
     * @param cmd     a reference to the command line instance
     * @param options a list of options to validate
     * @return an error string containing a formatted message when validation fails, otherwise null (valid)
     */
    static String validateOptions(CommandLine cmd, String... options) {
      StringBuilder sb = new StringBuilder();
      Arrays.stream(options).forEach(option -> {
        if (!cmd.hasOption(option)) {
          sb.append("\n\t --");
          sb.append(option);
          sb.append(" is required!");
        }
      });
      return sb.length() == 0 ? null : sb.toString();
    }

    /**
     * Gets the set of supported application options.
     *
     * @return the options to be used within the application.
     */
    static Options getOptions() {
      // create Options
      Option hostNameOption = Option.builder()
          .argName("s").longOpt(SERVICE_ROOT).hasArg()
          .desc("Service root URL on the host.")
          .build();

      Option bearerTokenOption = Option.builder()
          .argName("b").longOpt(BEARER_TOKEN).hasArg()
          .desc("Bearer token to be used with the request.")
          .build();

      Option clientIdOption = Option.builder()
          .argName("d").longOpt(CLIENT_ID).hasArg()
          .desc("Client Id to be used with the request.")
          .build();

      Option clientSecretOption = Option.builder()
          .argName("s").longOpt(CLIENT_SECRET).hasArg()
          .build();

      Option inputFileOption = Option.builder()
          .argName("i").longOpt(INPUT_FILE).hasArg()
          .desc("Path to input file.")
          .build();

      Option outputFileOption = Option.builder()
          .argName("o").longOpt(OUTPUT_FILE).hasArg()
          .desc("Path to output file.")
          .build();

      Option uriOption = Option.builder()
          .argName("u").longOpt(URI).hasArg()
          .desc("URI for raw request. Use 'single quotes' to enclose.")
          .build();

      Option entityName = Option.builder()
          .argName("n").longOpt(ENTITY_NAME).hasArg()
          .desc("The name of the entity to fetch, e.g. Property.")
          .build();

      Option contentType = Option.builder()
          .argName("t").longOpt(CONTENT_TYPE).hasArg()
          .desc("Results format: JSON (default), JSON_NO_METADATA, JSON_FULL_METADATA, XML.")
          .build();

      Option useEdmEnabledClient = Option.builder()
          .argName("e").longOpt(USE_EDM_ENABLED_CLIENT)
          .desc("present if an EdmEnabledClient should be used.")
          .build();

      Option helpOption = Option.builder()
          .argName("?").longOpt(HELP).hasArg(false)
          .desc("print help")
          .build();

      OptionGroup actions = new OptionGroup()
          .addOption(Option.builder().argName("r").longOpt(ACTIONS.RUN_RESOSCRIPT)
            .desc("Runs commands in RESOScript file given as <inputFile>.").build())
          .addOption(Option.builder().argName("t").longOpt(ACTIONS.GENERATE_DD_ACCEPTANCE_TESTS)
            .desc("Generates acceptance tests in the current directory.").build())
          .addOption(Option.builder().argName("r").longOpt(ACTIONS.GENERATE_REFERENCE_EDMX)
            .desc("Generates reference metadata in EDMX format.").build())
          .addOption(Option.builder().argName("m").longOpt(ACTIONS.GET_METADATA)
            .desc("Fetches metadata from <serviceRoot> using <bearerToken> and saves results in <outputFile>.").build())
          .addOption(Option.builder().argName("g").longOpt(ACTIONS.GENERATE_METADATA_REPORT)
              .desc("Generates metadata report from given <inputFile>.").build())
          .addOption(Option.builder().argName("v").longOpt(ACTIONS.VALIDATE_METADATA)
              .desc("Validates previously-fetched metadata in the <inputFile> path.").build())
          .addOption(Option.builder().argName("s").longOpt(ACTIONS.SAVE_GET_REQUEST)
              .desc("Performs GET from <requestURI> using the given <bearerToken> and saves output to <outputFile>.").build());

      return new Options()
          .addOption(helpOption)
          .addOption(hostNameOption)
          .addOption(bearerTokenOption)
          .addOption(clientIdOption)
          .addOption(clientSecretOption)
          .addOption(inputFileOption)
          .addOption(outputFileOption)
          .addOption(entityName)
          .addOption(useEdmEnabledClient)
          .addOption(uriOption)
          .addOption(contentType)
          .addOptionGroup(actions);
    }

    static class ACTIONS {
      //actions
      public static final String GENERATE_DD_ACCEPTANCE_TESTS = "generateDDAcceptanceTests";
      public static final String GENERATE_REFERENCE_EDMX = "generateReferenceEDMX";
      public static final String RUN_RESOSCRIPT = "runRESOScript";
      public static final String GET_METADATA = "getMetadata";
      public static final String VALIDATE_METADATA = "validateMetadata";
      public static final String SAVE_GET_REQUEST = "saveGetRequest";
      public static final String GENERATE_METADATA_REPORT = "generateMetadataReport";
    }
  }
}
