package com.netflix.helpdesk.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netflix.helpdesk.model.dto.TicketDTO;

public interface IServiceTicket {

	void save(TicketDTO dto);
	
	Page<TicketDTO> findAllByUser(String email, Pageable pageable);
}
