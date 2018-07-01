package main

import "log"

func ManageDiscovery(configs RegistrationVariables)  {

	switch configurations.RegistryType() {
	case NETFLIX_EUREKA:
		 manager := new (EurekaRegistrationManager)
		 manager.RegisterWithSerivceRegistry(configs)
		 manager.SendHeartBeat(configs)
	case HASHICORP_CONSUL:
		log.Fatal("No manager found for Registry Type : " + configurations.RegistryType())
	}

}
