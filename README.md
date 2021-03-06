# Sous-chef
Web application project for kitchen management and recipe suggestions

## Contents
[Prerequisites](#prerequisites)

[Running Locally](#running-locally)

[Tips and Tricks](#tips-and-tricks)

### Prerequisites

| Prerequisite                                | Version |
| ------------------------------------------- | ------- |
| [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html) | `~ ^1.8.x`  |
| [Maven](http://maven.apache.org/download.cgi) | `~ ^3.1.x`  |
| [Node.js](http://nodejs.org)                | `~ ^6.9.x`  |
| npm (comes with Node)                       | `~ ^3.x.x`  |
| @angular/cli                                 | `latest`|

### Running Locally

1. Run `mvn clean install` on the top level pom
2. `cd backend` and run `mvn spring-boot:run`
3. The app should be up at `localhost:8080`

**Note:** Spring Boot recommends exporting the following when running from the command line: `export MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=128M`

### Tips and Tricks
- Running maven install every time you make a frontend change can be quite tedious. You can [configure the project](https://blog.jdriven.com/2016/12/angular2-spring-boot-getting-started/) (Skip to Step 7) to automatically build frontend changes. You'll have to run the frontend/backend independently with this configuration, but it pays off for frontend work!