package com.netflix.series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.series.model.SerieEntity;

@Repository
public interface ISerieRepository extends JpaRepository<SerieEntity, Long> {

	List<SerieEntity> findBykeywordsKeywordContainingIgnoreCase(String word);
	
}
