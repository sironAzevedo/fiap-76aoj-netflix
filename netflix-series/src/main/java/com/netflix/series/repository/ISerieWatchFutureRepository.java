package com.netflix.series.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netflix.series.model.SerieEntity;
import com.netflix.series.model.SerieUserEntityPK;
import com.netflix.series.model.SerieWatchFutureEntity;

@Repository
public interface ISerieWatchFutureRepository extends JpaRepository<SerieWatchFutureEntity, SerieUserEntityPK> {

	@Query("SELECT m FROM SerieWatchFutureEntity m WHERE m.pk.user = :user")
	List<SerieWatchFutureEntity> findByUser(@Param("user") Long user);
	
	@Query("SELECT m FROM SerieWatchFutureEntity m WHERE m.pk.user = :user AND m.pk.serie = :serie")
	Optional<SerieWatchFutureEntity> findByUserAndSerie(@Param("user") Long user, @Param("serie") SerieEntity serie);
}
