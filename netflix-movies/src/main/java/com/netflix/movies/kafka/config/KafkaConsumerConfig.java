package com.netflix.movies.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.netflix.movies.kafka.deserializer.CustomJsonDeserializer;
import com.netflix.movies.model.dto.MovieWatchedDTO;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MovieWatchedDTO> movieWatchedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MovieWatchedDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(movieWatchedConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, MovieWatchedDTO> movieWatchedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "netflix_movie");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new CustomJsonDeserializer<>(MovieWatchedDTO.class));
    }
}
