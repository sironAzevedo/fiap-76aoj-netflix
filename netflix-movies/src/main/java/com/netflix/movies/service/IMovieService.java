package com.netflix.movies.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.model.dto.MovieLikeDTO;
import com.netflix.movies.model.dto.MovieUserDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;
import com.netflix.movies.model.dto.TopMovieCategoryResponseDTO;

public interface IMovieService {

	Page<MovieDTO> byCategory(Long idCategory, Pageable pageable);

	MovieDTO detail(Long id_movie);

	Page<MovieDTO> getKeyword(String word, Pageable pageable);

	void watched(MovieWatchedDTO dto);

	Page<MovieWatchedDTO> watched(String user, Pageable pageable);

	void like(MovieLikeDTO dto);

	Page<MovieLikeDTO> likes(String user, Pageable pageable);

	void watchFuture(String user, Long movie);
	
	Page<MovieUserDTO> watchFuture(String user, Pageable pageable);	
	
	void deleteWatchFuture(String user, Long movie);
	
	List<TopMovieCategoryResponseDTO> getTopMovieByCategory(Pageable pageable);
}
