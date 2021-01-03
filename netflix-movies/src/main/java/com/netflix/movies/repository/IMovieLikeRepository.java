package com.netflix.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.netflix.movies.model.MovieLikeEntity;
import com.netflix.movies.model.MovieUserEntityPK;

public interface IMovieLikeRepository extends JpaRepository<MovieLikeEntity, MovieUserEntityPK> {

	@Query("SELECT m FROM MovieLikeEntity m WHERE m.pk.user = :user")
	List<MovieLikeEntity> findByUser(@Param("user") Long user);

}
