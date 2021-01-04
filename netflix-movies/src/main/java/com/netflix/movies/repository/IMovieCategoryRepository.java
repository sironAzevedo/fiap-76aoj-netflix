package com.netflix.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netflix.movies.model.MovieCategoryEntity;
import com.netflix.movies.model.MovieCategoryEntityPK;
import com.netflix.movies.model.MovieEntity;

@Repository
public interface IMovieCategoryRepository extends JpaRepository<MovieCategoryEntity, MovieCategoryEntityPK> {
	
	@Query("SELECT m FROM MovieCategoryEntity m WHERE m.pk.category = :category")
	List<MovieCategoryEntity> getMoviesByCategory(@Param("category") Long category);

	
	@Query("SELECT m FROM MovieCategoryEntity m WHERE m.pk.movie = :movie")
	List<MovieCategoryEntity> getCategoryByMovie(@Param("movie") MovieEntity movie); 
}
