package org.reso;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.format.ContentType;

import java.net.URI;
import java.util.Arrays;

/**
 * Entry point of the RESO Web API Commander, which is a command line OData client that uses the Java Olingo
 * Client Library to handle OData and the Apache CXF library to handle Auth. Currently, the following forms
 * of Auth are supported:
 *
 *  - Bearer Tokens
 *
 * Exposes several different actions for working with OData-based WebAPI servers.
 * This application is structured so that the Main class is an OData WebAPI consumer
 * using the Commander class, which contains the actual methods for working with OData.
 *
 * For usage, see README.
 *
 * TODO: add better handling for required parameters, currently just checks if they're there and prints help if not
 */
public class Main {

  private static final Logger log = Logger.getLogger(Main.class);

  public static void main(String[] params) {

    // create the command line parser
    CommandLineParser parser = new DefaultParser();
    Edm metadata;
    Commander commander;

    String serviceRoot, bearerToken;

    try {
      // parse the command line arguments
      CommandLine cmd = parser.parse(APP_OPTIONS.getOptions(), params);

      serviceRoot = cmd.getOptionValue(APP_OPTIONS.SERVICE_ROOT, null);

      // only bearer token support for now
      // TODO: apache CXF for other forms of auth
      bearerToken = cmd.getOptionValue(APP_OPTIONS.BEARER_TOKEN, null);

      boolean useEdmEnabledClient = cmd.hasOption(APP_OPTIONS.USE_EDM_ENABLED_CLIENT);

      // using the edmEnabledClient requires the serviceRoot for schema validation, which is performed
      // against the payload each time the request is made when enabled.
      if (useEdmEnabledClient && !cmd.hasOption(APP_OPTIONS.SERVICE_ROOT)) {
        printErrorMsgAndExit("\nERROR: --" + APP_OPTIONS.SERVICE_ROOT
            + " is required with the --" + APP_OPTIONS.USE_EDM_ENABLED_CLIENT + " option!");
      }

      // pre-load options for later use
      String inputFile = cmd.getOptionValue(APP_OPTIONS.INPUT_FILE, null);
      String outputFile = cmd.getOptionValue(APP_OPTIONS.OUTPUT_FILE, null);
      String entityName = cmd.getOptionValue(APP_OPTIONS.ENTITY_NAME, null);
      String uri = cmd.getOptionValue(APP_OPTIONS.URI, null);
      String filter = cmd.getOptionValue(APP_OPTIONS.FILTER, null);
      ContentType contentType = Commander.getContentType(cmd.getOptionValue(APP_OPTIONS.CONTENT_TYPE, null));

      //pass -1 to get all pages, default is 10
      int limit = Integer.parseInt(cmd.getOptionValue(APP_OPTIONS.LIMIT, "10"));

      // create a new Web API Commander
      commander = new Commander.Builder()
          .serviceRoot(serviceRoot)
          .bearerToken(bearerToken)
          .useEdmEnabledClient(useEdmEnabledClient)
          .build();

      if (cmd.hasOption(APP_OPTIONS.ACTIONS.GET_METADATA)) {
        APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.GET_METADATA);

        log.info("\nGetting metadata from " + serviceRoot + "...");
        metadata = commander.getMetadata(outputFile);

        log.info("\nThe metadata contains the following items:");
        prettyPrint(metadata);

        log.info("\nChecking Metadata for validity...");
        if (commander.validateMetadata(outputFile)) {
          log.info("--> Valid Metadata!");
        } else {
          log.error("--> Invalid Metadata!");
          System.exit(Commander.NOT_OK);
        }

      } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.VALIDATE_METADATA)) {
        APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.VALIDATE_METADATA);

        /**
         * Validates the metadata in inputFile in three ways:
         *    - deserializes it into a native Edm object, which will fail if given metadata isn't valid
         *    - verifies whether the given EDMX file is a valid service document
         *    - verifies whether the given EDMX file is in version 4 format
         */
        if (commander.validateMetadata(inputFile)) {
          log.info("Valid Metadata!");
        } else {
          log.error("ERROR: Invalid Metadata!\n");
          System.exit(Commander.NOT_OK);
        }
      } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.GET_ENTITY_SET)) {
        APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.GET_ENTITY_SET);

        /**
         * Gets a ClientEntitySet from the given uri. If the useEdmEnabledClient option was passed,
         * then serviceRoot is required, and results fetched from uri are validated against the server's
         * published metadata.
         *
         * Results are written to outputFile.
         */
          try {
            ClientEntitySet results = commander.getEntitySet(URI.create(uri));

            if (results != null) {
              commander.serializeEntitySet(results, outputFile, contentType);
            }
          } catch (Exception ex) {
            System.exit(Commander.NOT_OK);
            log.error(ex.toString());
          }

      } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.READ_ENTITIES)) {
        APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.READ_ENTITIES);

        /**
         * Read entities up to limit number of records and writes the results to outputFile.
         * Optionally takes a filter to be used with the query.
         *
         * @see Commander.readEntities()
         */

        //NOTE: pass -1 to get all entities
        log.info("Reading entities for the given resource: " + entityName);
        ClientEntitySet entities = commander.readEntities(entityName, filter, limit);

        log.info(entities.getCount() + " entities fetched: ");
        entities.getEntities().stream().forEach(entity -> log.info(entity.toString()));

        log.info("Saving to file: " + outputFile);
        commander.serializeEntitySet(entities, outputFile, contentType);

      } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.SAVE_RAW_GET_REQUEST)) {
        APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.SAVE_RAW_GET_REQUEST);

        commander.saveRawGetRequest(URI.create(uri), outputFile);

      } else if (cmd.hasOption(APP_OPTIONS.ACTIONS.CONVERT_EDMX_TO_OAI)) {
        APP_OPTIONS.validateAction(cmd, APP_OPTIONS.ACTIONS.CONVERT_EDMX_TO_OAI);

        //converts metadata in input source file to output file
        commander.convertMetadata(inputFile);

      } else {
        printHelp(APP_OPTIONS.getOptions());
      }
    } catch (ParseException exp) {
      log.error("\nERROR: Parse Exception, Commander cannot continue! " + exp.getMessage());
      System.exit(Commander.NOT_OK);
    }
  }

  /**
   * Prints an error message and the help for the application, then exits with 1.
   * @param msg the message to print
   */
  private static void printErrorMsgAndExit(String msg) {
    log.error("\n\n" + msg + "\n");
    printHelp(APP_OPTIONS.getOptions());
    System.exit(Commander.NOT_OK);
  }

  /**
   * Prints help
   * @param options the options to print help for.
   */
  private static void printHelp(Options options) {
    new HelpFormatter().printHelp("java -jar web-api-commander", options);
  }

  /**
   * Metadata Pretty Printer
   *
   * @param metadata any metadata in Edm format
   */
  private static void prettyPrint(Edm metadata) {

    //Note: other treatments may be added to this summary info
    metadata.getSchemas().stream().forEach(schema -> {
      log.info("\nNamespace: " + schema.getNamespace());
      log.info("=============================================================");

      schema.getTypeDefinitions().stream().forEach(a ->
          log.info("\tType Definition:" + a.getFullQualifiedName().getFullQualifiedNameAsString()));

      schema.getEnumTypes().stream().forEach(a ->
          log.info("\tEnum Type: " + a.getFullQualifiedName().getFullQualifiedNameAsString()));

      schema.getEntityTypes().stream().forEach(a ->
          log.info("\tEntity Type: " + a.getFullQualifiedName().getFullQualifiedNameAsString()));

      schema.getComplexTypes().stream().forEach(a ->
          log.info("\tComplex Entity Type: " + a.getFullQualifiedName().getFullQualifiedNameAsString()));

      schema.getAnnotationGroups().stream().forEach(a ->
          log.info("\tAnnotations: " + a.getQualifier() + ", Target Path: " + a.getTargetPath()));

      schema.getTerms().stream().forEach(a ->
          log.info(a.getFullQualifiedName().getFullQualifiedNameAsString()));
    });
  }

  /**
   * Helps with various app options used by the main application when processing input from the command line.
   */
  private static class APP_OPTIONS {

    //parameter names
    public static String SERVICE_ROOT = "serviceRoot";
    public static String BEARER_TOKEN = "bearerToken";
    public static String INPUT_FILE = "inputFile";
    public static String OUTPUT_FILE = "outputFile";
    public static String URI = "uri";
    public static String LIMIT = "limit";
    public static String ENTITY_NAME = "entityName";
    public static String USE_EDM_ENABLED_CLIENT = "useEdmEnabledClient";
    public static String FILTER = "filter";
    public static String CONTENT_TYPE = "contentType";
    public static String HELP = "help";

    public static class ACTIONS {
      //actions
      public static String GET_METADATA = "getMetadata";
      public static String VALIDATE_METADATA = "validateMetadata";
      public static String GET_ENTITY_SET = "getEntitySet";
      public static String READ_ENTITIES = "readEntities";
      public static String SAVE_RAW_GET_REQUEST = "saveRawGetRequest";
      public static String CONVERT_EDMX_TO_OAI = "convertEDMXtoOAI";
    }

    /**
     * Validates options for the various actions exposed in Main.
     *
     * TODO: determine if there's a way this can be handled using Commons Command.
     *
     * @param cmd a reference to the current Command instance
     * @param action one of APP_OPTIONS.ACTIONS, representing the action to be performed
     *
     * If the given action doesn't validate, then an error message will be printed and the application will exit.
     */
    public static void validateAction(CommandLine cmd, String action) {
      String validationResponse = null;

      if (action.matches(ACTIONS.GET_METADATA)) {
        validationResponse = validateOptions(cmd, SERVICE_ROOT, BEARER_TOKEN, OUTPUT_FILE);
      } else if (action.matches(ACTIONS.VALIDATE_METADATA)) {
        validationResponse = validateOptions(cmd, INPUT_FILE);
      } else if (action.matches(ACTIONS.GET_ENTITY_SET)) {
        validationResponse = validateOptions(cmd, BEARER_TOKEN, URI, OUTPUT_FILE);
      } else if (action.matches(ACTIONS.READ_ENTITIES)) {
        validationResponse = validateOptions(cmd, SERVICE_ROOT, BEARER_TOKEN, ENTITY_NAME, LIMIT, OUTPUT_FILE);
      } else if (action.matches(ACTIONS.SAVE_RAW_GET_REQUEST)) {
        validationResponse = validateOptions(cmd, SERVICE_ROOT, BEARER_TOKEN, URI, OUTPUT_FILE);
      } else if (action.matches(ACTIONS.CONVERT_EDMX_TO_OAI)) {
        validationResponse = validateOptions(cmd, INPUT_FILE);
      }

      if (validationResponse != null) {
        printErrorMsgAndExit("ERROR: the following options are required when using " + APP_OPTIONS.ACTIONS.GET_METADATA
            + "\n" + validationResponse + "\n\n");
      }
    }

    /**
     * Validates options passed to the command line
     * @param cmd a reference to the command line instance
     * @param options a list of options to validate
     * @return an error string containing a formatted message when validation fails, otherwise null (valid)
     */
    private static String validateOptions(CommandLine cmd, String... options) {
      StringBuilder sb = new StringBuilder();
      Arrays.stream(options).forEach(option -> {
        if (!cmd.hasOption(option)) {
          sb.append("\t --");
          sb.append(option);
          sb.append(" is required!");
        }
      });
      return sb.length() == 0 ? null : sb.toString();
    }

    /**
     * Gets the set of supported application options.
     * @return the options to be used within the application.
     */
    public static Options getOptions() {
      // create Options
      Option hostNameOption = Option.builder()
          .argName("s").longOpt(SERVICE_ROOT).hasArg()
          .desc("Service root URL on the host.")
          .build();

      Option bearerTokenOption = Option.builder()
          .argName("b").longOpt(BEARER_TOKEN).hasArg()
          .desc("Bearer token to be used with the request.")
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
          .desc("URI for raw request.")
          .build();

      Option filterOption = Option.builder()
          .argName("f").longOpt(FILTER).hasArg()
          .desc("If <filter> is passed, then readEntities will use it.")
          .build();

      Option limit = Option.builder()
          .argName("l").longOpt(LIMIT).hasArg()
          .desc("The number of records to fetch, or -1 to fetch all.")
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
          .addOption(Option.builder().argName("m").longOpt(ACTIONS.GET_METADATA)
              .desc("fetches metadata from <serviceRoot> using <bearerToken> and saves results in <outputFile>.").build())
          .addOption(Option.builder().argName("r").longOpt(ACTIONS.READ_ENTITIES)
              .desc("reads <entityName> from <serviceRoot> using <bearerToken> and saves results in <outputFile>.").build())
          .addOption(Option.builder().argName("g").longOpt(ACTIONS.GET_ENTITY_SET)
              .desc("executes GET on <uri> using the given <serviceRoot> and <bearerToken>.").build())
          .addOption(Option.builder().argName("v").longOpt(ACTIONS.VALIDATE_METADATA)
              .desc("validates previously-fetched metadata in the <inputFile> path.").build())
          .addOption(Option.builder().argName("w").longOpt(ACTIONS.SAVE_RAW_GET_REQUEST)
              .desc("performs GET from <requestURI> using the given <bearerToken> and saves output to <outputFile>.").build())
          .addOption(Option.builder().argName("c").longOpt(ACTIONS.CONVERT_EDMX_TO_OAI)
              .desc("converts EDMX in <inputFile> to OAI, saving it in <inputFile>.swagger.json").build());

      return new Options()
          .addOption(helpOption)
          .addOption(hostNameOption)
          .addOption(bearerTokenOption)
          .addOption(inputFileOption)
          .addOption(outputFileOption)
          .addOption(limit)
          .addOption(entityName)
          .addOption(useEdmEnabledClient)
          .addOption(uriOption)
          .addOption(filterOption)
          .addOption(contentType)
          .addOptionGroup(actions);
    }
  }
}
