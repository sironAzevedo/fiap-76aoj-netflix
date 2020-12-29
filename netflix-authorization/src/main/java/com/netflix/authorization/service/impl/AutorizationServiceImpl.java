package com.netflix.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.authorization.feignclients.UserFeignClient;
import com.netflix.authorization.model.dto.UserDTO;
import com.netflix.authorization.service.IAutorizationService;

@Service
public class AutorizationServiceImpl implements IAutorizationService {

	@Autowired
	private UserFeignClient userClient;
	
	@Override
	public UserDTO findByEmail(String email) {
		
		UserDTO user = userClient.findByEmail(email);
		if (user == null) {
			throw new IllegalArgumentException("Email not found");
		}
		return user;
	}
}
