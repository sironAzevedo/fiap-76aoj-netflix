package com.netflix.likes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.likes.feignClients.MovieFeignClient;
import com.netflix.likes.feignClients.SerieFeignClient;
import com.netflix.likes.feignClients.UserFeignClient;
import com.netflix.likes.handler.exception.SerieException;
import com.netflix.likes.handler.exception.UserException;
import com.netflix.likes.kafka.producer.LikeProducer;
import com.netflix.likes.model.dto.MovieDTO;
import com.netflix.likes.model.dto.MovieLikeDTO;
import com.netflix.likes.model.dto.SerieDTO;
import com.netflix.likes.model.dto.SerieLikeDTO;
import com.netflix.likes.model.dto.UserDTO;
import com.netflix.likes.service.ILikeService;

@Service
public class LikeServiceImpl implements ILikeService {

	@Autowired
	private SerieFeignClient serieClient;

	@Autowired
	private MovieFeignClient movieClient;

	@Autowired
	private UserFeignClient userClient;

	@Autowired
	private LikeProducer producer;

	@Override
	public void serieLike(String emailUser, Long id_serie, String like) {
		SerieDTO serie = serieClient.detail(id_serie);

		if (serie == null) {
			throw new SerieException("Serie not found");
		}

		SerieLikeDTO dto = new SerieLikeDTO();
		dto.setUser(getUser(emailUser).getEmail());
		dto.setSerie(serie);
		dto.setLiked(like.toUpperCase());
		producer.sendSerieLike(dto);
	}

	@Override
	public void movieLike(String emailUser, Long id_movie, String like) {
		MovieDTO movie = movieClient.detail(id_movie);

		if (movie == null) {
			throw new SerieException("Movie not found");
		}

		MovieLikeDTO dto = new MovieLikeDTO();
		dto.setUser(getUser(emailUser).getEmail());
		dto.setMovie(movie);
		dto.setLiked(like.toUpperCase());
		producer.sendMovieLike(dto);
	}

	private UserDTO getUser(String user) {
		UserDTO u = userClient.findByEmail(user);
		if (u == null) {
			throw new UserException("User not found");
		}
		return u;
	}
}
