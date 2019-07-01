# Real time analytics with kafka streams
This projects demosntrates real time processing using kafka streams processing DSL



## Assumptions
    1. you allready have the kafka cluster setup. if not please reffer to the docs directory. Refer the section 'How to Setup single node Kafka cluster' on page 7
    2. The topic is created, which is requried by this application to function. You can use below command to create the topic 'gplsLocation'
    `kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1  --topic gpslocation` 

## Project 
 ### vehicle-tracker:
    The overall project wrapping all other applications as Modules.

 ### common-libs: 
    this will contain the POJO classes to model the different objects like Vehicle, VehicleCount, VehicleLocation. This application will be added as dependency to other two applications eliminating the concerns of model mismatch.
### vehicle-simulator: 
    the responsibility of this application will be to simulate the behavior of GPS signals being emitted by the device fitted in each vehicle.
### tracker-dashboard:
    this application will be our stream processing component. Though we can have separate application for querying the state-stores of Kafka, but for the sake of simplicity we add this functionality in this application itself.

## How to run the application

    1. start the zookeper server
    2. start the kafka server
    3. create topic 'gpslocation', if not already created
    4. start the *vehicle-simulator* application and use below CURL command to simulate the GPS signal emission
     ` curl -X POST "http://localhost:8080/simulate/3/{emit-interval}/{stop-index}?emitInterval=1&stopIndex=1" -H  "accept: */*" `
    5. start the *tracker-dashboard* application and use below CURL command to get the Online/Offline vehicle count
    ` curl http://localhost:9090/count/Online` 
    ` curl http://localhost:9090/count/Offline` 
