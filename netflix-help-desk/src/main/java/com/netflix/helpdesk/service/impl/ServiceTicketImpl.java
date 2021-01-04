package com.netflix.helpdesk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netflix.helpdesk.converter.HelpdeskConverter;
import com.netflix.helpdesk.feignClients.UserFeignClient;
import com.netflix.helpdesk.handler.exception.UserException;
import com.netflix.helpdesk.model.TicketEntity;
import com.netflix.helpdesk.model.dto.TicketDTO;
import com.netflix.helpdesk.model.dto.UserDTO;
import com.netflix.helpdesk.repository.IStatusRepository;
import com.netflix.helpdesk.repository.ITicketRepository;
import com.netflix.helpdesk.service.IServiceTicket;

@Service
public class ServiceTicketImpl implements IServiceTicket {
	
	@Autowired
	private ITicketRepository repository;
	
	@Autowired
	private IStatusRepository statusRepo;
	
	@Autowired
	private HelpdeskConverter converter;
	
	@Autowired
	private UserFeignClient userClient;

	@Override
	public void save(TicketDTO dto) {
		UserDTO user = getUser(dto.getUser());
		TicketEntity entity = converter.toEntity(dto, statusRepo.findByDescriptionIgnoreCase("novo"), user);
		repository.save(entity);
	}

	@Override
	public Page<TicketDTO> findAllByUser(String email, Pageable pageable) {
		UserDTO user = getUser(email);
		List<TicketDTO> result = repository.findByUser(user.getId(), pageable).stream().map(converter::toDTO).collect(Collectors.toList());
		return new PageImpl<>(result, pageable, result.size());
	}
	
	private UserDTO getUser(String user) {
		UserDTO u = userClient.findByEmail(user);
		if (u == null) {
			throw new UserException("User not found");
		}
		return u;
	}
}
