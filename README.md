# To-Do-List-project

###  [Jira](https://ojackson.atlassian.net/secure/RapidBoard.jspa?projectKey=SP&rapidView=3&atlOrigin=eyJpIjoiYjVmMDg3MDg4MGZhNDY4YmExY2FkZTllZjVmYzFiZTUiLCJwIjoiaiJ9)

Coverage: 62.8%
# Project Title

To Do List is a Java program using MySQL.

### Presentation

Owen Jackson [Presentation](https://qalearning.sharepoint.com/:p:/r/sites/20AugSDET2-Comms-TeamAir/_layouts/15/Doc.aspx?sourcedoc=%7B5B3A8409-469D-4245-BD63-C8C7535D28B6%7D&file=IMS%20project.pptx&action=edit&mobileredirect=true)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Version Control System: Git 
Source Code Management: GitHub 
Project Management: Jira 
Database Management System: MySQL Server 5.7 
Back-End Programming Language: Java 
Build Tool: Maven Unit Testing: JUnit

### Installing

1) Clone this Git repository and open it as Maven build in your IDE.

2) Either change the 'connect()' method to your own database connection inside the src/main/java/com/qa/ims/utils/DBUtils.java file OR change the database properties method and modify src/main/java/com/qa/ims/IMS.java to pass the file location of 'properties.db' to connect().

3)Run the 'Runner.java' class under src/main/java/com/qa/ims to start the application.

## Running the tests

The tests are located under src/test.

The Junit tests cover some testing such as checking the Order and Item classes have correct equals() methods.
  
The tests for the Data-Access Objects files are a more complex ensuring objects are 
processed correctly and that the correct information is passed back. Example:

@Test
	public void testCreate() {
		final Customer created = new Customer(6L, "Leeand", "Perrins");
		assertEquals(created, DAO.create(created));  
  
Using an IDE you can run these tests by right-clicking the classes and selecting "Run as JUnit test" or right-click
on the IMS folder and click "Run as JUnit test" to run whole application test.

To run these tests in your IDE, right-click the classes and select "Run as JUnit test".  
  
If you are using eclipse, you can right-click the project folder and select 'Coverage As...'.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Jordan Harrison** - *IMS Starter* - 
[JHarry444](https://github.com/JHarry444/IMS-Starter)
* **Nick Johnson** - *Updated IMS Starter* 
[nickrstewarttds](https://github.com/nickrstewarttds/IMS-Starter)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thanks to Nick and Shafeeq for all the help
