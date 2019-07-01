package org.sk.vtracker.dashboard.streams.processor;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import com.sk.vtracker.commons.models.VehicleLocation;

@Component
public class VehicleStatusCountProcessor {
	@Autowired
	private KafkaProperties kafkaProperties;	//default properties
	
	@Bean	//configure key-value serdes
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
		return props;
	}

	@Bean	
	public KStream<String, Long>  statusCountStreamProcessor(StreamsBuilder streamsBuilder) {
		KStream<Integer, VehicleLocation> stream = streamsBuilder.stream("gpslocation",	//Read from topic
				Consumed.with(Serdes.Integer(), new JsonSerde<>(VehicleLocation.class)));	//using Integer and JSON serde
		return stream.map((k,v)-> {							// transform they key as Online/Offline based on status
				String online =  v.isOnline() == true ? "Online" : "Offline";	
				return new KeyValue<>(online, v);
			})
			.groupByKey(Serialized.with(			//Group by the newly mapped key in previous step
				      Serdes.String(), 
				      new JsonSerde<>(VehicleLocation.class))     
				  )
			.count(Materialized.as("statusCount"))	// materialize this value to state store
			.toStream();
	}
}
