package com.netflix.helpdesk.converter;

import org.mapstruct.Mapper;

import com.netflix.helpdesk.model.StatusEntity;
import com.netflix.helpdesk.model.TicketEntity;
import com.netflix.helpdesk.model.dto.TicketDTO;
import com.netflix.helpdesk.model.dto.UserDTO;

@Mapper(componentModel = "spring")
public abstract class HelpdeskConverter {
	
	public TicketDTO toDTO(TicketEntity entity) {
		TicketDTO dto = new TicketDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setStatus(entity.getStatus().getDescription());
		return dto;
	} 
	
	public TicketEntity toEntity(TicketDTO dto, StatusEntity status, UserDTO user) {
		TicketEntity entity = new TicketEntity();
		entity.setUser(user.getId());
		entity.setDescription(dto.getDescription());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setStatus(status);
		return entity;
	}
}
