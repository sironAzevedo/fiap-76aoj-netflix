package com.netflix.movies.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;

public interface IMovieService {

	Page<MovieDTO> byCategory(Long idCategory, Pageable pageable);

	MovieDTO detail(Long id_movie);

	Page<MovieDTO> getKeyword(String word, Pageable pageable);

	void movieWatched(MovieWatchedDTO dto);

	Page<MovieWatchedDTO> movieWatched(String user, Pageable pageable);
}
