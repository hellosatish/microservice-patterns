package org.sk.vtracker.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class TrackerDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerDashboardApplication.class, args);
	}

}
