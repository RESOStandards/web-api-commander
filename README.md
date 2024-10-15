
# RESO Commander

[![CodeFactor](https://www.codefactor.io/repository/github/resostandards/web-api-commander/badge)](https://www.codefactor.io/repository/github/resostandards/web-api-commander)  ![Java CI with Gradle](https://github.com/RESOStandards/web-api-commander/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=main)

[**VIEW ON GITHUB**](https://github.com/RESOStandards/web-api-commander)

The RESO Commander is an OData client Java library and command-line client, 
as well as an automated RESO Certification testing tool.

# Getting Started
To begin using the RESO Commander, choose one of the following topics:

## [RESO Certification](/doc/Certification.md)
One of the Commander's primary uses is as an automated testing tool. This section
explains how to run the following tests:

* Data Dictionary
* Web API Core

**Note**: Data Dictionary testing is now handled by the [**RESO Certification Utils**](https://github.com/RESOStandards/reso-certification-utils) command-line utility. The Commander still works for metadata validation, but will be retired in the near future and the new tools merged into this repository. 

## [Command-Line OData Web API Tools](/doc/CLI.md)
The RESO Commander contains command line tools for working with OData Web APIs. 
This section covers how to validate metadata, make requests, and generate sample requests for testing. 

## [Web API Client Library](/doc/ODataClient.md)
The RESO Commander offers a convenient wrapper around the Apache Olingo Java client for OData, 
with added support for OAuth2 Bearer Tokens and Client Credentials. 

The client library can be used to fetch and validate metadata, fetch data, and perform other 
OData client tasks. 

## [Docker](/doc/Docker.md)
For those who prefer Docker, both the Command Line and Automated Testing Tools can be 
run in a Docker container. 

## [Codegen](/doc/Codegen.md)
The RESO Commander may also be used to generate reference XML Metadata, DDL, database seeds,
automated acceptance tests, reference server models, and to convert XML Metadata to Open API 3 format.
