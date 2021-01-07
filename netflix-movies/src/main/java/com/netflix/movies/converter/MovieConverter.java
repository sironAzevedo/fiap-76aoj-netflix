package com.netflix.movies.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.movies.feignClients.CategoryClient;
import com.netflix.movies.model.MovieEntity;
import com.netflix.movies.model.MovieKeyWordEntity;
import com.netflix.movies.model.MovieLikeEntity;
import com.netflix.movies.model.MovieUserEntityPK;
import com.netflix.movies.model.MovieWatchFutureEntity;
import com.netflix.movies.model.MovieWatchedEntity;
import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.model.dto.MovieLikeDTO;
import com.netflix.movies.model.dto.MovieUserDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;

@Mapper(componentModel = "spring")
public abstract class MovieConverter {
	
	@Autowired
	private CategoryClient categoryClient;

	public MovieDTO toDTO(MovieEntity entity) {
		MovieDTO dto = new MovieDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setSummary(entity.getSummary());
		dto.setReleaseDate(entity.getReleaseDate());
		dto.setKeywords(entity
				.getKeywords()
				.stream()
				.map(MovieKeyWordEntity::getKeyword)
				.collect(Collectors.toList()));
		
		List<String> categories = new ArrayList<>();
		entity.getCategories().forEach(c -> {
			 Optional.ofNullable(categoryClient.category(c.getCategory())).ifPresent(r -> categories.add(r.getName()));
		});
		
		dto.setCategories(categories);
		return dto;
	}
	
	public MovieWatchedDTO toDTO(MovieWatchedEntity entity) {
		MovieWatchedDTO dto = new MovieWatchedDTO();
		dto.setMovie(this.toDTO(entity.getPk().getMovie()));
		dto.setDateWatched(entity.getDate());		
		return dto;
	}
	
	public MovieLikeDTO toDTO(MovieLikeEntity entity) {
		MovieLikeDTO dto = new MovieLikeDTO();
		dto.setMovie(this.toDTO(entity.getPk().getMovie()));
		dto.setLiked(entity.getLiked());
		return dto;
	}
	
	public MovieUserDTO toDTO(MovieWatchFutureEntity entity) {
		MovieUserDTO dto = new MovieUserDTO();
		dto.setMovie(this.toDTO(entity.getPk().getMovie()));
		return dto;
	}

	public MovieWatchedEntity toMovieWatched(MovieWatchedDTO dto, Long user) {
		MovieEntity movie = new MovieEntity();
		movie.setId(dto.getMovie().getId());
		
		MovieUserEntityPK pk = new MovieUserEntityPK();
		pk.setUser(user);
		pk.setMovie(movie);
		
		MovieWatchedEntity entity = new MovieWatchedEntity();
		entity.setPk(pk);
		entity.setDate(new Date());
		
		return entity;
	}
	
	public MovieLikeEntity toMovieLike(MovieLikeDTO dto, Long user) {
		MovieEntity movie = new MovieEntity();
		movie.setId(dto.getMovie().getId());
		
		MovieUserEntityPK pk = new MovieUserEntityPK();
		pk.setUser(user);
		pk.setMovie(movie);
		
		MovieLikeEntity entity = new MovieLikeEntity();
		entity.setPk(pk);
		entity.setLiked(dto.getLiked());
		return entity;
	}
	
	public MovieWatchFutureEntity toMovieWatchFuture(MovieEntity movie , Long user) {
		MovieUserEntityPK pk = new MovieUserEntityPK();
		pk.setUser(user);
		pk.setMovie(movie);
		
		MovieWatchFutureEntity entity = new MovieWatchFutureEntity();
		entity.setPk(pk);
		return entity;
	}
}
