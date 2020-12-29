package com.netflix.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.user.model.dto.UserDTO;
import com.netflix.user.service.IUserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public UserDTO create(@Valid @RequestBody UserDTO dto) {		
		return service.create(dto);
	}
	
	@ResponseBody
	@GetMapping(value = "/by-mail")
	@ResponseStatus(value = HttpStatus.OK)
	public UserDTO findByEmail(@RequestParam(value = "email") String email) {
		return service.findUserByEmail(email);
	}
}
