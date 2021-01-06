
# RESO Web API Commander

[![CodeFactor](https://www.codefactor.io/repository/github/resostandards/web-api-commander/badge)](https://www.codefactor.io/repository/github/resostandards/web-api-commander)  ![Java CI with Gradle](https://github.com/RESOStandards/web-api-commander/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)

The RESO Web API Commander is an OData client library and command-line client, as well as an automated RESO Certification testing tool.

## Getting Started
To begin using the Commander, choose one of the following topics:
* [Command-line Web API Tools](#command-line-web-api-tools)
* [RESO Certification](#reso-certification)
* [Commander as a Web API client library](#using-the-commander-as-a-web-api-client-library)


# Command-line Web API Tools
**Introduction**
* [Java Requirements](#java-requirements)  
* [Display Help](#display-help)  
* [Authentication](#authentication)  

**Client Commands**
* [Getting Metadata](#getting-metadata)  
* [Validating Metadata stored in an EDMX file](#validating-metadata-stored-in-an-edmx-file)  
* [Saving Results from a Given `uri`](#saving-results-from-a-given-uri)
* [Displaying Queries for RESOScript Files](#displaying-queries-for-resoscript-files)
* [Running RESOScript Files](#running-resoscript-files)


**Additional Commands**
* [Generating RESO Data Dictionary Acceptance Tests](#generating-reso-data-dictionary-acceptance-tests)
* [Generating RESO Data Dictionary Reference Metadata](#generating-reso-data-dictionary-reference-metadata)
* [Converting metadata to Open API 3 format](#converting-metadata-to-open-api-3-format)  

## Java Requirements
Your operating system probably already has a Java Runtime Environment (JRE) installed. This is all you need to run the Commander as a Web API Client. 

To check your version of Java, type the following in a command line environment:
```
$ java -version
```
If you have the Java SE Runtime Environment installed, the output will look similar to the following:
```
$ java -version
Java version "1.8.x" (or a higher version)
Java<TM> SE Runtime Environment ...
```
If you don't see something like this, you need to install the [Java SE](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) runtime.

Once the Java SE Runtime is installed, you may [download the Commander JAR file](build/libs/web-api-commander.jar) 

## Display Help

Assuming [you've downloaded `web-api-commander.jar`](build/libs/web-api-commander.jar) at this point, help is available from the command line by passing `--help` or just passing no arguments, as follows:
```
$ java -jar path/to/web-api-commander.jar
```

Doing so displays the following information:
```
usage: java -jar web-api-commander
    --bearerToken <b>             Bearer token to be used with the
                                  request.
    --contentType <t>             Results format: JSON (default),
                                  JSON_NO_METADATA, JSON_FULL_METADATA,
                                  XML.
    --entityName <n>              The name of the entity to fetch, e.g.
                                  Property.
    --generateDDAcceptanceTests   Generates acceptance tests in the
                                  current directory.
    --generateMetadataReport      Generates metadata report from given
                                  <inputFile>.
    --generateReferenceEDMX       Generates reference metadata in EDMX
                                  format.
    --getMetadata                 Fetches metadata from <serviceRoot>
                                  using <bearerToken> and saves results in
                                  <outputFile>.
    --help                        print help
    --inputFile <i>               Path to input file.
    --outputFile <o>              Path to output file.
    --runRESOScript               Runs commands in RESOScript file given
                                  as <inputFile>.
    --saveGetRequest              Performs GET from <requestURI> using the
                                  given <bearerToken> and saves output to
                                  <outputFile>.
    --serviceRoot <s>             Service root URL on the host.
    --uri <u>                     URI for raw request. Use 'single quotes'
                                  to enclose.
    --validateMetadata            Validates previously-fetched metadata in
                                  the <inputFile> path.
```
When using commands, if required arguments aren't provided, relevant feedback will be displayed in the terminal.

## Authentication
The RESO Commander only supports passing OAuth2 "Bearer" tokens from the command line at this time. For those using OAuth2 Client Credentials, please see the section on _[Running RESOScript files](#running-resoscript-files)_.


## Getting Metadata
To get metadata from a given server, use the `--getMetadata` argument with the following 
options:

```
$ java -jar path/to/web-api-commander.jar --getMetadata --serviceRoot https://api.server.com/serviceRoot --outputFile metadata.xml --bearerToken abc123
```

where `serviceRoot` is the path to the _root_ of the OData WebAPI server.

Assuming everything goes well, metadata will be retrieved from the host 
and written to the provided `--outputFile`, and the following output will be displayed:
```
Requesting metadata from: https://api.server.com/serviceRoot/$metadata
Metadata request succeeded.
```

## Validating Metadata stored in an EDMX file
Sometimes it's useful to validate a local OData XML Metadata (EDMX) file. 

Since parsing EDMX is an incremental process, validation terminates _each time_ invalid items are encountered. Therefore, the workflow for correcting an EDMX document that contains errors would be to run the 
Commander repeatedly, fixing errors that are encountered along the way.

To validate metadata that's already been downloaded, call Commander with the following options, 
adjusting the `path/to/web-api-commander.jar` and `--inputFile` path for your environment accordingly:
```
$ java -jar path/to/web-api-commander.jar --validateMetadata --inputFile '/src/main/resources/RESODataDictionary-1.7.edmx' 
```
XML or OData validation errors will be displayed if any issues were found. If successful, the following message 
should appear: 
```
Checking Metadata for validity...
Valid Metadata!
```

## Saving Results from a Given `uri`
The `--saveGetRequest` action makes a request to a `--uri` using a given  `--bearerToken`, and saves the response to the given `--outputFile`.

For example:
```
$ java -jar build/libs/web-api-commander.jar --saveGetRequest --uri 'https://api.server.com/OData/Property?$filter=ListPrice gt 100000&$top=100' --bearerToken abc123 --outputFile response.json
```
If the response is successful, it will be written to the specified file and the following will be displayed on the console:
```
JSON Data fetched from: https://api.server.com/OData/Property?$filter=ListPrice gt 100000&top=100"
	with response code: 200
JSON Response saved to: response.json
```
Otherwise, errors will be displayed showing what went wrong during the request.


## Displaying Queries for RESOScript Files
A RESOScript file usually contains a server's service root and one or more Requests that can either 
be used in batch-format or can be used during testing.

To resolve all parameters and display the queries to be run with your RESOScript, use the following command:

```
$ java -jar web-api-commander.jar --generateQueries --inputFile /path/to/your.resoscript
```

This should display something similar to the following:

```
==============================================================
Web API Commander Starting... Press <ctrl+c> at any time to exit.
==============================================================
Displaying 44 Request(s)
RESOScript: src/test/resources/mock.web-api-server.core.1.0.2.resoscript
==============================================================


===========================
Request: #1
===========================
Request Id: metadata-validation
Resolved URL: https://api.reso.org/OData/$metadata

  
===========================
Request: #2
===========================
Request Id: fetch-by-key
Resolved URL: https://api.reso.org/OData/Property('12345')?$select=ListingKey

...
```

## Running RESOScript Files
The Web API Commander is able to run files written using RESO's XML-based scripting format, also known as a RESOScript.

In order to run an RESOScript file, use a command similar to the following:

```
$ java -jar out/web-api-commander.jar --runRESOScript --inputFile /path/to/your/inputFile
```

A results directory will be created from the RESOScript name and timestamp when it was run, and output will be shown as the requests are made. 

Results will be saved to the filenames specified in the given RESOScript, and error files will be created when there are exceptions, with an ".ERROR" extension appended to them. 

**RESOScript File Format**
For examples of files using the RESOScript format, see:
* [Data Dictionary 1.7 RESOScript Template](sample-data-dictionary.1.7.0.resoscript)
* [Web API Core 1.0.2 RESOScript Template](sample-web-api-server.core.1.0.2.resoscript)


## Generating RESO Data Dictionary Acceptance Tests
The RESO Commander can be used to generate Data Dictionary acceptance tests from the currently approved [Data Dictionary Spreadsheet](src/main/resources/RESODataDictionary-1.7.xlsx). 

The Commander project's copy of the sheet needs to be updated with a copy of the [DD Google Sheet](https://docs.google.com/spreadsheets/d/1SZ0b6T4_lz6ti6qB2Je7NSz_9iNOaV_v9dbfhPwWgXA/edit?usp=sharing) prior to generating reference metadata.

```
$ java -jar path/to/web-api-commander.jar --generateDDAcceptanceTests
```
New Cucumber BDD acceptance tests will be generated and placed in a timestamped directory relative to your current path.

To update the current tests, copy the newly generated ones into the [Data Dictionary BDD `.features` directory](src/main/java/org/reso/certification/features/data-dictionary/v1-7-0), run the `./gradlew build` task, and if everything works as expected, commit the newly generated tests. 

## Generating RESO Data Dictionary Reference Metadata
In addition to generating DD acceptance tests, the RESO Commander can generate reference metadata based on the current reference [Data Dictionary Spreadsheet](src/main/resources/RESODataDictionary-1.7.xlsx). 

```
$ java -jar path/to/web-api-commander.jar --generateReferenceEDMX --inputFile=src/main/resources/RESODataDictionary-1.7.xlsx
```
In order to update the Commander's version of the reference metadata, update the local copy of the [DD Google Sheet](https://docs.google.com/spreadsheets/d/1SZ0b6T4_lz6ti6qB2Je7NSz_9iNOaV_v9dbfhPwWgXA/edit?usp=sharing) _prior to_ generating metadata, replace [the local copy](src/main/resources/RESODataDictionary-1.7.edmx), and try running automated acceptance tests with `./gradlew build`.


## Converting metadata to Open API 3 format
See documentation regarding running the [nodejs-based tools in odata-openapi/lib/README.md](odata-openapi/lib/README.md).

---

# RESO Certification
* [Java and the JDK](#java-and-the-jdk)
* [Cloning Commander Repository](#cloning-commander-repository)
* [Cucumber Feature Specifications](#cucumber-feature-specifications)
* [Testing Environment](#testing-environment) 
* [Gradle Wrapper](#gradle-wrapper)
* [Automated RESO Web API Core Testing](#automated-reso-web-api-core-testing)
* [Automated RESO Data Dictionary Testing](#automated-reso-data-dictionary-testing)


## Java and the JDK
To run the Commander as an _automated testing tool_, the Java JDK must be installed. The Commander has been tested with JDK 1.8 and 10 at this point. Those using JDK 11+, please [report issues](https://github.com/RESOStandards/web-api-commander/issues) if they arise.

To see whether you have the JDK installed, type the following using your local command line environment:
```
$ java -version
```
If you have a Java JDK installed, your output will look something like:
```
$ java -version
openjdk version "1.8.0_275"
OpenJDK Runtime Environment (build 1.8.0_275-8u275-b01-0ubuntu1~20.10-b01)
OpenJDK 64-Bit Server VM (build 25.275-b01, mixed mode)
```
If you don't see something like this, you need to install the JDK:
* [Open JDK is recommended](https://openjdk.java.net/install/index.html).
* [Oracle's SE Development kit may also be used](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html), but there may be additional licensing terms to accept.

## Cloning Commander Repository
The Commander may be run in automated testing mode using a terminal. Automated testing assumes that you have a Java 1.8+ JDK installed, as mentioned elsewhere in this [`README`](#java-and-the-jdk).

First, change into the directory you want to work in and clone the Commander repository. 

You will need to have Git installed. Chances are you already do, to check, open a command line and type `git` and if it's present, it will print some info about the app. If not, [there are installation instructions here](https://git-scm.com/downloads).

##### MacOS or Linux
```
$ git clone https://github.com/RESOStandards/web-api-commander.git
```

##### Windows
```
C:\> git clone https://github.com/RESOStandards/web-api-commander.git
```

This will clone the repository into a directory called web-api-commander relative to whatever directory you're currently in, which also means you'll have a fresh copy of the latest code to execute. 

To refresh the code after you have downloaded it, issue the command `$ git pull` in the root of the directory that was just created. 

## Cucumber Feature Specifications

[Cucumber](https://cucumber.io) is being used to describe acceptance criteria in a higher-level DSL rather than encapsulating all of the test logic code. Cucumber's DSL is called [Gherkin](https://cucumber.io/docs/gherkin/) and essentially allows backing test code to be organized in a logical manner that makes sense to analysts as well as programmers.

## Testing Environment

Under the hood, [Gradle](https://gradle.org/) is being used for automation. It works across multiple platforms and is friendly with both Docker and Cucumber so that tests may be automated on CI/CD platforms such as Jenkins, Circle CI, Travis, or similar, and emit standard system codes during regression testing. 

## Gradle Wrapper
The [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) provides a convenient way to automatically download Gradle when running tests. 

After you have cloned the repository, as shown in [a previous step](#cloning-commander-repository), change into the directory containing the source code from GitHub. Convenience methods have been provided for the various certification tasks. 

## Gradle Tasks
Once the Gradle Wrapper is set up, you should be able to run the `./gradlew tasks` command in from the root of the Commander source directory in a terminal window and see the list of available tasks. 
```
$ ./gradlew tasks

> Task :tasks

------------------------------------------------------------
Tasks runnable from root project
------------------------------------------------------------
...
```
There are both _built-in tasks_ and _RESO tasks_.  
The following section is what's of interest here:
```
RESO Certification tasks
------------------------
generateCertificationReport_DD_1_7 - Runs Data Dictionary 1.7 tests and creates a certification report
  RESOScript Example:
    ./gradlew generateCertificationReport_DD_1_7 -DpathToRESOScript=/path/to/dd17.resoscript -Dminimal=true -Dstrict=true
  Metadata File Example:
    ./gradlew generateCertificationReport_DD_1_7 -DpathToMetadata=/path/to/RESODataDictionary-1.7.edmx -Dminimal=true -Dstrict=true
  To enable strict mode, pass -Dstrict=true. All applicants MUST pass strict mode tests to be certified.

testDataDictionary_1_7 - Runs Data Dictionary 1.7 Automated Acceptance Tests and generates a "raw" report.
  RESOScript Example:
    ./gradlew testDataDictionary_1_7 -DpathToRESOScript=/path/to/dd17.resoscript -DshowResponses=true -Dcucumber.filter.tags=""
  Metadata File Example:
    ./gradlew testDataDictionary_1_7 -DpathToMetadata=/path/to/RESODataDictionary-1.7.edmx -Dcucumber.filter.tags=""
  To enable strict mode, pass -Dstrict=true. All applicants MUST pass strict mode tests to be certified.

testDataDictionaryReferenceMetadata_1_7 - Runs Data Dictionary tests against reference metadata

testWebApiServer_1_0_2_Core - Runs Web API Core 1.0.2 Automated Acceptance Tests.
  Example: 
    $ ./gradlew testWebApiServer_1_0_2_Core -DpathToRESOScript=/path/to/web-api-core-1.0.2.resoscript -DshowResponses=true

    Note: by default the Web API tests assume Collection(Edm.EnumType).
      Pass -DuseCollections=false if using OData IsFlags.
```
 
## Automated RESO Web API Core Testing
Automated Web API Core automated testing tools are currently in development. See [Issue 34](https://github.com/RESOStandards/web-api-commander/issues/34) for progress.

To use the automated RESO testing tools, you must have a [JDK installed](#java-and-the-jdk).

### Web API Core RESOScript Template
To use the Commander for automated Web API Core testing, you need a RESOScript.

For Web API 1.0.2 Server Core Certification, use [this resoscript](sample-web-api-server.core.1.0.2.resoscript) as a template. 

For more information regarding Parameters and Client Settings, see the [Web API Walkthrough](https://github.com/RESOStandards/web-api-commander/wiki/Walkthrough:-Automated-Web-API-Certification-Using-the-RESO-Commander#configuring-the-resoscript-file) (in-progress).

### Web API Cucumber Acceptance Tests
The Cucumber BDD acceptance tests for Web API 1.0.2 Core certification are [here](https://github.com/RESOStandards/web-api-commander/blob/issue-37-data-dictionary-testing/src/main/java/org/reso/certification/features/web-api/web-api-server.core.1.0.2.feature).  If you have any questions, please [send us an email](mailto:dev@reso.org).

### Gradle Tasks for Web API 1.0.2 Server Certification
While you may use tags to filter tests as you choose, explained in the next section, it's convenient
to be able to run a predefined set of tests Web API Core Certification. 

These tasks will also produce reports in the local `build` directory, named according to which test you ran. 

#### Core Certification

This will run the Core tests against the Web API 1.0.2 Server provided as `WebAPIURI` in your `web-api-server.core.1.0.2.resoscript` file.

**Note**: by default, the Commander uses `Collection(Edm.EnumType)` for multiple enumerations testing. 
Pass `-DuseCollections=false` if you are using `IsFlags="true"` instead.

##### MacOS or Linux
```
$ ./gradlew testWebApiServer_1_0_2_Core -DpathToRESOScript=/path/to/your.web-api-server.core.1.0.2.resoscript -DshowResponses=true
```

##### Windows
```
C:\path\to\web-api-commander> gradlew testWebApiServer_1_0_2_Core -DpathToRESOScript=C:\path\to\your.web-api-server.core.1.0.2.resoscript -DshowResponses=true
```

*Note: the first time you run these tasks, they will take some time as the environment must be configured and code is being compiled from the contents of the source directory downloaded in the previous step. 

### Web API Program Output

A sample of the runtime terminal output follows:

```gherkin
> Task :testWebApiServer_1_0_2_Core

@metadata-request @2.4.1
Scenario: REQ-WA103-END3 - Request and Validate Server Metadata                                            

Using RESOScript: ./web-api-server.core.1.0.2.resoscript
  Given a RESOScript file was provided                                                                     

RESOScript loaded successfully!
  And Client Settings and Parameters were read from the file                                               

Bearer token loaded... first 4 characters: test
Service root is: https://api.yourserver.com/OData
  And a test container was successfully created from the given RESOScript                                  

Authentication Type: authorization_code
  And the test container uses an authorization_code or client_credentials for authentication               

Requesting XML Metadata from service root at: https://api.yourserver.com/OData
  When XML Metadata are requested from the service root in "ClientSettings_WebAPIURI"                      

Asserted Response Code: 200, Server Response Code: 200
  Then the server responds with a status code of 200                                                       

Reported OData-Version header value: '4.0'
  And the server has an OData-Version header value of "4.0" or "4.01"                                      

Validating XML Metadata response to ensure it's valid XML and matches OASIS OData XSDs...
See: https://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/schemas/
XMLMetadata string is valid XML!
  And the XML Metadata response is valid XML                                                               

Validating XML Metadata response to ensure it's valid XML and matches OASIS OData XSDs...
See: https://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/schemas/
XMLMetadata string is valid XML!
XML Metadata is valid!
Edm Metadata is valid!
  And the XML Metadata returned by the server are valid                                                    

  And the XML Metadata returned by the server contains Edm metadata                                        

  And the Edm metadata returned by the server are valid                                                    

Found Default Entity Container: 'Default'
  And the metadata contains a valid service document                                                       

Resource Name: Property
Allowed Resources: Property, Member, Office, Contacts, ContactListings, HistoryTransactional, InternetTracking, Media, OpenHouse, OUID, Prospecting, Queue, Rules, SavedSearch, Showing, Teams
  And the given "Parameter_EndpointResource" resource exists within "Parameter_DD17_WellKnownResourceList" 

Found EntityContainer for the given resource: 'Property'
  And the metadata contains the "Parameter_EndpointResource" resource                                      

Searching the default entity container for one of the following Standard Resources: Property, Member, Office, Media
Standard Resource Names requirement met!
  And the metadata contains at least one resource from "Parameter_WebAPI102_RequiredResourceList"          


1 Scenarios (1 passed)
15 Steps (15 passed)
0m4.093s       
```

Detailed information will be added to a local `commander.log` file at runtime.

---
## Automated RESO Data Dictionary Testing
The Commander provides automated Data Dictionary 1.7 acceptance testing for RESO Certification. The DD 1.7 testing specification is available [here](https://docs.google.com/document/d/15DFf9kDX_mlGCJVOch2fztl8W5h-yd18N0_03Sb4HwM/edit?usp=sharing).

* [Data Dictionary RESOScript Template](#data-dictionary-resoscript-template)
* [Data Dictionary Acceptance Tests](#data-dictionary-acceptance-tests)
* [Gradle Tasks for Data Dictionary Certification](#gradle-tasks-for-data-dictionary-certification)
  * [Test Data Dictionary](#test-data-dictionary)
  * [Generate Data Dictionary Certification Report](#generate-data-dictionary-certification-report)

To use the RESO Commander for Data Dictionary testing, you must have the JDK installed and a local copy of the Commander repository. See [RESO Certification](#reso-certification) before proceeding.

### Data Dictionary RESOScript Template
To use the Commander for automated Data Dictionary testing, you need a RESOScript.

For Data Dictionary 1.7 Certification, use [this resoscript](sample-data-dictionary.1.7.0.resoscript) as a template. 

### Data Dictionary Acceptance Tests
RESO Data Dictionary Certification is driven off of the official Data Dictionary spreadsheet for each version of the dictionary, [currently DD 1.7](https://docs.google.com/spreadsheets/d/1SZ0b6T4_lz6ti6qB2Je7NSz_9iNOaV_v9dbfhPwWgXA/edit?usp=sharing). 

Cucumber BDD acceptance tests are [automatically generated](#generating-reso-data-dictionary-acceptance-tests) from the [local copy of the approved spreadsheet](src/main/resources/RESODataDictionary-1.7.xlsx). 

The generated Data Dictionary 1.7 Cucumber BDD tests are [located in this directory](https://github.com/RESOStandards/web-api-commander/tree/issue-37-data-dictionary-testing/src/main/java/org/reso/certification/features/data-dictionary/v1-7-0).  See the [property.feature file](src/main/java/org/reso/certification/features/data-dictionary/v1-7-0/property.feature), for example, for the RESO Property Resource acceptance tests.

If you have any questions, please [send us an email](mailto:dev@reso.org).

### Gradle Tasks for Data Dictionary Certification
There are predefined tasks for automated RESO Data Dictionary Certification using the Commander. These can be displayed using [Gradle Tasks](#gradle-tasks) as well.

* [Test Data Dictionary 1.7](#test-data-dictionary)
* [Generate Data Dictionary 1.7 Certification Report](#generate-data-dictionary-certification-report)

_Note: the first time you run these tasks, they will take some time as the environment must be configured and code is being compiled from the contents of the source directory downloaded in the previous step._

#### Test Data Dictionary
This task tests for Data Dictionary compliance and generates a raw report in a timestamped local directory.

There are two ways to run automated testing to check for RESO compliant Web API metadata:
* using a local metadata file
* using a RESOScript file to fetch metadata from a given server

While RESOScript files and the use of strict mode are required for RESO Certification. In both cases, metadata are validated and then processed for RESO compliance. 

##### Data Dictionary Testing using Local Metadata
The Commander allows for a local metadata file to be specified. Not only is this used for internal acceptance testing, but is useful for developers to troubleshoot metadata locally while working on compliance. 

The Gradle task to validate local metadata can be run using the following command:

```
$ ./gradlew testDataDictionary_1_7 -DpathToMetadata=/path/to/RESODataDictionary-1.7.edmx
```
You may also pass a `-Dstrict=true` flag to see whether the given metadata file would pass Certification. 

A raw report will be generated in a timestamped directory, and a `commander.log` will be generated during runtime. 

##### Data Dictionary Testing using a Data Dictionary RESOScript
During Certification, metadata are retrieved directly from an applicant's Web API server using either OAuth2 Bearer Tokens or Client Credentials. Either authentication option is currently available for RESO Certification, depending on configuration, and the applicant will provide working RESOScripts when they apply for certification.

An example Data Dictionary RESOScript template can be found [here](sample-data-dictionary.1.7.0.resoscript).

Once a RESOScript file has been created, it may be used with the following command:

```
$ ./gradlew testDataDictionary_1_7 -DpathToRESOScript=/path/to/dd17.resoscript -DshowResponses=true
```
You may also pass a `-Dstrict=true` flag to see whether the given metadata file would pass Certification. 

A raw report will be generated in a timestamped directory, and a `commander.log` will be generated during runtime. 

#### Generate Data Dictionary Certification Report
This task tests for Data Dictionary compliance and generates both a raw report and a RESO Certification report in a timestamped directory.

Similar to the [Test Data Dictionary 1.7](#test-data-dictionary-1.7) task, the report generator can be run for both local metadata or used with a RESOScript. 

For the purposes of Certification, a Certification Report MUST be generated using a RESOScript using strict mode. But it's useful to be able to produce certification reports with any local files as well.

##### Certification Reports using Local Metadata
A RESO Certification report can be generated for local metadata by using the following commmand:
```
$ ./gradlew generateCertificationReport_DD_1_7 -DpathToMetadata=src/main/resources/RESODataDictionary-1.7.edmx -Dminimal=true -Dstrict=true --continue
```
Note the use of the `--continue` argument. 

You may remove the `-Dstrict=true` flag, but it will be required for RESO Certification. 

A "pretty" Certification report will be generated in a timestamped directory in addition to the normal raw report. 

##### Certification Reports using a Data Dictionary RESOScript
A RESO Certification report can be generated using a RESOScript by using the following command: 
```
$ ./gradlew generateCertificationReport_DD_1_7 -DpathToRESOScript=/path/to/dd1.7.resoscript -Dminimal=true -Dstrict=true --continue
```
You may remove the `-Dstrict=true` flag, but it will be required for RESO Certification. 

A "pretty" Certification report will be generated in a timestamped directory in addition to the normal raw report. 

### Data Dictionary Testing Output
To see examples of Data Dictionary testing output, you may use the `./gradlew testDataDictionaryReferenceMetadata_1_7` command to run the Data Dictionary acceptance tests on the RESO reference metadata. 

There is additional documentation about how Data Dictionary testing works, including sample output, in the [RESO Data Dictionary 1.7 Testing Specification](https://docs.google.com/document/d/15DFf9kDX_mlGCJVOch2fztl8W5h-yd18N0_03Sb4HwM/edit#heading=h.rib4osorsdcx).

## Advanced feature: Tag Filtering 
You may filter by tags in any of the Web API or Data Dictionary tests. These are the items in the Cucumber .feature files prefixed by an `@` symbol. Expressions may also be used with tags. This README doen't cover how to use tags, but the Commander supports them. For more information, see the [Cucumber Documentation](https://cucumber.io/docs/cucumber/api/#tags).

#### Examples

**Run Web API Core Metadata Tests Only**
```
$ gradle testWebAPIServerCore_1_0_2 -DpathToRESOScript=/path/to/your.web-api-server.core.1.0.2.resoscript -Dcucumber.filter.tags="@metadata"
```

**Run Data Dictionary Tests on IDX Fields Only**
```
$ ./gradlew testDataDictionary_1_7 -DpathToRESOScript=/path/to/your/dd1.7.resoscript -DshowResponses=true -Dcucumber.filter.tags="@IDX"
```

## Docker

A [Dockerfile](./Dockerfile) has been provided to dockerize the application. 
This can be used for CI/CD environments such as Jenkins or TravisCI. The following command will build an image for you:


### Commander Features Other Than Automated Web API Testing
```
$ docker build -t web-api-commander .
```

The usage for the docker container is the same for `web-api-commander.jar` presented above.

```
$ docker run -it web-api-commander --help
```

If you have input files you may need to mount your filesystem into the docker container

```
$ docker run -it -v $PWD:/app web-api-commander --validateMetadata --inputFile <pathInContainer>
```

### Automated Web API Testing

You may also run the tests in a Docker container locally by issuing one of the following commands. 
Docker must be running on your local machine.

#### MacOS or Linux All-In-One Commands
```
cd ~; \
rm -rf commander-tmp/; \
mkdir commander-tmp; \
cd commander-tmp; \
git clone https://github.com/RESOStandards/web-api-commander.git; \
cd web-api-commander; \
docker run --rm -u gradle -v "$PWD":/home/gradle/project -v /path/to/your/resoscripts:/home/gradle/project/resoscripts -w /home/gradle/project gradle gradle testWebAPIServer_1_0_2_Core -DpathToRESOScript=/home/gradle/project/resoscripts/your.web-api-server.core.1.0.2.resoscript -DshowResponses=true
```

Note that this will create a directory in your home directory for the project, and build artifacts and the log will be placed in that directory, 
which is also where you will end up after runtime.


#### Windows All-In-One WIP
```
cd C:\;mkdir commander-tmp;cd commander-tmp;git clone https://github.com/RESOStandards/web-api-commander.git;cd web-api-commander; docker run --rm -u gradle -v C:\current\path\web-api-commander:/home/gradle/project -v C:\path\to\your\resoscripts:/home/gradle/project/resoscripts -w /home/gradle/project gradle gradle testWebAPIServer_1_0_2_Core -DpathToRESOScript=/home/gradle/project/resoscripts/your.web-api-server.core.1.0.2.resoscript -DshowResponses=true
```

---
## Using the Commander as a Web API Client Library
Java or Scala developers may also use the Commander as a client library, which uses the Apache Olingo library under the hood but adds things like OAuth2 support and data retrieval, validation, and serialization methods. To do so, include the [standalone Web API Commander Jar](build/libs/web-api-commander.jar) in your projects. Feel free to open issues or feature requests in the [Commander GitHub project](https://github.com/RESOStandards/web-api-commander/issues).

---
## Logging

In the current version of the Commander, two logs are produced. One is outputted in the terminal at `INFO` level during runtime through `stdout`. A detailed log called `commander.log` will be outputted at runtime and will contain details down to the wire requests. 

Log4j 2 is being used under the hood for logging, and a configuration file may be found [here](https://github.com/RESOStandards/web-api-commander/blob/master/src/main/resources/log4j2.xml). Multiple outputs may be used, including posting to collectors or sending emails. [See Log4j 2 docs for more info](https://logging.apache.org/log4j/2.x/manual/index.html). 

Gradle may be debugged as well, and additional gradle commands such as turning on full gradle step logging are discussed in [Gradle Commands](#gradle-commands).

---

## Support
Please contact [Josh](mailto:josh@reso.org) with any questions, bug reports, or feature requests. Contributions to code or documentation are welcome. 

You may also [open a ticket](https://github.com/RESOStandards/web-api-commander/issues).