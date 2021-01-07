package com.netflix.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.movies.model.MovieCategoryEntity;

@Repository
public interface IMovieCategoryRepository extends JpaRepository<MovieCategoryEntity, Long> {

	List<MovieCategoryEntity> findByCategory(Long category);

}
