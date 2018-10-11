package com.satish.central.docs.config.swagger;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;

/**
 * 
 * @author satish sharma
 * <pre>
 *   	In-Memory store to hold API-Definition JSON
 * </pre>
 */
@Component
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceDefinitionsContext {
	
	private final ConcurrentHashMap<String,String> serviceDescriptions; 
	 
	 private ServiceDefinitionsContext(){
		 serviceDescriptions = new ConcurrentHashMap<String, String>();
	 }
	 
	 public void addServiceDefinition(String serviceName, String serviceDescription){
		 serviceDescriptions.put(serviceName, serviceDescription);
	 }
	 
	 public String getSwaggerDefinition(String serviceId){
		 return this.serviceDescriptions.get(serviceId);
	 }
	 
	 public List<SwaggerResource> getSwaggerDefinitions(){
			return  serviceDescriptions.entrySet().stream().map( serviceDefinition -> {
				 SwaggerResource resource = new SwaggerResource();
				 resource.setLocation("/service/"+serviceDefinition.getKey());
				 resource.setName(serviceDefinition.getKey());
				 resource.setSwaggerVersion("2.0");	 
				 return resource;
			 }).collect(Collectors.toList());
		 }
}
