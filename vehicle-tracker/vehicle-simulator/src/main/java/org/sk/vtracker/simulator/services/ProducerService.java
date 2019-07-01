package org.sk.vtracker.simulator.services;

import org.springframework.stereotype.Service;

import com.sk.vtracker.commons.models.VehicleLocation;

@Service
public interface ProducerService {

	public void produce(String key, String message);
	public void produce(int vehicleId, VehicleLocation location);
}
