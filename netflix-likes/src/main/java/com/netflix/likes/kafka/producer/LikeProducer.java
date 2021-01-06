package com.netflix.likes.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.netflix.likes.model.dto.MovieLikeDTO;
import com.netflix.likes.model.dto.SerieLikeDTO;

@Service
public class LikeProducer {
	
	@Value(value = "${kafka.topic.serie}")
	private String topicSerie;
	
	@Value(value = "${kafka.topic.movie}")
	private String topicmovie;

	private static final Logger logger = LoggerFactory.getLogger(LikeProducer.class);

	@Autowired
	private KafkaTemplate<String, Object> likeKafkaTemplate;

	public void sendSerieLike(SerieLikeDTO serie) {
		logger.info(String.format("Producing %s, serie: %s", topicSerie, serie.toString()));
		this.likeKafkaTemplate.send(topicSerie, serie);
	}

	public void sendMovieLike(MovieLikeDTO movie) {
		logger.info(String.format("Producing %s, movie: %s", topicmovie, movie.toString()));
		this.likeKafkaTemplate.send(topicmovie, movie);
	}
}
