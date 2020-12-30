package com.netflix.user.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.netflix.user.model.User;
import com.netflix.user.model.dto.UserDTO;

@Mapper
public interface UserConverter {
	
	UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
	
	UserDTO toDTO(User entity);
	
	@Mappings({
	      @Mapping(target="name", source="dto.name"),
	      @Mapping(target="email", source="dto.email"),
	      @Mapping(target="status", source="dto.status", defaultExpression = "java(com.netflix.user.model.enums.UserStatusEnum.ACTIVE)" )
	    })
	User toEntity(UserDTO dto);

}
