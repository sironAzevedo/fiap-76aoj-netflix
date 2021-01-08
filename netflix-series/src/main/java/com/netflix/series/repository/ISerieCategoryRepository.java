package com.netflix.series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.series.model.SerieCategoryEntity;

@Repository
public interface ISerieCategoryRepository extends JpaRepository<SerieCategoryEntity, Long> {

	List<SerieCategoryEntity> findByCategory(Long category);
}
