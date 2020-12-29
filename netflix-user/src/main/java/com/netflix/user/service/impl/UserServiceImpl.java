package com.netflix.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.user.converter.UserConverter;
import com.netflix.user.handler.exception.UserException;
import com.netflix.user.model.User;
import com.netflix.user.model.dto.UserDTO;
import com.netflix.user.model.enums.PerfilEnum;
import com.netflix.user.repository.IRoleRepository;
import com.netflix.user.repository.IUserRepository;
import com.netflix.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository repo;
	
	@Autowired
	private IRoleRepository iRole;
	
	@Override
	public UserDTO create(UserDTO dto) {
		
		if (repo.existsByEmail(dto.getEmail())) {
			throw new UserException("Este e-mail já existe");
		}
		
		User user = UserConverter.INSTANCE.toEntity(dto);
		user.setRoles(iRole.findByName(PerfilEnum.USER));
		repo.save(user);
		return UserConverter.INSTANCE.toDTO(user);
	}

	@Override
	public UserDTO findUserByEmail(String email) {
		return repo.findByEmail(email).map(UserConverter.INSTANCE::toDTO).orElseThrow(()-> new UserException("Usuário não encontrado!"));
	}
}
