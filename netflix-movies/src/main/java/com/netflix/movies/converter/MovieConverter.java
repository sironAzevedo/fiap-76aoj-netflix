package com.netflix.movies.converter;

import java.util.Date;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.netflix.movies.model.MovieEntity;
import com.netflix.movies.model.MovieKeyWordEntity;
import com.netflix.movies.model.MovieWatchedEntity;
import com.netflix.movies.model.MovieWatchedEntityPK;
import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;

@Mapper(componentModel = "spring")
public abstract class MovieConverter {

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
		return dto;
	}
	
	public MovieWatchedDTO toDTO(MovieWatchedEntity entity) {
		MovieWatchedDTO dto = new MovieWatchedDTO();
		dto.setMovie(this.toDTO(entity.getPk().getMovie()));
		dto.setDateWatched(entity.getDate());		
		return dto;
	}

	public MovieWatchedEntity toMovieWatched(MovieWatchedDTO dto, Long user) {
		MovieEntity movie = new MovieEntity();
		movie.setId(dto.getMovie().getId());
		
		MovieWatchedEntityPK pk = new MovieWatchedEntityPK();
		pk.setUser(user);
		pk.setMovie(movie);
		
		MovieWatchedEntity entity = new MovieWatchedEntity();
		entity.setPk(pk);
		entity.setDate(new Date());
		
		return entity;
	}
}
