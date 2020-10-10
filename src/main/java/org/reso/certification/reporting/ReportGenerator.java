package org.reso.certification.reporting;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.commander.common.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
  private static final Logger LOG = LogManager.getLogger(ReportGenerator.class);
  private static final String defaultPathToJsonResults = "build/data-dictionary-1.7.json";
  private static final String outputDirectoryName = "DD-1.7-report-" + Utils.getTimestamp();

  private static final String PATH_TO_JSON_RESULTS = System.getProperty("pathToJsonResults", defaultPathToJsonResults);
  private static final boolean USE_MINIMAL_REPORT = Boolean.parseBoolean(System.getProperty("minimal", "false"));

  public static void main(String... args) {
    List<String> jsonFiles = new ArrayList<>();

    final String projectName = "RESO Data Dictionary 1.7 Certification Report";
    Configuration configuration =
        new Configuration(new File(outputDirectoryName), projectName);

    if (USE_MINIMAL_REPORT) LOG.info("Using minimal report format...");

    jsonFiles.add(USE_MINIMAL_REPORT ? filterJSONResults() : PATH_TO_JSON_RESULTS);

    ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
    reportBuilder.generateReports();
  }

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
          .replace(".json", ".minimal.json");
      Utils.createFile(outputDirectoryName, outputFilename, filteredScenarios.toString());

      return outputDirectoryName + File.separator + outputFilename;

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
