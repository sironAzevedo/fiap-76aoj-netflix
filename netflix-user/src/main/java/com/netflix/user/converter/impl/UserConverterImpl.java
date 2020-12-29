package com.netflix.user.converter.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.netflix.user.converter.UserConverter;
import com.netflix.user.model.Role;
import com.netflix.user.model.User;
import com.netflix.user.model.dto.RoleDTO;
import com.netflix.user.model.dto.UserDTO;
import com.netflix.user.model.enums.UserStatusEnum;

@Component
public class UserConverterImpl implements UserConverter {
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Override
	public UserDTO toDTO(User entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setStatus(entity.getStatus());
		dto.setRoles(getRoles(entity.getRoles()));
		return dto;
	}

	@Override
	public User toEntity(UserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(pe.encode(dto.getPassword()));
		user.setStatus(UserStatusEnum.ACTIVE);
		return user;
	}
	
	protected Set<RoleDTO> getRoles(Set<Role> roles) {
		Set<RoleDTO> result = Set.of();
		for (Role role : roles) {
			RoleDTO dto = new RoleDTO();
			dto.setId(role.getId());
			dto.setName(role.getName());
			result.add(dto);
		}
		return result;
	}
}
