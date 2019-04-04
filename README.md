# RESO Web API Commander

[![CodeFactor](https://www.codefactor.io/repository/github/darnjo/web-api-commander/badge)](https://www.codefactor.io/repository/github/darnjo/web-api-commander)

The RESO Web API Commander is a command line Java application that uses
the Apache Olingo library to provide the following functionality:

* Get Metadata
* Validate Metadata
* Get Entity data from a Web API URL
* Save raw responses from a WEB API URL
* Convert EDMX to Swagger / OpenAPI format

The Web API Commander currently supports Bearer Tokens for authentication. 
Additional methods of authentication will be added through subsequent updates.

Help is available from the command line by passing `--help`, which displays
the following information:

```
usage: java -jar web-api-commander
    --bearerToken <b>       Bearer token to be used with the request.
    --contentType <t>       Results format: JSON (default),
                            JSON_NO_METADATA, JSON_FULL_METADATA, XML.
    --convertEDMXtoOAI      converts EDMX in <inputFile> to OAI, saving it
                            in <inputFile>.swagger.json
    --entityName <n>        The name of the entity to fetch, e.g.
                            Property.
    --getEntities           executes GET on <uri> using the given
                            <bearerToken> and optional <serviceRoot> when
                            --useEdmEnabledClient is specified. Optionally
                            takes a <limit>, which will fetch that number
                            of results. Pass --limit -1 to fetch all
                            results.
    --getMetadata           fetches metadata from <serviceRoot> using
                            <bearerToken> and saves results in
                            <outputFile>.
    --help                  print help
    --inputFile <i>         Path to input file.
    --limit <l>             The number of records to fetch, or -1 to fetch
                            all.
    --outputFile <o>        Path to output file.
    --saveRawGetRequest     performs GET from <requestURI> using the given
                            <bearerToken> and saves output to
                            <outputFile>.
    --serviceRoot <s>       Service root URL on the host.
    --uri <u>               URI for raw request. Use 'single quotes' to enclose.
    --useEdmEnabledClient   present if an EdmEnabledClient should be used.
    --validateMetadata      validates previously-fetched metadata in the
                            <inputFile> path.
```

## Usage

## 1. Getting Metadata

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


## 2. Validating Metadata stored in an EDMX file
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

## 3. Getting results from a given `uri`

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
 

## 4. Getting raw results from a given `uri` using `saveRawGetRequest`

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
  

## 5. Converting metadata from EDMX format to Open API / Swagger 2.0 format

The WebAPI Commander also supports converting files in EDMX format to 
OpenAPI / Swagger 2.0 format. This gives servers an alternative 
representation besides the OData-specific representation used by EDMX. 

It's worth mentioning that translation from EDMX to OpenAPI/Swagger is 
_lossy_, meaning that some EDMX elements will not be translated. This 
is due to the fact that EDMX is more specific than OpenAPI, for instance with type 
representations like Integers.

The EDMX converter may be called as follows:

```
java -jar web-api-commander.jar --convertEDMXtoOAI --inputFile <i>
``` 

Any errors will be displayed, and the output file is automatically created by appending `.swagger.json` to
the given EDMX `inputFile` name.

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
* Support for authentication options in addition to Bearer tokens
* Parallel fetch
* Job Scheduling
* Excel export
 


