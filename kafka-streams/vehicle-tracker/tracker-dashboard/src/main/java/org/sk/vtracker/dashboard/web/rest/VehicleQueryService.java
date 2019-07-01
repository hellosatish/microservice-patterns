package org.sk.vtracker.dashboard.web.rest;


import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VehicleQueryService {
	@Autowired
	private StreamsBuilderFactoryBean kStreamBuilderFactoryBean;
	
	@GetMapping("/count/{status}")
	public long getVehicleCoutnForStatus(@PathVariable("status") String status) {
		// Get the state-store
		ReadOnlyKeyValueStore<String, Long> keyValueStore= kStreamBuilderFactoryBean.getKafkaStreams()
														.store("statusCount", QueryableStoreTypes.keyValueStore());
		return keyValueStore.get(status);	//get the count for the key viz. Offline/Online
	}
}
