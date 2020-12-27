package com.netflix.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.service.IMovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private IMovieService service;

	@ResponseBody
	@GetMapping("/category/{category}")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MovieDTO> byCategory(@PathVariable final Long category, Pageable pageable) {
		return service.byCategory(category, pageable);
	}
	
	@ResponseBody
	@GetMapping("/{id_movie}")
	@ResponseStatus(value = HttpStatus.OK)
	public MovieDTO detail(@PathVariable final Long id_movie) {
		return service.detail(id_movie);
	}
}
