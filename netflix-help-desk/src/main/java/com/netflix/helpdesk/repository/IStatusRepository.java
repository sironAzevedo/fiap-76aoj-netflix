package com.netflix.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.helpdesk.model.StatusEntity;

@Repository
public interface IStatusRepository extends JpaRepository<StatusEntity, Long> {
	
	StatusEntity findByDescriptionIgnoreCase(String description);

}
