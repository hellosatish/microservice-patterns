package org.sk.vtracker.simulator.services;

import org.springframework.stereotype.Service;

@Service
public interface SimulationService {

	public void simulate(int numberOfVehicles, int emitInterval, int stopIndex);
}
