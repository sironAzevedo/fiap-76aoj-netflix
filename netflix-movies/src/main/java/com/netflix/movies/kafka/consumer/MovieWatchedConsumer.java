package com.netflix.movies.kafka.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.netflix.movies.model.dto.MovieWatchedDTO;
import com.netflix.movies.service.IMovieService;

@Service
public class MovieWatchedConsumer {

	private final Logger logger = LoggerFactory.getLogger(MovieWatchedConsumer.class);

	@Autowired
	private IMovieService service;

	@KafkaListener(topics = "remove_movie_watch_future", groupId = "netflix_movie", containerFactory = "movieWatchedKafkaListenerContainerFactory")
	public void consume(MovieWatchedDTO message) throws IOException {
		logger.info(String.format("Consuming movie_watched, movie: %s", message.toString()));
		service.deleteWatchFuture(message.getUser(), message.getMovie().getId());
	}
}
