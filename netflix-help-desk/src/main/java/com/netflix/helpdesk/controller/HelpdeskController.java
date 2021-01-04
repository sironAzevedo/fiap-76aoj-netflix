package com.netflix.helpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.helpdesk.model.dto.TicketDTO;
import com.netflix.helpdesk.service.IServiceTicket;

@RestController
@RequestMapping(value = "/ticket")
public class HelpdeskController {
	
	@Autowired
	private IServiceTicket service;
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public void like(@Valid @RequestBody TicketDTO dto) {
		service.save(dto);
	}
	
	@GetMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
    public Page<TicketDTO> tickets(@RequestParam(value = "user") String user, Pageable pageable) {
        return service.findAllByUser(user, pageable);
    } 
}
