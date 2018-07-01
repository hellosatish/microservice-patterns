package main

import (
	"log"
	"awesomeProject/helper"
	"time"
	"fmt"
	"github.com/carlescere/scheduler"
	"runtime"
	"os"
)

/**
Below is the format required by Eureka to register and application instance
{
    "instance": {
        "hostName": "MY_HOSTNAME",
        "app": "org.github.hellosatish.microservicepattern.awesomeproject",
        "vipAddress": "org.github.hellosatish.microservicepattern.awesomeproject",
        "secureVipAddress": "org.github.hellosatish.microservicepattern.awesomeproject"
        "ipAddr": "10.0.0.10",
        "status": "STARTING",
        "port": {"$": "8080", "@enabled": "true"},
        "securePort": {"$": "8443", "@enabled": "true"},
        "healthCheckUrl": "http://WKS-SOF-L011:8080/healthcheck",
        "statusPageUrl": "http://WKS-SOF-L011:8080/status",
        "homePageUrl": "http://WKS-SOF-L011:8080",
        "dataCenterInfo": {
            "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo", 
            "name": "MyOwn"
        },
    }
}
 */

type AppRegistrationBody struct {
	Instance InstanceDetails `json:"instance"`
}

type InstanceDetails struct {
	HostName         string         `json:"hostName"`
	App              string         `json:"app"`
	VipAddress       string         `json:"vipAddress"`
	SecureVipAddress string         `json:"secureVipAddress"`
	IpAddr           string         `json:"ipAddr"`
	Status           string         `json:"status"`
	Port             Port           `json:"port"`
	SecurePort       Port           `json:"securePort"`
	HealthCheckUrl   string         `json:"healthCheckUrl"`
	StatusPageUrl    string         `json:"statusPageUrl"`
	HomePageUrl      string         `json:"homePageUrl"`
	DataCenterInfo   DataCenterInfo `json:"dataCenterInfo"`
}
type Port struct {
	Port    string `json:"$"`
	Enabled string `json:"@enabled"`
}

type DataCenterInfo struct {
	Class string `json:"@class"`
	Name  string `json:"name"`
}

// This struct shall be responsible for manager to manage registration with Eureka
type EurekaRegistrationManager struct {
}

func (erm EurekaRegistrationManager) RegisterWithSerivceRegistry(eurekaConfigs RegistrationVariables) {
	log.Print("Registering service with status : STARTING")
	body :=  erm.getBodyForEureka("STARTING")

	helper.MakePostCall(eurekaConfigs.ServiceRegistryURL()+"MY_AWSOME_GO_MS", body, nil)
	log.Print("Waiting for 10 seconds for application to start properly")
	time.Sleep(10 * time.Second)
	log.Print("Updating the status to : UP")
	bodyUP :=  erm.getBodyForEureka("UP")
	helper.MakePostCall(eurekaConfigs.ServiceRegistryURL()+"MY_AWSOME_GO_MS", bodyUP, nil)
}

func (erm EurekaRegistrationManager) SendHeartBeat(eurekaConfigs RegistrationVariables) {
	log.Println("In SendHeartBeat!")
	hostname, err := os.Hostname()
	if err != nil{
		log.Print("Error while getting hostname which shall be used as APP ID")
	}
	job := func() {
		fmt.Println("sending heartbeat : ", time.Now().UTC())
		helper.MakePutCall(eurekaConfigs.ServiceRegistryURL()+"MY_AWSOME_GO_MS/"+hostname, nil, nil)
	}
	// Run every 25 seconds but not now.
	scheduler.Every(25).Seconds().Run(job)
	runtime.Goexit()

}
func (erm EurekaRegistrationManager) DeRegisterFromServiceRegistry(configs RegistrationVariables) {
	helper.MakePostCall(configs.ServiceRegistryURL(), nil, nil)
}

func (erm EurekaRegistrationManager) getBodyForEureka(status string) *AppRegistrationBody {
	httpport := "8080"
	hostname, err := os.Hostname()
	if err != nil{
		log.Print("Enable to find hostname form OS, sending appname as host name")
	}

	ipAddress, err := helper.ExternalIP()
	if err != nil{
		log.Print("Enable to find IP address form OS")
	}

	port := Port{httpport,"true"}
	securePort := Port{"8443","true"}
	dataCenterInfo := DataCenterInfo{"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo","MyOwn"}

	homePageUrl := "http://"+hostname+":"+httpport
	statusPageUrl := "http://"+hostname+":"+httpport+"/status"
	healthCheckUrl := "http://"+hostname+":"+httpport+"/healthcheck"

	instance := InstanceDetails{hostname, "MY_AWSOME_GO_MS", "MY_AWSOME_GO_MS", "MY_AWSOME_GO_MS",
		ipAddress,status , port,securePort, healthCheckUrl, statusPageUrl, homePageUrl, dataCenterInfo}

	body := &AppRegistrationBody{instance}
	return body
}