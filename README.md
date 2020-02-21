# RESO Web API Commander

[![CodeFactor](https://www.codefactor.io/repository/github/resostandards/web-api-commander/badge)](https://www.codefactor.io/repository/github/resostandards/web-api-commander)

The RESO Web API Commander is a command line Java application that uses
the Apache Olingo library to provide the following functionality:

* Get Metadata
* Validate Metadata
* Get Entity data from a Web API URL
* Save raw responses from a WEB API URL
* Convert EDMX to Swagger / OpenAPI format
* Run RESOScript Files
* Automated Web API Testing (beta)

The Web API Commander currently supports Bearer Tokens for authentication. 
Additional methods of authentication will be added through subsequent updates.

Help is available from the command line by passing `--help` OR just passing no arguments. 
Doing so displays the following information:

```
/path/to/web-api-commander$ java -jar out/web-api-commander.jar

usage: java -jar web-api-commander
    --bearerToken <b>       Bearer token to be used with the request.
    --contentType <t>       Results format: JSON (default),
                            JSON_NO_METADATA, JSON_FULL_METADATA, XML.
    --convertEDMXtoOAI      Converts EDMX in <inputFile> to OAI, saving it
                            in <inputFile>.swagger.json
    --entityName <n>        The name of the entity to fetch, e.g.
                            Property.
    --getEntities           Executes GET on <uri> using the given
                            <bearerToken> and optional <serviceRoot> when
                            --useEdmEnabledClient is specified. Optionally
                            takes a <limit>, which will fetch that number
                            of results. Pass --limit -1 to fetch all
                            results.
    --getMetadata           Fetches metadata from <serviceRoot> using
                            <bearerToken> and saves results in
                            <outputFile>.
    --help                  print help
    --inputFile <i>         Path to input file.
    --limit <l>             The number of records to fetch, or -1 to fetch
                            all.
    --outputFile <o>        Path to output file.
    --runRESOScript         Runs commands in RESOScript file given as
                            <inputFile>.
    --saveRawGetRequest     Performs GET from <requestURI> using the given
                            <bearerToken> and saves output to
                            <outputFile>.
    --serviceRoot <s>       Service root URL on the host.
    --uri <u>               URI for raw request. Use 'single quotes' to
                            enclose.
    --useEdmEnabledClient   present if an EdmEnabledClient should be used.
    --validateMetadata      Validates previously-fetched metadata in the
                            <inputFile> path.

```

## Usage

## Getting Metadata

To get metadata, use the `--getMetadata` argument with the following 
options:

```
java -jar web-api-commander.jar --getMetadata --serviceRoot <s> --bearerToken <b> --outputFile <o>
```

where `serviceRoot` is the path to the root of the OData WebAPI server.

Assuming everything goes well, metadata will be retrieved from the host 
and written to the provided `--outputFile`.

**Note**: additional validation is done after metadata have been received. 
Errors in metadata won't cause the program to terminate, but validation 
information will be displayed. Also, it's worth mentioning that some
of the validation error messages "out-of-the-box" from the Olingo
Library we're using to validate with can be pretty cryptic. Please
open an issue if you find things that need better explanations. 


## Validating Metadata stored in an EDMX file
Sometimes it's useful to validate an already-downloaded EDMX file. 

Since parsing EDMX is an incremental process, validation terminates 
_each time_ invalid items are encountered. Therefore, the workflow for 
correcting an EDMX document that contains errors would be to run the 
Commander repeatedly, fixing errors that are encountered along the way.

To validate metadata that's already been downloaded, call the Web API 
Commander with the following options:

```
java -jar web-api-commander.jar --validateMetadata --inputFile <i>
```

where `inputFile` is the path to your EDMX file. Errors will be logged 
according to the `log4j.properties` file used at runtime. 

## Getting results from a given `uri`

OData offers additional options for requesting data from a WebAPI server 
beyond just receiving the raw server response (shown in the next example).

In this case, the appropriate action is: `--getEntities`, which can be 
called as follows:

```
java -jar web-api-commander.jar --getEntities --uri <u> --bearerToken <b> --outputFile <o>
``` 

Make sure that any `uri` containing spaces or special characters is 
wrapped in 'single quotes'. 

When using the `--useEdmEnabledClient` option, results will be verified 
against Server metadata after being downloaded. If this option is chosen, 
then `--serviceRoot` is required so that the Web API Commander can pull 
the Server's metadata in addition to the results from the given `--uri`

The `getEntitySet` action also supports the `--contentType` option, 
which will change how results are written. Currently supported options 
are: `JSON`, `JSON_NO_METADATA`, `JSON_FULL_METADATA`, and `XML`.

Finally, there's an "experimental" auto-paging option which allows 
all records to be pulled from the Server. In order to use this option,
pass `--limit -1` when using `--getEntities`. In the near future,
an auto-resume feature will be added so that if something happens 
during transfer, the process will resume from the last record consumed.
 

## Getting raw results from a given `uri` using `saveRawGetRequest`

If additional processing using the OData Olingo library is not needed, 
raw requests may be issued against the server instead.

The `--saveRawGetRequest` action writes the raw response from a GET 
request to the given `--uri` from the Web API server directly to the 
given `--outputFile`.

Usage:

```
java -jar web-api-commander.jar --saveRawGetRequest --uri <u> --bearerToken <b> --outputFile <o>
```

Results are not checked against Server Metadata and are not written in 
any specific OData format.

Make sure that any `uri` containing spaces or special characters is 
wrapped in 'single quotes'. 

Note: this option is currently being rolled into `--getEntities` with 
`--contentType RAW`. Documentation will be updated once the change has 
been made.
  

## Converting metadata from EDMX format to Open API / Swagger 2.0 format

The WebAPI Commander also supports converting files in EDMX format to 
OpenAPI / Swagger 2.0 format. This gives servers an alternative 
representation besides the OData-specific representation used by EDMX. 

It's worth mentioning that translation from EDMX to OpenAPI/Swagger is 
*lossy*, meaning that some EDMX elements will not be translated. This 
is due to the fact that EDMX is more specific than OpenAPI, for instance with type 
representations like Integers.

The EDMX converter may be called as follows:

```
java -jar web-api-commander.jar --convertEDMXtoOAI --inputFile <i>
``` 

Any errors will be displayed, and the output file is automatically created by appending `.swagger.json` to
the given EDMX `inputFile` name.


## Running RESOScript Files
The Web API Commander is able to run RESO's XML-based scripting format, otherwise known as a RESOScript.

In order to run an RESOScript file, use a command similar to the following:

```/path/to/web-api-commander$ java -jar out/web-api-commander.jar --runRESOScript --i /path/to/your/inputFile --useEdmEnabledClient```

Notice that the EDM Enabled client has been requested in the above command. This turns on strict OData checking, which 
performs additional validation on query strings as well as schema validation on responses, among other things. 
This feature is optional when using the `--runRESOScript` option, and may be omitted. The recommendation is to use it.

When executing the Web API Commander, a results directory will be created as a sibling
to the RESOScript file being run, with the directory name being generated from the RESOScript filename
and the current timestamp. 

Within this directory will be a file for each RESOScript request that was run,
and those that generated errors will have ".ERROR" appended to them. Error files contain the request that 
was made as well as the Java exception that was thrown, which most frequently comes from the underlying
OLingo library and provides a sufficient amount of information to determine what occurred with the query.

For those wanting more information, a `log4j.properties` file may be created (as shown below), or you may 
use the DEBUG build of the application located in `/build/libs/` identified by `-DEBUG` in the Commander jar's file name.

RESOScript files contain zero or more Settings, Parameters, and Requests. For example:
```
<?xml version="1.0" encoding="utf-8" ?>
   <OutputScript>
     <ClientSettings>
       <ServerName></ServerName>
       <ServerId></ServerId>
       <WebAPIURI></WebAPIURI>
       <AuthorizationURI></AuthorizationURI>
       <TokenURI></TokenURI>
       <RedirectURI></RedirectURI>
       <AuthenticationType></AuthenticationType>
       <BearerToken></BearerToken>
       <ClientIdentification></ClientIdentification>
       <ClientSecret></ClientSecret>
       <UserName></UserName>
       <Password></Password>
       <ClientScope></ClientScope>
       <Version></Version>
       <Preauthenticate></Preauthenticate>
     </ClientSettings>
     <Parameters>
       <Parameter Name="EndpointMetadata" Value="https://yourserver.com/api/$metadata" />
     </Parameters>
     <Requests>
       <Request OutputFile="myTestOutputFile.json"         Url="*Parameter_EndpointMetadata*" />
       <!-- ... additional requests -->    
     </Requests>
   </OutputScript>
```

The XML DTD for this schema is as follows:

```dtd
<!DOCTYPE OutputScript [
  <!ELEMENT OutputScript (ClientSettings|Parameters|Requests)*>
  <!ELEMENT ClientSettings (WebAPIURI|AuthenticationType|BearerToken|ClientScope|Version|Preauthenticate)*>
  <!ELEMENT WebAPIURI (#PCDATA)>
  <!ELEMENT AuthenticationType (#PCDATA)>
  <!ELEMENT BearerToken (#PCDATA)>
  <!ELEMENT ClientScope (#PCDATA)>
  <!ELEMENT Version (#PCDATA)>
  <!ELEMENT Preauthenticate (#PCDATA)>
  <!ELEMENT Parameters (Parameter)*>
  <!ELEMENT Parameter (#PCDATA)>
  <!ATTLIST Parameter
    Name CDATA #REQUIRED
    Value CDATA #REQUIRED>
  <!ELEMENT Requests (Request)*>
  <!ELEMENT Request (#PCDATA)*>
  <!ATTLIST Request
    AssertResponseCode CDATA #IMPLIED
    Capability CDATA #REQUIRED
    MetallicLevel CDATA #REQUIRED
    OutputFile CDATA #REQUIRED
    RequirementId CDATA #REQUIRED
    TestDescription CDATA #REQUIRED
    Url CDATA #REQUIRED
    WebAPIReference CDATA #REQUIRED>
  ]>
```

## Automated Web API Testing (beta)

Currently in development is the ability for the Commander to be able to perform fully-automated Web API testing, 
upon being provided a valid RESOScript file with parameters for the given server. 
See [the generic RESOScript template for more info](./generic.resoscript).

### Cucumber Feature Specifications

[Cucumber](https://cucumber.io) is being used to describe acceptance criteria in a higher-level DSL
rather than encapsulating all of the test logic code. Cucumber's DSL is called [Gherkin](https://cucumber.io/docs/gherkin/) 
and essentially allows backing test code to be organized in a logical manner that makes sense to analysts as well as 
programmers.

Testing output during runtime has been designed to be easy to read and during each step, the relevant 
output for the step will be displayed in the terminal or in an IDE if you have chosen to use the testing tool 
there. This can often be useful if debugging tests as a developer can step through backing test code as its running
and inspect requests and responses in a controlled manner. 

### Testing Environment

Under the hood, [Gradle](https://gradle.org/) is being used for automation. It works across multiple platforms 
and is friendly with both Docker and Cucumber so that tests may be automated on CI/CD platforms such as Jenkins, 
Circle CI, Travis, or similar, and emit standard system codes during regression testing. 
 
It also provides pleasing command line interaction, and plays well with Cucumber by supporting the 
ability to run individual or multiple tests using tags.

### Usage

 Once [Gradle is installed](https://gradle.org/install/), the Commander may be run in automated testing mode for a Web API 1.0.2 certification using
 a terminal. A command similar to the following would be issued (adjust to your specific needs):

```shell script
$ gradle testWebAPIServer_1_0_2 -DpathToRESOScript=/path/to/your.resoscript
```

This will run the entirety of the tests against the Web API server provided as `WebAPIURI` in `your.resoscript` file.


To filter by tags, a command similar to the following would be used:

```shell script
$ gradle testWebAPIServer_1_0_2 -DpathToRESOScript=/path/to/your.resoscript -Dcucumber.filter.tags="@core"
```

This would run only the tests marked as `@core` in the 
[Web API Server 1.0.2 `.feature` file](./src/main/java/org/reso/certification/features/web-api-server-1.0.2.feature).


There is still some "glue code" to back the [test descriptions 
in `.feature` files](./src/main/java/org/reso/certification/features), but it is greatly optimized by the use 
of [cucumber-jvm](https://github.com/cucumber/cucumber-jvm), which has support for the reuse of backing Java 
code to cut down on copypasta test development. This library is bundled with the Commander and there is no need to install it.
The backing test code is done using [JUnit5](https://junit.org/junit5/). Normally, only those who are contributing 
test code should need to know about the implementation details of how tests are run.

*Note*: tests are currently tagged with their Web API version being 1.0.3, such as `@REQ-WA103-END3`, 
but the tests currently being run on the server for Web API 1.0.2 is the backwards-compatible subset of 
Web API 1.0.3 tests. Tags are still a work in progress, and are being added for Web API 1.0.2 tests as well. 
Please feel free to suggest additional tags that might be useful.   

### Program Output

A sample of the runtime terminal output follows:

```
    @REQ-WA103-END3 @core @x.y.z @core-support-endorsement
    Scenario: REQ-WA103-END3 - CORE - Request and Validate Server Metadata   
    
    Using RESOScript: /path/to/your.resoscript
      Given a RESOScript file was provided                                   
    
    RESOScript loaded successfully!
      And Client Settings and Parameters were read from the file             
    
    Bearer token loaded... first 4 characters: abcd
    Service root is: https://api.server.com/serviceRoot
      And an OData client was successfully created from the given RESOScript 
    
    Request URI: https://api.server.com/serviceRoot/$metadata?$format=application/xml
    Request succeeded...185032 bytes received.
      When a GET request is made to the resolved Url in "REQ-WA103-END3"     
    
    Response code is: 200
      Then the server responds with a status code of 200                     
    
    Response is valid XML!
      And the response is valid XML                                          
    
    Metadata is valid!
      And the metadata returned is valid   
```

This shows configuration parameters, requests, and responses in a lightweight-manner. 

Detailed information will be added to a local `./commander.log` file at runtime.


## Docker

A [Dockerfile](./Dockerfile) has been provided to dockerize the application. 
This can be used for CI/CD environments such as Jenkins or TravisCI. The following command will build an image for you:

```
docker build -t darnjo/web-api-command .
```

The usage for the docker container is the same for `web-api-commander.jar` presented above.

```
docker run -it darnjo/web-api-commander --help
```

If you have input files you may need to mount your filesystem into the docker container

```
docker run -it -v $PWD:/app darnjo/web-api-commander --validateMetadata --inputFile <pathInContainer>
```

---

## Logging

In the current release of the Commander, the default logging level has been set to `INFO` rather than `ALL` or `DEBUG`. 

For instance, to log output to a file rather than piping the output of what's on the console to a file, the path to your custom settings file can be specified by passing a command line arg. There's more information about log4j.properties file [here](https://logging.apache.org/log4j/2.x/manual/configuration.html).

In this case, you'd want a log4j.properties file (name is arbitrary) similar to the following:

```
log4j.rootLogger=ALL, Console
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.conversionPattern=%m%n
```
which you can then pass to the Commander as follows:

```
 java -Dlog4j.configuration=file:/path/to/your/log4j.properties -jar web-api-commander.jar <... remaining arguments>
```

It's important to note that `-Dlog4j.configuration=file:/path/to/your/log4j.properties` _must_ contain a path to the file. In the case above, the file was in the same directory as the Java executable, but you'll need to change that if you're using a different directory.

---

Please contact [josh@reso.org](mailto:josh@reso.org) with any questions, bug reports, or feature requests.

**Coming Soon**: 
* Fully-automated Data Dictionary certification (in-progress)
* Support for authentication options in addition to Bearer tokens (Client Credentials in beta, please email for more info).
* Parallel fetch for replication
* Job Scheduling
* Excel export
