package com.netflix.movies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netflix.movies.model.MovieUserEntityPK;
import com.netflix.movies.model.MovieWatchedEntity;

@Repository
public interface IMovieWatchedRepository extends JpaRepository<MovieWatchedEntity, MovieUserEntityPK> {

	@Query("SELECT m FROM MovieWatchedEntity m WHERE m.pk.user = :user")
	List<MovieWatchedEntity> findByUser(@Param("user") Long user);

	@Query("SELECT m FROM MovieWatchedEntity m WHERE m.pk.user = :user AND m.pk.movie.id = :movie")
	Optional<MovieWatchedEntity> findByPkUserAndMovie(@Param("user") Long user, @Param("movie") Long movie);
}
