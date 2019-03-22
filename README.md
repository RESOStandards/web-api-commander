# RESO Web API Commander

[![CodeFactor](https://www.codefactor.io/repository/github/darnjo/web-api-commander/badge)](https://www.codefactor.io/repository/github/darnjo/web-api-commander)

The RESO Web API Commander is a command line Java application that uses
the Apache Olingo library to provide the following functionality:

* Get Metadata
* Validate Metadata
* Get Entity data from a Web API URL
* Save raw responses from a WEB API URL
* Read all Entities up to a given limit
* Convert EDMX to Swagger / OAI

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
    --filter <f>            If <filter> is passed, then readEntities will
                            use it.
    --getEntitySet          executes GET on <uri> using the given
                            <bearerToken> and <outputFile>.
    --getMetadata           fetches metadata from <serviceRoot> using
                            <bearerToken> and saves results in
                            <outputFile>.
    --help                  print help
    --inputFile <i>         Path to input file.
    --limit <l>             The number of records to fetch, or -1 to fetch
                            all.
    --outputFile <o>        Path to output file.
    --readEntities          reads <entityName> from <serviceRoot> using
                            <bearerToken> and saves results in
                            <outputFile>.
    --saveRawGetRequest     performs GET from <requestURI> using the 
                            given <bearerToken> and saves the output to <outputFile>.
    --serviceRoot <s>       Service root URL on the host.
    --uri <u>               URI for raw request.
    --useEdmEnabledClient   present if an EdmEnabledClient should be used.
    --validateMetadata      validates previously-fetched metadata in the
                            <inputFile> path.

```

## Usage

## 1. Getting Metadata

To get metadata, use the `--getMetadata` argument with the following options:

```
java -jar web-api-commander.jar --getMetadata --serviceRoot <s> --bearerToken <b> --outputFile <o>
```

where `serviceRoot` is the path to the root of the OData WebAPI server.

Assuming everything goes well, metadata will be retrieved from the host and written to the provided `--outputFile`.

**Note**: additional validation is done after metadata have been received. Errors in metadata 
won't cause the program to terminate, but validation information will be displayed.

## 2. Validating Metadata stored in an EDMX file
Sometimes it's useful to validate an already-downloaded EDMX file. 

Since parsing EDMX is an incremental process, validation terminates _each time_ invalid 
items are encountered. Therefore, the workflow for correcting an EDMX document that contains errors 
would be to run the Commander repeatedly, fixing errors that are encountered along the way.

To validate metadata, call the Web API Commander with the following options:

```
java -jar web-api-commander.jar --validateMetadata --inputFile <i>
```

where `inputFile` is the path to your EDMX file. Errors will be logged according to the `log4j.properties` file
used at runtime. 

## 3. Getting results from a given `uri` using OData

OData offers additional options for requesting data from a WebAPI server beyond just receiving the 
raw server response (shown in the next example).

In this case, the appropriate action is: `--getEntitySet`, which can be called as follows:

```
java -jar web-api-commander.jar --getEntitySet --uri <u> --bearerToken <b> --outputFile <o>
``` 

When using the `--useEdmEnabledClient` option, results will be verified against Server metadata 
after being downloaded. If this option is chosen, then `--serviceRoot` is required so that the Web API
Commander can pull the Server's metadata in addition to the results from the given `--uri`

The `getEntitySet` action also supports the `--contentType` option, which will change how results are 
written. Currently supported options are: `JSON`, `JSON_NO_METADATA`, `JSON_FULL_METADATA`, and `XML`.

## 4. Getting raw results from a given `uri` using `saveGetRawRequest`

If additional processing using the OData Olingo library is not needed, raw requests may be issued 
against the server instead.

The `--saveGetRawRequest` action writes the raw response from a GET request to the given `--uri` 
from the Web API server directly to the given `--outputFile`.

Usage:

```
java -jar web-api-commander.jar --uri <u> --bearerToken <b> --outputFile <o>
```

Results are not checked against Server Metadata and are not written in any specific OData format.

  
## 5. Reading all Entities up to `limit` using `readEntities`

The `readEntities` action takes an `entityName` and reads all items up until `limit` on a given 
`serverRoot`. Paging is done behind the scenes by computing skips for each page of results.

Usage:

```
java -jar web-api-commander.jar --readEntities --inputFile <i> --outputFile <o> --limit 101 --serviceRoot --serviceRoot <s> --bearerToken <b> --entityName Property --filter "ListPrice gt 1000000" --useEdmEnabledClient
```


Once results are fetched, they're written to the given `outputFile`. Additional options are supported
as well, such as `useEdmEnabledClient`, which also requires that `serviceRoot` be passed. The EDM-enabled
client will check results against server metadata once they're downloaded. 

This action also supports serialization in different formats using the `contentType` option. The 
Content Types currently supported are: `JSON`, `JSON_NO_METADATA`, `JSON_FULL_METADATA`, and `XML`.

`readEntities` also supports `filter` expressions, as it may sometimes be useful to filter the entities
being fetched. While most command line arguments don't need to be quoted, `filter` expressions are the exception. 
See above example.

**Note**: passing `--limit -1` as an option will fetch _all_ Entities from the given `serviceRoot`.


Additional query options will eventually be added, such as `$select` and `$order`. Planned 
functionality includes a `parallel` option which will fetch multiple pages simultaneously up to `numThreads` workers.


## 6. Converting metadata from EDMX format to Open API / Swagger 2.0 format

The WebAPI Commander also supports converting files in EDMX format to Open API / Swagger 2.0 format. This 
gives servers an alternative representation besides the OData-specific representation used by EDMX. 

It's worth mentioning that translation from EDMX to OAI/Swagger is _lossy_, meaning that some EDMX elements 
will not be translated. This is due to the fact that EDMX is more specific than OAI, for instance with type 
representations like Integers.

The EDMX converter may be called as follows:

```
java -jar web-api-commander.jar --convertEDMXtoOAI --inputFile <i>
``` 

Any errors will be displayed, and the output file is automatically created by appending `.swagger.json` to
the given EDMX `inputFile` name.

---

Please contact [josh@reso.org](mailto:josh@reso.org) with any questions, bug reports, or feature requests.

**Coming Soon**: support for authentication options in addition to Bearer tokens. 


