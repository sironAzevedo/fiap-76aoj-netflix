package com.netflix.movies.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.netflix.movies.model.dto.MovieWatchedDTO;

@Service
public class MovieWatchedProducer {

	private static final Logger logger = LoggerFactory.getLogger(MovieWatchedProducer.class);
    private static final String TOPIC = "remove_movie_watch_future";

    @Autowired
    private KafkaTemplate<String, MovieWatchedDTO> userCreatedKafkaTemplate;

    public void sendMessage(MovieWatchedDTO movie) {
        logger.info(String.format("Producing %s, movie: %s", TOPIC, movie.toString()));
        this.userCreatedKafkaTemplate.send(TOPIC, movie);
    }
}
