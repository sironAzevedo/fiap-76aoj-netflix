package com.netflix.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.netflix.movies.model.MovieWatchedEntity;
import com.netflix.movies.model.MovieWatchedEntityPK;

public interface IMovieWatchedRepository extends JpaRepository<MovieWatchedEntity, MovieWatchedEntityPK> {

	@Query("SELECT m FROM MovieWatchedEntity m WHERE m.pk.user = :user")
	List<MovieWatchedEntity> findByUser(@Param("user") Long user);

}
