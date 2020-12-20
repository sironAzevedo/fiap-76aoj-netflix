package com.netflix.movies.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.movies.model.Movie;

public interface IMovieRepository extends JpaRepository<Movie, Long> {
	
	Page<Movie> findByCategoriesId(final Long idCategory, Pageable pageable);

}
