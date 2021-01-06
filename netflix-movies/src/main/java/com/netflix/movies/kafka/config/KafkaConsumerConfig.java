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
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.netflix.movies.kafka.deserializer.CustomJsonDeserializer;
import com.netflix.movies.model.dto.MovieLikeDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
	
	private static final String GROUP_ID = "netflix_movie";

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MovieWatchedDTO> movieWatchedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MovieWatchedDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(movieWatchedConsumerFactory());
        return factory;
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MovieLikeDTO> movieLikeKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MovieLikeDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(movieLikeConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, MovieWatchedDTO> movieWatchedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new CustomJsonDeserializer<>(MovieWatchedDTO.class));
    }
    
    public ConsumerFactory<String, MovieLikeDTO> movieLikeConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(MovieLikeDTO.class, false));
    }
}
