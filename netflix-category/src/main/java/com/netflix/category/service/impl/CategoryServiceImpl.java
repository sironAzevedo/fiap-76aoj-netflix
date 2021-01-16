package com.netflix.category.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netflix.category.converter.CategoryConverter;
import com.netflix.category.model.dto.CategoryDTO;
import com.netflix.category.repository.ICategoryRepository;
import com.netflix.category.service.ICategoryService;

@Service
@RefreshScope
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository repo;
	
	@Override
	@CacheEvict(cacheNames = CategoryDTO.CACHE_NAME, allEntries = true)
	public void create(CategoryDTO dto) {
		repo.save(CategoryConverter.INSTANCE.toEntity(dto));
	}

	@Override
	@Cacheable(cacheNames = CategoryDTO.CACHE_NAME, key="#pageable.getPageNumber() + #pageable.getPageSize()")
	public Page<CategoryDTO> findAll(Pageable pageable) {
		Page<CategoryDTO> result = repo.findAll(pageable).map(CategoryConverter.INSTANCE::toDTO);
		return new PageImpl<>(result.stream().collect(Collectors.toList()), pageable, result.getTotalElements());
	}

	@Override
	@Cacheable(cacheNames = CategoryDTO.CACHE_NAME, key="#id")
	public CategoryDTO findById(Long id) {
		return repo.findById(id).map(CategoryConverter.INSTANCE::toDTO).orElse(null);
	}

	@Override
	@CacheEvict(cacheNames = CategoryDTO.CACHE_NAME, allEntries = true)
	public void delete(Long id) {
		Optional.ofNullable(findById(id)).ifPresent(c -> repo.deleteById(c.getId()));
	}
}