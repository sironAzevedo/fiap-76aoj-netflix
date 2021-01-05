package com.netflix.series.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.netflix.series.model.dto.SerieWatchedDTO;

@Service
public class SerieWatchedProducer {

	private static final Logger logger = LoggerFactory.getLogger(SerieWatchedProducer.class);
    private static final String TOPIC = "remove_movie_watch_future";

    @Autowired
    private KafkaTemplate<String, SerieWatchedDTO> watchedKafkaTemplate;

    public void sendMessage(SerieWatchedDTO movie) {
        logger.info(String.format("Producing %s, movie: %s", TOPIC, movie.toString()));
        this.watchedKafkaTemplate.send(TOPIC, movie);
    }
}
