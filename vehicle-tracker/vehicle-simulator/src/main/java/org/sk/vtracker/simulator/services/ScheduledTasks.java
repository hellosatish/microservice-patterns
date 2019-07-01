package org.sk.vtracker.simulator.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sk.vtracker.commons.models.VehicleLocation;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ScheduledTasks {

	@Value("${app.topic.name}")
	private  String topicName;
	
    @Autowired
    private KafkaTemplate<Integer, VehicleLocation> kafkaTemplate;


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  //  @PostConstruct
    public void reportCurrentTime() throws InterruptedException {
        log.info("The time is now {}", dateFormat.format(new Date()));

    		VehicleLocation loc = new VehicleLocation(true, true, 11111111, 444444);
    		while(true) {
    			boolean on =  loc.isOnline() == true ? false : true;
    			loc.setOnline(on);
    			log.info("Sending Location info : {}", loc);
    			Thread.sleep(10000);
    			this.kafkaTemplate.send(topicName,1, loc);
    		}
    	}
}