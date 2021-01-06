package com.netflix.series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netflix.series.model.SerieCategoryEntity;
import com.netflix.series.model.SerieCategoryEntityPK;
import com.netflix.series.model.SerieEntity;

@Repository
public interface ISerieCategoryRepository extends JpaRepository<SerieCategoryEntity, SerieCategoryEntityPK> {
	
	@Query("SELECT m FROM SerieCategoryEntity m WHERE m.pk.category = :category")
	List<SerieCategoryEntity> getSerieByCategory(@Param("category") Long category);

	
	@Query("SELECT m FROM SerieCategoryEntity m WHERE m.pk.serie = :serie")
	List<SerieCategoryEntity> getCategoryBySerie(@Param("serie") SerieEntity serie); 
}
