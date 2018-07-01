# my_awesome_ms
Sample project in GO to register *[GOlang](http://golang.org)* application as a  service instance in service registry.
Currently this application supports [Eureka Registry](https://github.com/Netflix/eureka/wiki)

# Pre-Requisite to run binaries
You need to set the following environment variables
* REGISTRY_TYPE : This can contain one of the following values
  * NETFLIX_EUREKA
  * CONSUL (Work in Progress)
* REGISTRY_URL : The URL of service registry to be used for registering the application. For ex. in case of Eureka which is running in my local machine in port 8761 "http://localhost:8761/eureka/apps/"

* REGISTRY_USER : The username to be used while authentication.
* REGISTRY_PASSWORD : Password to be used while authentication.

REGISTRY_USER and REGISTRY_PASSWORD are required in case you have the security enabled in eureka instance.

# TODO
* Enable usage of username and password so that we can register with the secured service registries
* Complete service registry with * consul *
