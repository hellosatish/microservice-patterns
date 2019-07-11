/**
 * 
 */
package org.sk.vtracker.simulator.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sk.vtracker.commons.models.VehicleLocation;

import lombok.extern.log4j.Log4j2;

/**
 * @author satish-s
 *
 */
@Component
@Log4j2
@Service
public class KafkaProducer implements ProducerService {

	@Value("${app.topic.name}")
	private  String topicName;
	
    @Autowired
    private KafkaTemplate<Integer, VehicleLocation> kafkaTemplate;

	    
	@Override
	public void produce(String key, String message) {
		log.debug("Sending message : {}", message);
		this.kafkaTemplate.send(topicName, message);
	}

	@Override
	public void produce(int vehicleId, VehicleLocation location) {
		log.debug("Sending Location info : {}", location);
		this.kafkaTemplate.send(topicName,vehicleId, location);
	}
	
}
