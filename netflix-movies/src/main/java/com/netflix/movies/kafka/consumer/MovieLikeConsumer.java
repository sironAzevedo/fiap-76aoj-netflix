package com.netflix.movies.kafka.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.netflix.movies.model.dto.MovieLikeDTO;
import com.netflix.movies.service.IMovieService;

@Service
public class MovieLikeConsumer {

	private final Logger logger = LoggerFactory.getLogger(MovieLikeConsumer.class);

	@Autowired
	private IMovieService service;
	
	@KafkaListener(topics = "movie_like", groupId = "netflix_movie", containerFactory = "movieLikeKafkaListenerContainerFactory")
	public void consume(MovieLikeDTO message) throws IOException {
		logger.info(String.format("Consuming netflix_movie, movie: %s", message.toString()));
		service.like(message);
	}
}
