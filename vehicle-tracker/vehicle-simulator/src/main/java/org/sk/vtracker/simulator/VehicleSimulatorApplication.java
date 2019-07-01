package org.sk.vtracker.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAsync
@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class VehicleSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleSimulatorApplication.class, args);
	}

}
