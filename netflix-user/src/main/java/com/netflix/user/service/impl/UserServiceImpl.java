package com.netflix.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.user.model.dto.UserDTO;
import com.netflix.user.repository.IUserRepository;
import com.netflix.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository repo;

	@Override
	public UserDTO create(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
