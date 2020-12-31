package com.netflix.movies.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.netflix.movies.model.MovieEntity;
import com.netflix.movies.model.dto.MovieDTO;

@Mapper
public interface MovieConverter {
	
	MovieConverter INSTANCE = Mappers.getMapper(MovieConverter.class);
	
	@Mappings({
	      @Mapping(target="id", source="entity.id"),
	      @Mapping(target="title", source="entity.title"),
	      @Mapping(target="summary", source="entity.summary"),
	      @Mapping(target="releaseDate", source="entity.releaseDate")
	    })
	MovieDTO toDTO(MovieEntity entity);

}
