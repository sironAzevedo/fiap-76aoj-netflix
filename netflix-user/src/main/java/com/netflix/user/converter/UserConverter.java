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
	
	@Mappings({
	      @Mapping(target="id", source="entity.id", ignore = true),
	      @Mapping(target="name", source="entity.name"),
	      @Mapping(target="email", source="entity.email"),
	      @Mapping(target="password", source="entity.password", ignore = true)
	    })
	UserDTO toDTO(User entity);
	
	@Mappings({
	      @Mapping(target="id", source="dto.id"),
	      @Mapping(target="name", source="dto.name"),
	      @Mapping(target="email", source="dto.email")
	    })
	User toEntity(UserDTO dto);

}
