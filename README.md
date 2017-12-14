# Sous-chef
Web application project for kitchen management and recipe suggestions

## Contents
[Prerequisites](#prerequisites)

[Running Locally](#running-locally)

### Prerequisites

| Prerequisite                                | Version |
| ------------------------------------------- | ------- |
| [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html) | `~ ^1.8.x`  |
| [Node.js](http://nodejs.org)                | `~ ^6.9.x`  |
| npm (comes with Node)                       | `~ ^3.x.x`  |
| @angular/cli                                 | `latest`|

### Running Locally

To run the ui, navigate to the `sous-chef-ui` directory and run `npm start`

To run the backend in IntelliJ, navigate to `sous-chef-server/src/main/java/server/SousChefRunner` and select the arrow to the left of the class definition

To run the backend in the command line, navigate to the `sous-chef-server` directory and run `mvn spring-boot:run`
**Note:** Spring Boot recommends exporting the following when running from the command line: `export MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=128M`
