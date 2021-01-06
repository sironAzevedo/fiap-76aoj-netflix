package com.netflix.series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netflix.series.model.SerieUserEntityPK;
import com.netflix.series.model.SerieWatchedEntity;

@Repository
public interface ISerieWatchedRepository extends JpaRepository<SerieWatchedEntity, SerieUserEntityPK> {

	@Query("SELECT m FROM SerieWatchedEntity m WHERE m.pk.user = :user")
	List<SerieWatchedEntity> findByUser(@Param("user") Long user);

}
