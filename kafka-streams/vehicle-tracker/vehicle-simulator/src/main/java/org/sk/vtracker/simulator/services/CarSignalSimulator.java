/**
 * 
 */
package org.sk.vtracker.simulator.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sk.vtracker.commons.models.VehicleLocation;

import lombok.extern.log4j.Log4j2;

/**
 * @author satish-s
 *
 */
@Log4j2
@Service
public class CarSignalSimulator implements SimulationService {
	private  List<VehicleLocation> availbleLocations;
	
	@Autowired
	private ProducerService producer;
	
	public CarSignalSimulator() {
		initialiseLocations();
	}

	@Override
	public void simulate(int vehicleId, int emitInterval, int stopIndex) {
	 simulateVehicleMovement(vehicleId, emitInterval, stopIndex);
	}
	
	@Async
	 public void simulateVehicleMovement(int vehicleId,int emitInterval, int stopIndex) {
			int ctr =0;
				for(short i =0 ; i < availbleLocations.size() ; i++){
					ctr++;
					if (ctr == stopIndex) {
						emmitOfflineSignal(vehicleId,availbleLocations.get(i));
						addSleep(emitInterval*1000);
					}
					emitSignal(vehicleId,availbleLocations.get(i));
					if(i >= availbleLocations.size()-1) {
						i--;
						ctr =0;
						emitSignal(vehicleId,availbleLocations.get(i));
						for(;i>0 ;i--) {
							ctr++;
							if (ctr == stopIndex) {
								emmitOfflineSignal(vehicleId,availbleLocations.get(i));
								addSleep(emitInterval*1000);
							}
							emitSignal(vehicleId,availbleLocations.get(i));
						}
					}
				}
				log.info("------------------------ Loop Ended ------------------------ ");
			//	return CompletableFuture.completedFuture("DONE");
	 }
	
	private void emitSignal(int vehicleId, VehicleLocation location) {
		log.info("Vehicle Id : {}  GPS : {}", vehicleId, location);
		location.setVehicleId(vehicleId);
		producer.produce(vehicleId,location);
	}
	 
	private void emmitOfflineSignal(int vehicleId, VehicleLocation location) {
		log.info("Vehicle Id : {}  GPS : {}", vehicleId, location);
		location.setVehicleId(vehicleId);
		location.setOnline(false);
		producer.produce(vehicleId,location);
		location.setOnline(true);
	}

	
	 
	 private void addSleep(long millis) {
		 try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			log.error("Error whith sleep : {} ",e.getMessage());
		}
	 }
	


	 private void initialiseLocations() {
		 availbleLocations = new ArrayList<VehicleLocation>();
		 
		 availbleLocations.add(new VehicleLocation(true, true, 28.378804, 77.313618));
		 availbleLocations.add(new VehicleLocation(true, true, 28.379969, 77.313526));
		 availbleLocations.add(new VehicleLocation(true, true, 28.380857, 77.313464));
		 availbleLocations.add(new VehicleLocation(true, true, 28.381414, 77.313405));
		 availbleLocations.add(new VehicleLocation(true, true, 28.382697, 77.313314));
		 availbleLocations.add(new VehicleLocation(true, true, 28.383943, 77.313186));
		 availbleLocations.add(new VehicleLocation(true, true, 28.385449, 77.313057));
		 availbleLocations.add(new VehicleLocation(true, true, 28.386992, 77.312880));
		 availbleLocations.add(new VehicleLocation(true, true, 28.388186, 77.312762));
		 availbleLocations.add(new VehicleLocation(true, true, 28.389692, 77.312649));
		 availbleLocations.add(new VehicleLocation(true, true, 28.391178, 77.312520));
		 availbleLocations.add(new VehicleLocation(true, true, 28.395241, 77.312233));
		 availbleLocations.add(new VehicleLocation(true, true, 28.398233, 77.311964));
		 availbleLocations.add(new VehicleLocation(true, true, 28.401489, 77.311686));
		 availbleLocations.add(new VehicleLocation(true, true, 28.404584, 77.311557));
		 availbleLocations.add(new VehicleLocation(true, true, 28.407226, 77.311246));
		 availbleLocations.add(new VehicleLocation(true, true, 28.409255, 77.311095));
		 availbleLocations.add(new VehicleLocation(true, true, 28.412643, 77.310784));
		 availbleLocations.add(new VehicleLocation(true, true, 28.415342, 77.310537));
		 availbleLocations.add(new VehicleLocation(true, true, 28.418852, 77.310269));
		 availbleLocations.add(new VehicleLocation(true, true, 28.421787, 77.309990));
		 availbleLocations.add(new VehicleLocation(true, true, 28.424485, 77.309744));
		 availbleLocations.add(new VehicleLocation(true, true, 28.427457, 77.309529));
		 availbleLocations.add(new VehicleLocation(true, true, 28.429793, 77.309307));
		 availbleLocations.add(new VehicleLocation(true, true, 28.431340, 77.309189));
		 availbleLocations.add(new VehicleLocation(true, true, 28.433039, 77.309077));
		 availbleLocations.add(new VehicleLocation(true, true, 28.435331, 77.308867));
		 availbleLocations.add(new VehicleLocation(true, true, 28.436864, 77.308728));
		 availbleLocations.add(new VehicleLocation(true, true, 28.440392, 77.308413));
		 availbleLocations.add(new VehicleLocation(true, true, 28.445156, 77.308027));
		 availbleLocations.add(new VehicleLocation(true, true, 28.449203, 77.307662));
		 availbleLocations.add(new VehicleLocation(true, true, 28.452797, 77.307383));
		 availbleLocations.add(new VehicleLocation(true, true, 28.458070, 77.306943));
		 availbleLocations.add(new VehicleLocation(true, true, 28.463560, 77.306536));
		 availbleLocations.add(new VehicleLocation(true, true, 28.467228, 77.306128));
		 availbleLocations.add(new VehicleLocation(true, true, 28.473048, 77.305689));
		 availbleLocations.add(new VehicleLocation(true, true, 28.477876, 77.305248));
		 availbleLocations.add(new VehicleLocation(true, true, 28.481318, 77.304894));
		 availbleLocations.add(new VehicleLocation(true, true, 28.489975, 77.303993));
		
	}


}
