package com.netflix.category.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netflix.category.model.dto.CategoryDTO;

public interface ICategoryService {
	
	void create(CategoryDTO dto);

	Page<CategoryDTO> findAll(Pageable pageable);
	
	CategoryDTO findById(Long id);
	
	void delete(Long id);

}
