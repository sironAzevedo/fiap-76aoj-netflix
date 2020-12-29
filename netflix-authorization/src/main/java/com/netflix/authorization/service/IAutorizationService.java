package com.netflix.authorization.service;

import com.netflix.authorization.model.dto.UserDTO;

public interface IAutorizationService {
	
	UserDTO findByEmail(String email);

}
