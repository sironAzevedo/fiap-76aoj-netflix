package com.netflix.movies.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netflix.movies.converter.MovieConverter;
import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.repository.IMovieRepository;
import com.netflix.movies.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository repo;
	
	@Override
	public Page<MovieDTO> byCategory(Long idCategory, Pageable pageable) {
		Page<MovieDTO> result = repo.findByCategoriesId(idCategory, pageable).map(MovieConverter.INSTANCE::toDTO);
		return new PageImpl<>(result.stream().collect(Collectors.toList()), pageable, result.getTotalElements());
	}

	@Override
	public MovieDTO detail(Long id_movie) {
		Optional<MovieDTO> map = repo.findById(id_movie).map(MovieConverter.INSTANCE::toDTO);
		return map.get();
	}

}
