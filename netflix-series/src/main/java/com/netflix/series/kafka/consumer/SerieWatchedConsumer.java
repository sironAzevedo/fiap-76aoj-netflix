package com.netflix.series.kafka.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.netflix.series.model.dto.SerieWatchedDTO;
import com.netflix.series.service.ISerieService;

@Service
public class SerieWatchedConsumer {

	private final Logger logger = LoggerFactory.getLogger(SerieWatchedConsumer.class);

	@Autowired
	private ISerieService service;

	@KafkaListener(topics = "remove_serie_watch_future", groupId = "netflix_serie", containerFactory = "serieWatchedKafkaListenerContainerFactory")
	public void consume(SerieWatchedDTO message) throws IOException {
		logger.info(String.format("Consuming serie_watched, serie: %s", message.toString()));
		service.deleteWatchFuture(message.getUser(), message.getSerie().getId());
	}
}
