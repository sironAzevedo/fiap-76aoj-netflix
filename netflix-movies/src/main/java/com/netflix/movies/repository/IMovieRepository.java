package com.netflix.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.movies.model.MovieEntity;

@Repository
public interface IMovieRepository extends JpaRepository<MovieEntity, Long> {

	List<MovieEntity> findBykeywordsKeywordContainingIgnoreCase(String word);
	
}
