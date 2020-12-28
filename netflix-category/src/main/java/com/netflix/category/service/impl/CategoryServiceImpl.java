package com.netflix.category.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netflix.category.converter.CategoryConverter;
import com.netflix.category.model.dto.CategoryDTO;
import com.netflix.category.repository.ICategoryRepository;
import com.netflix.category.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository repo;
	
	@Value("${test.config}")
	private String msgConfig;

	@Override
	public Page<CategoryDTO> findAll(Pageable pageable) {
		System.out.println("CONFIG = " + msgConfig);
		Page<CategoryDTO> result = repo.findAll(pageable).map(CategoryConverter.INSTANCE::toDTO);
		return new PageImpl<>(result.stream().collect(Collectors.toList()), pageable, result.getTotalElements());
	}
}