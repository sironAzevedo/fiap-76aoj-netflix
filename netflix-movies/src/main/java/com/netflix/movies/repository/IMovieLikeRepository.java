package com.netflix.movies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netflix.movies.model.MovieLikeEntity;
import com.netflix.movies.model.MovieUserEntityPK;

@Repository
public interface IMovieLikeRepository extends JpaRepository<MovieLikeEntity, MovieUserEntityPK> {

	@Query("SELECT m FROM MovieLikeEntity m WHERE m.pk.user = :user")
	List<MovieLikeEntity> findByUser(@Param("user") Long user);
	
	@Query("SELECT m FROM MovieLikeEntity m WHERE m.pk.user = :user AND m.pk.movie.id = :movie")
	Optional<MovieLikeEntity> findByPkUserAndMovie(@Param("user") Long user, @Param("movie") Long movie);

}
