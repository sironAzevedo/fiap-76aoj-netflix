package com.netflix.series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netflix.series.model.SerieLikeEntity;
import com.netflix.series.model.SerieUserEntityPK;

@Repository
public interface ISerieLikeRepository extends JpaRepository<SerieLikeEntity, SerieUserEntityPK> {

	@Query("SELECT m FROM SerieLikeEntity m WHERE m.pk.user = :user")
	List<SerieLikeEntity> findByUser(@Param("user") Long user);

}
