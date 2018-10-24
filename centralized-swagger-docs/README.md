# The Problem
As we already know, it is very easy to document REST applications using the SpringFox Swagger-UI library, but a problem arises when we are working in an environment where we have multiple REST-based applications. Typically, we face this issue in a microservices environment. Most of us end up managing a separate Swagger-UI for each application, which means that each service will have its own endpoint and to access the Swagger-UI and we have to use a different URL for different applications.

# Solution
- Get the list of registered service instances from the service registry.
- For each registered service instance, pull the Swagger definition JSON from the instance and store it locally. In our case, we are putting this JSON in the in-memory documentation context backed by a concurrent map.
- Refresh the in-memory context at regular intervals to automatically remove/add the definitions as they are updated in the service registry.
- Provide a single endpoint to serve Swagger definitions from our in-memory store on the basis of service instance name.

##  This repositry contain below projects

* **central-docs-eureka-server:** Service registry powered by Netflix Eureka
* **employee-application** and **person-application:** REST applications with Swagger-UI enabled. You can follow [this article] (https://dzone.com/articles/monitoring-using-spring-boot-2-prometheus-amp-graf)for a step-by-step guide.
* **documentation-service:** Spring Boot-based REST application consolidating all the Swagger JSON and offering it in a single endpoint.  Please note that this component can be part of a gateway or the registry itself, but I have chosen to keep it separate. The final documentation shall be available at http://localhost:9093/swagger-ui.html.


