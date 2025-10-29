<img width="500" height="151" alt="image" src="https://github.com/user-attachments/assets/4fc04bbc-6e73-4168-8a68-97faa9e47281" />

# RESO Commander

[![CodeFactor](https://www.codefactor.io/repository/github/resostandards/web-api-commander/badge)](https://www.codefactor.io/repository/github/resostandards/web-api-commander)  ![Java CI with Gradle](https://github.com/RESOStandards/web-api-commander/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=main)

[**VIEW ON GITHUB**](https://github.com/RESOStandards/web-api-commander)

The RESO Commander is an OData client Java library and command-line client, 
as well as an automated RESO Certification testing tool.

# RESO Certification
The RESO Commander is only used for Web API Core testing and will be deprecated in the future in favor of the RESO SDK. 

[**MORE INFO**](/doc/Certification.md)

_For Data Dictionary certification, see [**RESO Certification Utils**](https://github.com/RESOStandards/reso-certification-utils?tab=readme-ov-file#reso-data-dictionary-certification)._

# Command-Line OData Web API Tools
The RESO Commander contains command line tools for working with OData Web APIs. 
This section covers how to validate metadata, make requests, and generate sample requests for testing. 

[**MORE INFO**](/doc/CLI.md)

# Web API Client Library
The RESO Commander offers a convenient wrapper around the Apache Olingo Java client for OData, 
with added support for OAuth2 Bearer Tokens and Client Credentials. 

The client library can be used to fetch and validate metadata, fetch data, and perform other 
OData client tasks. 

[**MORE INFO**]((/doc/ODataClient.md))

# Docker
For those who prefer Docker, both the Command Line and Automated Testing Tools can be 
run in a Docker container. 

[**MORE INFO**](/doc/Docker.md)

# Codegen
The RESO Commander may also be used to generate reference XML Metadata, DDL, database seeds,
automated acceptance tests, reference server models, and to convert XML Metadata to Open API 3 format.

[**MORE INFO**](/doc/Codegen.md)

---

[**Questions? Contact RESO**](mailto:dev@reso.org)
