package com.netflix.user.service;

import com.netflix.user.model.dto.UserDTO;

public interface IUserService {

	UserDTO create(UserDTO dto);

	UserDTO findUserByEmail(final String email);
}
