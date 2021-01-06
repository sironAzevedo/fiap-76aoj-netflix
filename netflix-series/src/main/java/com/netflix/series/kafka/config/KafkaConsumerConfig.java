package com.netflix.series.kafka.config;

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

import com.netflix.series.kafka.deserializer.CustomJsonDeserializer;
import com.netflix.series.model.dto.SerieLikeDTO;
import com.netflix.series.model.dto.SerieWatchedDTO;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
	
	private static final String GROUP_ID = "netflix_serie";

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SerieWatchedDTO> serieWatchedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SerieWatchedDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(serieWatchedConsumerFactory());
        return factory;
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SerieLikeDTO> serieLikeKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SerieLikeDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(serieLikeConsumerFactory());
        return factory;
    }

    private ConsumerFactory<String, SerieWatchedDTO> serieWatchedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new CustomJsonDeserializer<>(SerieWatchedDTO.class));
    }
    
    private ConsumerFactory<String, SerieLikeDTO> serieLikeConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(SerieLikeDTO.class, false));
    }
}
