package com.netflix.movies.repository;

import java.util.List;

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

}
