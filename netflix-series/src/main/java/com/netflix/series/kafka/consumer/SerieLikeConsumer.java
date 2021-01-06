package com.netflix.series.kafka.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.netflix.series.model.dto.SerieLikeDTO;
import com.netflix.series.service.ISerieService;

@Service
public class SerieLikeConsumer {

	private final Logger logger = LoggerFactory.getLogger(SerieLikeConsumer.class);

	@Autowired
	private ISerieService service;
	
	@KafkaListener(topics = "serie_like", groupId = "netflix_serie", containerFactory = "serieLikeKafkaListenerContainerFactory")
	public void consume(SerieLikeDTO message) throws IOException {
		logger.info(String.format("Consuming serie_like, serie: %s", message.toString()));
		service.like(message);
	}
}
