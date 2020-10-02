package org.reso.certification.reporting;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.sorting.SortingMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

  public static void main(String[] args) {
    File reportOutputDirectory = new File(args[1]);
    List<String> jsonFiles = new ArrayList<>();
    jsonFiles.add(args[0]);

    String projectName = "RESO Data Dictionary 1.7 Certification Report";
    Configuration configuration = new Configuration(reportOutputDirectory, projectName);

    configuration.setSortingMethod(SortingMethod.NATURAL);
    //configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
    //configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);

    // points to the demo trends which is not used for other tests
    //configuration.setTrendsStatsFile(new File("target/test-classes/data-dictionary-trends.json"));

    ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
    reportBuilder.generateReports();
  }
}