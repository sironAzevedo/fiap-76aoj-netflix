package com.netflix.likes.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;
	
	@Value(value = "${kafka.topic.serie}")
	private String topicSerie;
	
	@Value(value = "${kafka.topic.movie}")
	private String topicmovie;
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic topicSerieLike() {
		return new NewTopic(topicSerie, 3, (short) 1);
	}
	
	@Bean
	public NewTopic topicMovieLike() {
		return new NewTopic(topicmovie, 3, (short) 1);
	}
}
