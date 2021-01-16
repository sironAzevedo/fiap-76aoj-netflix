package com.netflix.category.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.netflix.category.model.Category;
import com.netflix.category.model.dto.CategoryDTO;

@Mapper
public interface CategoryConverter {
	
	CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);
	
	@Mappings({
	      @Mapping(target="id", source="entity.id"),
	      @Mapping(target="name", source="entity.name")
	    })
	CategoryDTO toDTO(Category entity);
	
	@Mappings({
	      @Mapping(target="id", source="dto.id"),
	      @Mapping(target="name", source="dto.name")
	    })
	Category toEntity(CategoryDTO dto);
}
