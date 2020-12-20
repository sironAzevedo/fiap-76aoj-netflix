package com.netflix.movies.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netflix.movies.model.dto.MovieDTO;

public interface IMovieService {
	
	Page<MovieDTO> byCategory(Long idCategory, Pageable pageable);

	MovieDTO detail(Long id_movie);

}
