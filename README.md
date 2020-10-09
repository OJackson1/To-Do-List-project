# To-Do-List-project

###  [Jira](https://ojackson.atlassian.net/secure/RapidBoard.jspa?projectKey=SP&rapidView=3&atlOrigin=eyJpIjoiYjVmMDg3MDg4MGZhNDY4YmExY2FkZTllZjVmYzFiZTUiLCJwIjoiaiJ9)

Coverage: 61.4%
# Project Title

To Do List Web Application

Built with the following starters:

- Spring Web
- H2 Database
- Spring Data JPA
- Lombok

Requires the following Maven dependencies:

- [ModelMapper](https://mvnrepository.com/artifact/org.modelmapper/modelmapper/2.3.8)
- [Swagger-UI v3.0](https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter/3.0.0)

Requirements (if using Eclipse):

- [Spring Tool Suite](https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4)
- [Lombok](https://projectlombok.org/setup/eclipse)

### Purpose

To create an OOP-based web application, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during QA training.

### Presentation

Owen Jackson [Presentation](----)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

•	Version Control System: Git 
•	Source Code Management: GitHub 
•	Kanban Board: Jira Board 
•	Database Management System: Local instance of MySQL Server 5.7 
•	Back-End Programming Language: Java 
•	API Development Platform: Spring 
•	Front-End Web Technologies: HTML, CSS, JavaScript 
•	Build Tool: Maven 
•	Unit Testing: JUnit, Mockito 
•	UI Testing: Selenium

## API

- Runs on `localhost:8905` as default
- H2 console is accessible at the path `/h2` with JDBC URL `jdbc:h2:mem:TDL` and default username/password
- Swagger UI showing API endpoints is accessible at the path `/swagger-ui/index.html`

### Installing

1) Clone this Git repository and open it as Maven build in your IDE.

2) In src/main/resources, open up the the application-dev.properties and application-prod.properties. From here you need to check the server.port:8905 is free or change to a port which is free on your computer.

3) Right click on the the TDL project and click run as Spring Boot App.

## Running the tests

The tests are located under src/test/java.

The JUnit 

Mocktio 

Selenium
  
  
Using an IDE you can run these tests by right-clicking the classes and selecting "Run as JUnit test" or right-click
on the TDL folder and click "Run as JUnit test" to run whole application test.

To run these tests in your IDE, right-click the classes and select "Run as JUnit test".  
  
If you are using eclipse, you can right-click the project folder and select 'Coverage As...'.

If you are using Spring Tool Suite, you need to click Help, then in Eclipse Marketplace search and Install EclEmma Java Code Coverage. Restart your IDE and from there you can you can right-click the project folder and select 'Coverage As...'.


## Front-End Built with

* [Visual Studio Code](https://code.visualstudio.com/)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Built in 

* [Spring Tool Suite] (https://spring.io/tools)

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Owen Jackson** - *Creater* 
[Owen Jackson](https://github.com/OJackson1)


## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thanks to Nick, Alan and Vinesh for all the help
