package com.netflix.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.authorization.model.dto.UserDTO;
import com.netflix.authorization.service.IAutorizationService;

@RestController
@RequestMapping(value = "/oauth")
public class AuthorizationController {
	
	@Autowired
	private IAutorizationService service;
	
	@ResponseBody
	@GetMapping(value = "/search/user/by-mail")
	@ResponseStatus(value = HttpStatus.OK)
	public UserDTO findByEmail(@RequestParam(value = "email") String email) {
		return service.findByEmail(email);
	}

}
