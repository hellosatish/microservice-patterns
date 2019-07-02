# microservice-patterns
Code to share the knowledge I gained while designing and implementing micro services
Below are the projects description
1. [awesome-project](https://github.com/hellosatish/microservice-patterns/tree/master/awesomeProject) : This project demonstrates the service registry pattern.  how to register a non-java application.
2. [centralized-swagger-docs](https://github.com/hellosatish/microservice-patterns/tree/master/centralized-swagger-docs) : Swagger is un-doubtly the most used REST api documentation framework. And [swagger-ui](https://github.com/swagger-api/swagger-ui) is the implementation for Java. 

But, in case of microservices where you have multiple application and each exposes swagger document. This project is aimed at how do you consolidate all these swagger documents and expose them at one place.
The [documentation-app](https://github.com/hellosatish/microservice-patterns/tree/master/centralized-swagger-docs/documentation-app) aggregates data(swagger docs JSON) from other services and exposes a single endpoint for outer world. This application could be ocnsidered as the implemenation of Aggregator microservice pattern.

>Please note this implementation is specific to swagger-api and spring boot. However you can implement the same design in any language and tool


3. [vehicle-tracker](https://github.com/hellosatish/microservice-patterns/tree/master/vehicle-tracker) : An example of evnt based microservices. In this case we target real time event processing using [kafka-stream](https://kafka.apache.org/documentation/streams/) and spring boot. 
