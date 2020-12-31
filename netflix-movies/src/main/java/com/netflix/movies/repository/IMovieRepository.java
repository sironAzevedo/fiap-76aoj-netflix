package com.netflix.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.movies.model.MovieEntity;

public interface IMovieRepository extends JpaRepository<MovieEntity, Long> {
	
}
