package com.netflix.user.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.netflix.user.model.User;
import com.netflix.user.model.dto.UserDTO;

@Mapper
public interface UserConverter {

	UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

	UserDTO toDTO(User entity);

	User toEntity(UserDTO dto);
}
