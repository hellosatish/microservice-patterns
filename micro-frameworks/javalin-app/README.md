# This is a sample CRUD application for a sample entity (Person) having below model

``` JSON
    {
        "id":1,
        "firstName":"Satish",
        "lastName":"Sharma"
    }
```
# How to run
    This is simple java application. You can run the main class, or you can package this application and run that as jar

# [sample-requests](https://github.com/hellosatish/microservice-patterns/tree/master/micro-frameworks/sample-requests) folder contains postman collection for the requests. You can import that in Postman and test this application


# My observations till now

## The javalin doesn't ship with inbuilt logger. so if you see any warning like below.

```
Missing dependency 'Slf4j simple'. Add the dependency.
```
you will need to add a logger in classpath. For Slf4J use below maven cordinates

```
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.7.26</version>
</dependency>
```

## By default it doesnt ships with JSON mappers so you need to add JSON mapper. For jackson mapper use belwo snippet

```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.9</version>
</dependency>
```