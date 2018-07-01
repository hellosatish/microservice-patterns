package main

const (
	NETFLIX_EUREKA   = "NETFLIX_EUREKA"
	HASHICORP_CONSUL = "CONSUL"
)

type RegistrationManager interface {
	Manage(configs RegistrationVariables)
	RegisterWithSerivceRegistry()
	SendHeartBeat(configs RegistrationVariables)
	DeRegisterFromServiceRegistry(configs RegistrationVariables)
}

type RegistrationVariables struct {
	registryType       string
	serviceRegistryURL string
	userName           string
	password           string
}

func (rv RegistrationVariables) RegistryType() string {
	return rv.registryType
}

func (rv RegistrationVariables) ServiceRegistryURL() string {
	return rv.serviceRegistryURL
}
func (rv RegistrationVariables) UserName() string {
	return rv.userName
}
func (rv RegistrationVariables) Password() string {
	return rv.password
}
