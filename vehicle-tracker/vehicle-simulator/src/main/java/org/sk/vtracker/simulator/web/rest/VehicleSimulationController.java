package org.sk.vtracker.simulator.web.rest;

import java.util.Random;

import org.sk.vtracker.simulator.services.SimulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleSimulationController {
	
	private final SimulationService simulatorService;
	
	public VehicleSimulationController(SimulationService simulatorService) {
		super();
		this.simulatorService = simulatorService;
	}
	
	@PostMapping("/simulate/{number-of-vehicle}/{emit-interval}/{stop-index}")
	public ResponseEntity<String> simulateSingnalEmission(@PathVariable("number-of-vehicle") int numberOfVehicles,
			int emitInterval, int stopIndex) {
	
		for(int vehicleId = 1 ; vehicleId <= numberOfVehicles ; vehicleId++) {
			simulatorService.simulate(vehicleId, emitInterval*getRandomNumberInRange(1,4),  stopIndex);	
		}
		
		return new ResponseEntity<String>("Simulation Started for "+numberOfVehicles+" vehicles", HttpStatus.OK);
	}
	
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	

}
