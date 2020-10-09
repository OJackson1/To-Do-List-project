# To-Do-List-project

Coverage: 61.4%
# Project Title

### To Do List Web Application

[Project Jira Board](https://ojackson.atlassian.net/secure/RapidBoard.jspa?projectKey=SP&rapidView=3&atlOrigin=eyJpIjoiYjVmMDg3MDg4MGZhNDY4YmExY2FkZTllZjVmYzFiZTUiLCJwIjoiaiJ9)

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

<<<<<<< HEAD
Owen Jackson [Presentation](https://qalearning.sharepoint.com/:p:/r/sites/20AugSDET2-Comms-TeamAir/_layouts/15/doc2.aspx?sourcedoc=%7B38D1B013-E88C-4F51-A0C1-3AEB6A205D70%7D&file=To-Do-List%20Project%202.pptx&action=edit&mobileredirect=true&wdPreviousSession=bc445782-c0a6-4ac4-b0f0-c6a83ffef521&wdOrigin=TEAMS-ELECTRON.teams.undefined)
=======
Owen Jackson [Presentation](https://qalearning.sharepoint.com/:p:/r/sites/20AugSDET2-Comms-TeamAir/_layouts/15/Doc.aspx?sourcedoc=%7B03682CE2-2B9F-4EB8-83C1-DFA884C872A6%7D&file=IMS%20project.pptx&action=edit&mobileredirect=true&wdPreviousSession=f7493b11-3eaf-4573-97ca-8339192f873f&wdOrigin=TEAMS-ELECTRON.teams.undefined)
>>>>>>> dev

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

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

For the tests, JUnit and Mockito are used for unit and intergration tests whereas Selenium is used for UI testing of the front-end.

Unit - testing where individual units / components of a software are tested. The purpose is to validate that each unit of the software performs as designed.
Test example: 

    @Test
    void createTest() {

        when(this.repository.save(this.testTask)).thenReturn(this.testTaskWithId);

        when(this.modelMapper.map(this.testTaskWithId, TaskDTO.class)).thenReturn(this.taskDTO);

        TaskDTO expected = this.taskDTO;
        TaskDTO actual = this.service.create(this.testTask);
        assertThat(expected).isEqualTo(actual);

        verify(this.repository, times(1)).save(this.testTask);
    }

Integration -  testing where individual units / components are combined and tested as a group. The purpose of this level of testing is to expose faults in the interaction between integrated units.
Test example:  	    

	    @Test
	    void testCreate() {
	    	
	    	when(this.modelMapper.map(this.testTaskWithId, TaskDTO.class))
	    	.thenReturn(this.testTaskDTO);
	    	
	        assertThat(this.testTaskDTO)
	            .isEqualTo(this.service
	            .create(testTask));
	    }

Selenium - an open-source automated testing framework used to validate web applications across different browsers and platforms.
Test example:

	    @Test
	    public void UitestCreate() throws InterruptedException {
	        driver.get("http://localhost:8905/index.html");
	        Thread.sleep(1000);
	        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
	        Thread.sleep(1000);
	        assertThat("Create A Task").isEqualTo(driver.getTitle());
	    }
  
  
Using an IDE you can run these tests by right-clicking the classes and selecting "Run as JUnit test" or right-click
on the TDL folder and click "Run as JUnit test" to run whole application test.

To run these tests in your IDE, right-click the classes and select "Run as JUnit test".  
  
If you are using eclipse, you can right-click the project folder and select 'Coverage As...'.

If you are using Spring Tool Suite, you need to click Help, then in Eclipse Marketplace search and Install EclEmma Java Code Coverage. Restart your IDE and from there you can you can right-click the project folder and select 'Coverage As...'.

### Software

- Version Control System: Git 
- Source Code Management: GitHub 
- Kanban Board: Jira Board 
- Database Management System: Local instance of MySQL Server 5.7 
- Back-End Programming Language: Java 
- API Development Platform: Spring 
- Front-End Web Technologies: HTML, CSS, JavaScript 
- Build Tool: Maven 
- Unit Testing: JUnit, Mockito 
- UI Testing: Selenium

## Front-End Built with

* [Visual Studio Code](https://code.visualstudio.com/)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

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