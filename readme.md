## Solution Design

My solution consist of three tiers such as Web Service, Application Service and Repository. It was designed as a multi-tier architecture by me.

First tier is the Web Service Tier which has API methods are triggered by client is responsible for translating HTTP requests into calls to application services via interfaces. It has no other responsibility except that communicating application services and only knows the contract of application services.

Second tier is the Application Service Tier which has methods accessing repository tier. Basically, it includes basic validation and executing business logics before inserting valid data to storage.

The final tier is Repository Tier which includes methods to access data storage such as insert, update, delete and get. For this assignment, I choose InMemory data storage and it only lives as long as the application running. If the application stops, all data is lost.

Besides that, The projects also has unit test methods for reliability and integrity of the system.


## Technology Choices

For this assignment, I chose a framework called Spring to develop RESTful APIs. It also provides everything what I need to develop web applications. As a data storage, InMemory data storage which is thread-safe generic array list was chosen. It guarantee that one thread to access it at the same time. For id generation, Atomic long is used. It guarantee that no two objects of the same type will be assigned the same ID. For unit testing, JUnit Testing framework is used and also Swagger was implemented to test API methods.


## Limitations / Known Issues
* No persistence
* No logging exceptions
* No error reporting


## Building / Deployment Instructions

The service is a Java web application. To deploy it one can build and export it as a WAR file which can be used for deployment.

The service must be imported into a server with a servlet container. A popular choice of servlet-enabled server is Tomcat. Tomcat comes with a built-in servlet container (Catalina). Usually, the deployment process is as simple as copying the exported WAR file into the server's webapps directory, which is then picked up automatically.  

Some IDEs provide the option to export a project as a WAR file. However, if one needs to automate the deployment process, he/she can build and deploy the WAR file using command line, like this:

	$ cd /path/to/project
	$ jar -cvf service.war *
	$ cp service.war /path/to/tomcat/webapps
	
	
## Usage / Examples

You can find usage the API methods in Swagger. To access the Swagger UI, you should type address below;

http://{hosting-environment}/swagger-ui.html

If you build up the application in your local machine, you have to write localhost instead of hosting-environment variable.

Also, health and metrics was implemented in the application and so you can check the application healthy at regular intervals.

To check health the application;
http://{hosting-environment}/health

To check the metrics;
http://{hosting-environment}/metrics