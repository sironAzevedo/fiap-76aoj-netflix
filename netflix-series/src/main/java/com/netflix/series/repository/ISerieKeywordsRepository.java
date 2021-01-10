package com.netflix.series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.series.model.SerieEntity;
import com.netflix.series.model.SerieKeyWordEntity;

@Repository
public interface ISerieKeywordsRepository extends JpaRepository<SerieKeyWordEntity, Long> {

	List<SerieKeyWordEntity> findBySerie(SerieEntity serie);
}
