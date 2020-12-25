package com.netflix.user.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.user.converter.UserConverter;
import com.netflix.user.handler.exception.UserException;
import com.netflix.user.model.Role;
import com.netflix.user.model.User;
import com.netflix.user.model.dto.UserDTO;
import com.netflix.user.model.enums.PerfilEnum;
import com.netflix.user.model.enums.UserStatusEnum;
import com.netflix.user.repository.IRoleRepository;
import com.netflix.user.repository.IUserRepository;
import com.netflix.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository repo;
	
	@Autowired
	private IRoleRepository iRole;
	
//	@Autowired
//	private BCryptPasswordEncoder pe;

	@Override
	public UserDTO create(UserDTO dto) {
		
		if (repo.existsByEmail(dto.getEmail())) {
			throw new UserException("Este e-mail já existe");
		}
		
		User user = UserConverter.INSTANCE.toEntity(dto);
//		user.setPassword(pe.encode(dto.getPassword()));
		user.setPassword(dto.getPassword());
		user.setStatus(UserStatusEnum.ACTIVE);
		user.setRoles(iRole.findByName(PerfilEnum.USER));
		repo.save(user);
		return UserConverter.INSTANCE.toDTO(user);
	}

	@Override
	public UserDTO findUserByEmail(String email) {
		return repo.findByEmail(email).map(UserConverter.INSTANCE::toDTO).orElseThrow(()-> new UserException("Usuário não encontrado!"));
	}
}
