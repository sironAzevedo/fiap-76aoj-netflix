package com.netflix.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.movies.model.MovieEntity;

public interface IMovieRepository extends JpaRepository<MovieEntity, Long> {

	List<MovieEntity> findBykeywordsKeywordContainingIgnoreCase(String word);
	
}
