# RESO Certification
The RESO Commander is the basis for automated Data Dictionary, Payloads, and Web API Certification.

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
* [OpenJDK 8, 10, or 11 are recommended](https://openjdk.java.net/install/index.html).
* [Oracle's SE Development kit may also be used](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html), but there may be additional licensing terms to accept.

**Note**: there are known issues with Java/JDK 14+ and Groovy. The recommended JDK versions are 8 (1.8), 10, or 11. OpenJDK is preferred. If you're using MacOS you can install the JDK [using Homebrew](https://formulae.brew.sh/formula/openjdk@11). There are instructions for Windows and Linux [here](https://jdk.java.net/java-se-ri/11).


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
testDataAvailability_1_7 - Data Dictionary 1.7 Data Availability Tests
Example:
  ./gradlew testDataAvailability_1_7 -DpathToRESOScript=/path/to/web-api-core-2.0.0.resoscript
[Report location: build/certification/reports]


testDataDictionary_1_7 - Data Dictionary 1.7 Acceptance Tests
RESOScript Example:
  ./gradlew testDataDictionary_1_7 -DpathToRESOScript=/path/to/dd17.resoscript -DshowResponses=true -Dstrict=true

Metadata File Example:
  ./gradlew testDataDictionary_1_7 -DpathToMetadata=/path/to/RESODataDictionary-1.7.xml

To disable strict mode, remove the -Dstrict=true parameter. All applicants MUST pass strict mode tests to be certified!

[Report location: build/certification/reports]


testIdxPayload_1_7 - Data Dictionary 1.7 Payloads Sampling Tests
Example:
  ./gradlew testIdxPayload_1_7 -DpathToRESOScript=/path/to/web-api-core-2.0.0.resoscript

[Report location: build/certification/reports]


testWebApiCore_2_0_0 - Web API Core 2.0.0 Acceptance Tests
Example: 
  ./gradlew testWebApiCore_2_0_0 -DpathToRESOScript=/path/to/web-api-core-2.0.0.resoscript -DshowResponses=true

Note: by default the Web API tests assume Collection(Edm.EnumType).
Pass -DuseCollections=false if using OData IsFlags.

[Report location: build/certification/reports]

```
 
## Automated RESO Web API Core Testing
To use the automated RESO testing tools, you must have a [JDK installed](#java-and-the-jdk).

### Web API Core RESOScript Template
To use the Commander for automated Web API Core testing, you need a RESOScript.

For Web API 2.0.0 Server Core Certification, use [this resoscript](https://github.com/RESOStandards/web-api-commander/blob/main/sample-web-api-server.core.2.0.0.resoscript) as a template. 

For more information regarding Parameters and Client Settings, see the [Web API Walkthrough](https://github.com/RESOStandards/web-api-commander/wiki/Configuring-the-RESO-Commander-for-Automated-Web-API-Core-Testing).

### Web API Cucumber Acceptance Tests
The Cucumber BDD acceptance tests for Web API 2.0.0 Core certification are [here](https://github.com/RESOStandards/web-api-commander/blob/main/src/main/java/org/reso/certification/features/web-api/web-api-server.core.feature).  If you have any questions, please [send us an email](mailto:dev@reso.org).

### Gradle Tasks for Web API 2.0.0 Server Certification
While you may use tags to filter tests as you choose, explained in the next section, it's convenient
to be able to run a predefined set of tests Web API Core Certification. 

These tasks will also produce reports in the local `/build/certification` directory, relative to the project root, named according to which test you ran. 

#### Core Certification

This will run the Core tests against the Web API 2.0.0 Server provided as `WebAPIURI` in your `web-api-server.core.2.0.0.resoscript` file.

**Note**: by default, the Commander uses `Collection(Edm.EnumType)` for multiple enumerations testing. 
Pass `-DuseCollections=false` if you are using `IsFlags="true"` instead.

##### MacOS or Linux
```
$ ./gradlew testWebApiCore_2_0_0 -DpathToRESOScript=/path/to/your.web-api-server.core.2.0.0.resoscript -DshowResponses=true
```

##### Windows
```
C:\path\to\web-api-commander> gradlew testWebApiCore_2_0_0 -DpathToRESOScript=C:\path\to\your.web-api-server.core.2.0.0.resoscript -DshowResponses=true
```

*Note: the first time you run these tasks, they will take some time as the environment must be configured and code is being compiled from the contents of the source directory downloaded in the previous step. 

### Web API Program Output

A sample of the runtime terminal output follows:

```gherkin
> Task :testWebApiCore_2_0_0

@metadata-request @2.4.1
Scenario: REQ-WA103-END3 - Request and Validate Server Metadata                                            

Using RESOScript: ./web-api-server.core.2.0.0.resoscript
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

For Data Dictionary 1.7 Certification, use [this resoscript](https://github.com/RESOStandards/web-api-commander/blob/main/sample-data-dictionary.1.7.0.resoscript) as a template. 

### Data Dictionary Acceptance Tests
RESO Data Dictionary Certification is driven off of the official Data Dictionary spreadsheet for each version of the dictionary, [currently DD 1.7](https://docs.google.com/spreadsheets/d/1SZ0b6T4_lz6ti6qB2Je7NSz_9iNOaV_v9dbfhPwWgXA/edit?usp=sharing). 

Cucumber BDD acceptance tests are [automatically generated](#generating-reso-data-dictionary-acceptance-tests) from the [local copy of the approved spreadsheet](https://github.com/RESOStandards/web-api-commander/blob/main/src/main/resources/RESODataDictionary-1.7.xlsx). 

The generated Data Dictionary 1.7 Cucumber BDD tests are [located in this directory](https://github.com/RESOStandards/web-api-commander/tree/main/src/main/java/org/reso/certification/features/data-dictionary/v1-7-0).  See the [property.feature file](https://github.com/RESOStandards/web-api-commander/blob/main/src/main/java/org/reso/certification/features/data-dictionary/v1-7-0/property.feature), for example, for the RESO Property Resource acceptance tests.

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
$ ./gradlew testDataDictionary_1_7 -DpathToMetadata=/path/to/RESODataDictionary-1.7.xml
```
You may also pass a `-Dstrict=true` flag to see whether the given metadata file would pass Certification. 

A raw report will be generated in a timestamped directory, and a `commander.log` will be generated during runtime. 

##### Data Dictionary Testing using a Data Dictionary RESOScript
During Certification, metadata are retrieved directly from an applicant's Web API server using either OAuth2 Bearer Tokens or Client Credentials. Either authentication option is currently available for RESO Certification, depending on configuration, and the applicant will provide working RESOScripts when they apply for certification.

An example Data Dictionary RESOScript template can be found [here](https://github.com/RESOStandards/web-api-commander/blob/main/sample-data-dictionary.1.7.0.resoscript).

Once a RESOScript file has been created, it may be used with the following command:

```
$ ./gradlew testDataDictionary_1_7 -DpathToRESOScript=/path/to/dd17.resoscript -DshowResponses=true
```
You may also pass a `-Dstrict=true` flag to see whether the given metadata file would pass Certification. 

A raw report will be generated in a timestamped directory, and a `commander.log` will be generated during runtime. 

### Data Dictionary Testing Output
To see examples of Data Dictionary testing output, you may use the `./gradlew testDataDictionaryReferenceMetadata_1_7` command to run the Data Dictionary acceptance tests on the RESO reference metadata. 

There is additional documentation about how Data Dictionary testing works, including sample output, in the [RESO Data Dictionary 1.7 Specification](https://github.com/RESOStandards/transport/blob/main/data-dictionary.md).

## Advanced feature: Tag Filtering 
You may filter by tags in any of the Web API or Data Dictionary tests. These are the items in the Cucumber .feature files prefixed by an `@` symbol. Expressions may also be used with tags. This README doen't cover how to use tags, but the Commander supports them. For more information, see the [Cucumber Documentation](https://cucumber.io/docs/cucumber/api/#tags).

#### Examples

**Run Web API Core Metadata Tests Only**
```
$ gradle testWebApiCore_2_0_0 -DpathToRESOScript=/path/to/your.web-api-server.core.2.0.0.resoscript -Dcucumber.filter.tags="@metadata"
```

**Run Data Dictionary Tests on IDX Fields Only**
```
$ ./gradlew testDataDictionary_1_7 -DpathToRESOScript=/path/to/your/dd1.7.resoscript -DshowResponses=true -Dcucumber.filter.tags="@IDX"
```
