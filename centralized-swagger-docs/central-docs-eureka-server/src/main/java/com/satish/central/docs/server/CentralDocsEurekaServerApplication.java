package com.satish.central.docs.server;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CentralDocsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralDocsEurekaServerApplication.class, args);
	}
}
