package com.netflix.movies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.netflix.movies.model.MovieEntity;
import com.netflix.movies.model.MovieUserEntityPK;
import com.netflix.movies.model.MovieWatchFutureEntity;

public interface IMovieWatchFutureRepository extends JpaRepository<MovieWatchFutureEntity, MovieUserEntityPK> {

	@Query("SELECT m FROM MovieWatchFutureEntity m WHERE m.pk.user = :user")
	List<MovieWatchFutureEntity> findByUser(@Param("user") Long user);
	
	@Query("SELECT m FROM MovieWatchFutureEntity m WHERE m.pk.user = :user AND m.pk.movie = :movie")
	Optional<MovieWatchFutureEntity> findByUserAndMovie(@Param("user") Long user, @Param("movie") MovieEntity movie);

}
