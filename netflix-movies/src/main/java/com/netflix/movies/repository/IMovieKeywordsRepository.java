package com.netflix.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.movies.model.MovieEntity;
import com.netflix.movies.model.MovieKeyWordEntity;

@Repository
public interface IMovieKeywordsRepository extends JpaRepository<MovieKeyWordEntity, Long> {

	List<MovieKeyWordEntity> findByMovie(MovieEntity movie);
}
