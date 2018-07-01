package main

import (
	"os"
	"log"
)

var configurations RegistrationVariables

func init() {
	registryType := os.Getenv("REGISTRY_TYPE")
	if registryType == "" {
		log.Fatal("$REGISTRY_TYPE not set")
	}
	serviceRegistryURL := os.Getenv("REGISTRY_URL")
	if serviceRegistryURL == "" {
		log.Fatal("REGISTRY_URL not set. Exiting application")
	}
	userName := os.Getenv("REGISTRY_USER")
	if userName == "" {
		log.Print("REGISTRY_USER not set. Shall be proceeding without user name")
	}
	password := os.Getenv("REGISTRY_PASSWORD")
	if password == "" {
		log.Print("REGISTRY_PASSWORD not set. Shall be proceeding without password")
	}
	configurations = RegistrationVariables{registryType, serviceRegistryURL, userName, password}
}

func main() {
	log.Print("registryType : " + configurations.RegistryType())
	log.Print("serviceRegistryURL : " + configurations.ServiceRegistryURL())
	log.Print("userName : " + configurations.UserName())
	log.Print("password : " + configurations.Password())

	ManageDiscovery(configurations)
}
