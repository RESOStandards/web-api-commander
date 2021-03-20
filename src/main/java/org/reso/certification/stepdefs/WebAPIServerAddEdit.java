package org.reso.certification.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebAPIServerAddEdit {

  @Then("the test is skipped if the server responds with a status code of {int}")
  public void theTestIsSkippedIfTheServerRespondsWithAStatusCodeOf(int arg0) {
  }

  @When("the server responds with one of the following status codes")
  public void theServerRespondsWithOneOfTheFollowingStatusCodes() {
  }

  @Then("the response header {string} is {string}")
  public void theResponseHeaderIs(String arg0, String arg1) {
  }

  @And("the response header {string} is present")
  public void theResponseHeaderIsPresent(String arg0) {
  }

  @And("the response header {string} is a valid URL")
  public void theResponseHeaderIsAValidURL(String arg0) {
  }

  @When("the server responds with a {int} status code {string} in the JSON response")
  public void theServerRespondsWithAStatusCodeInTheJSONResponse(int arg0, String arg1) {
  }

  @When("the server responds with a {int} status code {string} {string} in the JSON response")
  public void theServerRespondsWithAStatusCodeInTheJSONResponse(int arg0, String arg1, String arg2) {
  }

  @When("the server responds with a {int} status code {string} {string} {string} in the JSON response")
  public void theServerRespondsWithAStatusCodeInTheJSONResponse(int arg0, String arg1, String arg2, String arg3) {
  }

  @When("the server responds with a {int} status code data from {string} {string} in the JSON response")
  public void theServerRespondsWithAStatusCodeDataFromInTheJSONResponse(int arg0, String arg1, String arg2) {
  }

  @When("a {string} request is made to the response header {string} URL")
  public void aRequestIsMadeToTheResponseHeaderURL(String arg0, String arg1) {
  }

  @And("the response has header {string} with one of the following values")
  public void theResponseHasHeaderWithOneOfTheFollowingValues(String arg0) {
  }

  @And("the JSON response contains the data in {string}")
  public void theJSONResponseContainsTheDataIn(String arg0) {
  }

  @And("the JSON response matches the format advertised in the metadata")
  public void theJSONResponseMatchesTheFormatAdvertisedInTheMetadata() {
  }

  @And("request data has been provided in {string}")
  public void requestDataHasBeenProvidedIn(String arg0) {
  }

  @And("request data in {string} is valid JSON")
  public void requestDataInIsValidJSON(String arg0) {
  }

  @And("schema in {string} matches the metadata")
  public void schemaInMatchesTheMetadata(String arg0) {
  }

  @When("a {string} request is made to the {string} URL with data in {string}")
  public void aRequestIsMadeToTheURLWithDataIn(String arg0, String arg1, String arg2) {
  }

  @Then("the server responds with one of the following error codes")
  public void theServerRespondsWithOneOfTheFollowingErrorCodes() {
  }

  @And("the error response is in a valid format")
  public void theErrorResponseIsInAValidFormat() {
  }

  @And("the values in the {string} field in the JSON payload {string} path are contained within the metadata")
  public void theValuesInTheFieldInTheJSONPayloadPathAreContainedWithinTheMetadata(String arg0, String arg1) {
  }

  @And("the request header {string} is {string}")
  public void theRequestHeaderIs(String arg0, String arg1) {
  }

  @And("the request header {string} contains {string}")
  public void theRequestHeaderContains(String arg0, String arg1) {

  }
}
