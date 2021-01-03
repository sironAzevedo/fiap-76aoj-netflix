package com.netflix.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.netflix.authorization.feignclients.UserFeignClient;
import com.netflix.authorization.handler.exception.UserException;
import com.netflix.authorization.model.dto.UserDTO;
import com.netflix.authorization.service.IAutorizationService;

@Service
public class AutorizationServiceImpl implements IAutorizationService {

	@Autowired
	private UserFeignClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userClient.findByEmail(username);
		if (user == null) {
			throw new UserException("user not found");
		}
		return user;
	}
}
