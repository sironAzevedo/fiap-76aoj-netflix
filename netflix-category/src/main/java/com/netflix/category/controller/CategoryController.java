package com.netflix.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.category.model.dto.CategoryDTO;
import com.netflix.category.service.ICategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private ICategoryService service;

	@GetMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Page<CategoryDTO> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}
	
	@ResponseBody
	@GetMapping(value = "/detail")
	@ResponseStatus(value = HttpStatus.OK)
	public CategoryDTO category(@RequestParam(value = "id") Long id) {
		return service.findById(id);
	}
}
