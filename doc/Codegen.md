# Codegen
The RESO Commander CLI contains code generation for the following items:
* [Generating RESO Data Dictionary Acceptance Tests](#Generating RESO Data Dictionary Acceptance Tests)
* [Generating RESO Web API Reference Server Data Models](#Generating RESO Web API Reference Server Data Models)
* [Generating RESO Data Dictionary Reference Metadata](#Generating RESO Data Dictionary Reference Metadata)
* [Generating RESO Data Dictionary 1.7 Reference DDL](#Generating RESO Data Dictionary 1.7 Reference DDL)
* [Converting OData XML Metadata to Open API 3 Format](#Converting OData XML Metadata to Open API 3 Format)

## Generating RESO Data Dictionary Acceptance Tests
The RESO Commander can be used to generate Data Dictionary acceptance tests from the currently approved [Data Dictionary Spreadsheet](src/main/resources/RESODataDictionary-1.7.xlsx). 

The Commander project's copy of the sheet needs to be updated with a copy of the [DD Google Sheet](https://docs.google.com/spreadsheets/d/1SZ0b6T4_lz6ti6qB2Je7NSz_9iNOaV_v9dbfhPwWgXA/edit?usp=sharing) prior to generating reference metadata.

```
$ java -jar path/to/web-api-commander.jar --generateDDAcceptanceTests
```
New Cucumber BDD acceptance tests will be generated and placed in a timestamped directory relative to your current path.

To update the current tests, copy the newly generated ones into the [Data Dictionary BDD `.features` directory](src/main/java/org/reso/certification/features/data-dictionary/v1-7-0), run the `./gradlew build` task, and if everything works as expected, commit the newly generated tests. 

## Generating RESO Web API Reference Server Data Models
The RESO Commander can be used to generate data models for the Web API Reference server from the currently approved [Data Dictionary Spreadsheet](src/main/resources/RESODataDictionary-1.7.xlsx). 

The Commander project's copy of the sheet needs to be updated with a copy of the [DD Google Sheet](https://docs.google.com/spreadsheets/d/1SZ0b6T4_lz6ti6qB2Je7NSz_9iNOaV_v9dbfhPwWgXA/edit?usp=sharing) prior to generating reference metadata.

```
$ java -jar path/to/web-api-commander.jar --generateResourceInfoModels
```
New ResourceInfo Models for the Web API Reference Server will be generated and placed in a timestamped directory relative to your current path.


## Generating RESO Data Dictionary Reference Metadata
In addition to generating DD acceptance tests, the RESO Commander can generate reference metadata based on the current reference [Data Dictionary Spreadsheet](src/main/resources/RESODataDictionary-1.7.xlsx). 

```
$ java -jar path/to/web-api-commander.jar --generateReferenceEDMX --inputFile=src/main/resources/RESODataDictionary-1.7.xlsx
```
In order to update the Commander's version of the reference metadata, update the local copy of the [DD Google Sheet](https://docs.google.com/spreadsheets/d/1SZ0b6T4_lz6ti6qB2Je7NSz_9iNOaV_v9dbfhPwWgXA/edit?usp=sharing) _prior to_ generating metadata, replace [the local copy](src/main/resources/RESODataDictionary-1.7.xml), and try running automated acceptance tests with `./gradlew build`.

## Generating RESO Data Dictionary 1.7 Reference DDL

There is a command that can generate reference DDL for creating SQL databases using either key or key numeric values.

### String Keys

Issuing the following will print DDL in the console using String keys as the primary key:

```
$ java -jar path/to/web-api-commander.jar --generateReferenceDDL
```

This means that linked lookups will also use string keys since they'll be linked by a related table that uses string keys.

There is a variable string key size in the DDLProcessor (currently 64 characters in length).

Numeric keys are still present in this case, they're just not the primary key. 


### Numeric Keys

Issuing the following will print DDL in the console using Numeric keys as the primary key:

```
$ java -jar path/to/web-api-commander.jar --generateReferenceDDL --useKeyNumeric
```

In this case, `BIGINT` values will be used for all related lookup values. 

### DDL TODO

The following items need to be added to the DDL generator still:
 - [ ] Foreign Key relationships.
 - [x] Creation of Lookup resource. 


## Converting OData XML Metadata to Open API 3 Format
See documentation regarding running the [nodejs-based tools in odata-openapi/lib/README.md](odata-openapi/lib/README.md).


