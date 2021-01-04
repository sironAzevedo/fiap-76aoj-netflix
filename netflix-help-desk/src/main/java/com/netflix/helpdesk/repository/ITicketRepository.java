package com.netflix.helpdesk.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.helpdesk.model.TicketEntity;

@Repository
public interface ITicketRepository extends JpaRepository<TicketEntity, Long> {

	List<TicketEntity> findByUser(Long user, Pageable pageable);

}
