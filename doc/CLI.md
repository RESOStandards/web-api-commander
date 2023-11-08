# Command-Line Tools
The RESO Commander offers the following command line utilities:
* Getting Metadata
* Validating XML Metadata
* Requesting and Saving Results
* Running RESOScript Files
* Displaying RESOScript Testing Queries

In order to run the RESO Commander locally, you must have the Java Runtime Environment (JRE) version 
8, 10, or 12 installed. 

You may also use [Docker](/doc/Docker.md) if you prefer.

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

After downloading the [latest `web-api-commander.jar` file from GitHub](build/libs/web-api-commander.jar), help is available from the command line by passing `--help` or just passing no arguments, as follows:
```
$ java -jar path/to/web-api-commander.jar
```

Doing so displays the following information:
```
usage: java -jar web-api-commander
    --bearerToken <b>              Bearer token to be used with the
                                   request.
    --clientId <d>                 Client Id to be used with the request.
    --clientSecret <s>
    --contentType <t>              Results format: JSON (default),
                                   JSON_NO_METADATA, JSON_FULL_METADATA,
                                   XML.
    --entityName <n>               The name of the entity to fetch, e.g.
                                   Property.
    --generateDDAcceptanceTests    Generates acceptance tests in the
                                   current directory.
    --generateMetadataReport       Generates metadata report from given
                                   <inputFile>.
    --generateQueries              Resolves queries in a given RESOScript
                                   <inputFile> and displays them in
                                   standard out.
    --generateReferenceDDL         Generates reference DDL to create a
                                   RESO-compliant SQL database. Pass
                                   --useKeyNumeric to generate the DB
                                   using numeric keys.
    --generateReferenceEDMX        Generates reference metadata in EDMX
                                   format.
    --getMetadata                  Fetches metadata from <serviceRoot>
                                   using <bearerToken> and saves results
                                   in <outputFile>.
    --help                         print help
    --inputFile <i>                Path to input file.
    --outputFile <o>               Path to output file.
    --runRESOScript                Runs commands in RESOScript file given
                                   as <inputFile>.
    --saveGetRequest               Performs GET from <requestURI> using
                                   the given <bearerToken> and saves
                                   output to <outputFile>.
    --serviceRoot <s>              Service root URL on the host.
    --uri <u>                      URI for raw request. Use 'single
                                   quotes' to enclose.
    --useEdmEnabledClient          present if an EdmEnabledClient should
                                   be used.
    --useKeyNumeric                present if numeric keys are to be used
                                   for database DDL generation.
    --validateMetadata             Validates previously-fetched metadata
                                   in the <inputFile> path.

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
$ java -jar path/to/web-api-commander.jar --validateMetadata --inputFile '/src/main/resources/RESODataDictionary-1.7.xml' 
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
RESOScript: src/test/resources/mock.web-api-server.core.2.0.0.resoscript
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
* [Data Dictionary RESOScript Template](../sample-data-dictionary.resoscript)
* [Web API Core RESOScript Template](../sample-web-api-server.core.resoscript)

