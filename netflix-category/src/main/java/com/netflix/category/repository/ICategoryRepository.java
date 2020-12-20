package com.netflix.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.category.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
