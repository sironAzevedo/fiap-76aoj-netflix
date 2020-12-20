package com.netflix.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.movies.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
