package org.reso.certification.reporting;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.commander.common.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.reso.commander.Commander.NOT_OK;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

public class CertificationReportGenerator {
  private static final Logger LOG = LogManager.getLogger(CertificationReportGenerator.class);
  private static final String PATH_TO_JSON_RESULTS = System.getProperty("pathToJsonResults", null);
  private static final String outputDirectoryName = PATH_TO_JSON_RESULTS.substring(0, PATH_TO_JSON_RESULTS.lastIndexOf(File.separator));
  private static final boolean USE_MINIMAL_REPORT = Boolean.parseBoolean(System.getProperty("minimal", "false"));
  private static final String DEFAULT_REPORT_DESCRIPTION = "Certification Report";
  private static final String projectName = System.getProperty("reportDescription", DEFAULT_REPORT_DESCRIPTION);
  private static final String MINIMAL_JSON_EXTENSION = ".minimal.json";

  public static void main(String[] args) {

    if (PATH_TO_JSON_RESULTS == null || !Files.exists(Paths.get(PATH_TO_JSON_RESULTS))) {
      LOG.error(getDefaultErrorMessage("path to JSON results does not exist!" +
          (PATH_TO_JSON_RESULTS != null ? "\npathToJsonResults=\"" + PATH_TO_JSON_RESULTS + "\"" : "")));
      System.exit(NOT_OK);
    }

//    List<String> jsonFiles = new ArrayList<>();
//    Configuration configuration = new Configuration(new File(outputDirectoryName), projectName);

    if (USE_MINIMAL_REPORT) {
      LOG.info("Using minimal report format...");
      Utils.createFile(PATH_TO_JSON_RESULTS, filterJSONResults());
    }
//    jsonFiles.add(USE_MINIMAL_REPORT ? filterJSONResults() : PATH_TO_JSON_RESULTS);

//    ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
//    reportBuilder.generateReports();

    //remove minimal report file
//    if (USE_MINIMAL_REPORT && jsonFiles.size() > 0 && jsonFiles.get(0).contains(MINIMAL_JSON_EXTENSION))
//      new File(jsonFiles.get(0)).deleteOnExit();
  }

  /**
   * Filters all trailing skipped tests where there are no subsequent errors.
   * Depends on the output of the Cucumber Reporting JSON plugin
   *
   * @return a JSON string with any trailing skipped tests removed
   */
  private static String filterJSONResults() {
    try {
      Gson gson = new Gson();
      JsonArray scenarios = gson.fromJson(new FileReader(PATH_TO_JSON_RESULTS), JsonArray.class);
      JsonArray filteredScenarios = new JsonArray();
      JsonArray filteredElements;

      int skippedStepsCount = 0, failedStepsCount = 0;

      for (JsonElement scenario : scenarios) {
        filteredElements = new JsonArray();
        JsonArray elements = scenario.getAsJsonObject().getAsJsonArray("elements").getAsJsonArray();
        for (JsonElement element : elements) {
          if (!element.getAsJsonObject().get("type").getAsString().contentEquals("background")) {
            JsonArray steps = element.getAsJsonObject().get("steps").getAsJsonArray();
            for (JsonElement step : steps) {
              JsonObject result = step.getAsJsonObject().get("result").getAsJsonObject();
              if (result.get("status").getAsString().contentEquals("skipped")) {
                skippedStepsCount++;
              }

              if (result.get("status").getAsString().contentEquals("failed")) {
                failedStepsCount++;
              }
            }
            if (skippedStepsCount == 0 || failedStepsCount > 0) {
              filteredElements.add(element);
            }
            skippedStepsCount = 0;
            failedStepsCount = 0;
          }
        }

        if (filteredElements.size() > 0) {
          final JsonObject filteredScenario = scenario.deepCopy().getAsJsonObject();
          filteredScenario.getAsJsonObject().remove("elements");
          filteredScenario.getAsJsonObject().add("elements", filteredElements);
          filteredScenarios.add(filteredScenario);
        }
      }

      String outputFilename = PATH_TO_JSON_RESULTS
          .substring(PATH_TO_JSON_RESULTS.lastIndexOf(File.separator) + 1)
          .replace(".json", MINIMAL_JSON_EXTENSION);
      return Utils.createFile(outputDirectoryName, outputFilename, filteredScenarios.toString()).toString();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
