package com.netflix.movies.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netflix.movies.converter.MovieConverter;
import com.netflix.movies.feignClients.CategoryFeignClient;
import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.repository.IMovieCategoryRepository;
import com.netflix.movies.repository.IMovieRepository;
import com.netflix.movies.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository repo;
	
	@Autowired
	private IMovieCategoryRepository mcRepo;
	
	@Autowired
	private CategoryFeignClient categoryClient;
	
	@Override
	public Page<MovieDTO> byCategory(Long idCategory, Pageable pageable) {

		List<MovieDTO> movies = new ArrayList<>();
		mcRepo.getMoviesByCategory(idCategory).forEach(mc -> {
			MovieDTO movie = MovieConverter.INSTANCE.toDTO(mc.getPk().getMovie());
			mcRepo.getCategoryByMovie(mc.getPk().getMovie()).forEach(cat -> movie.getCategories().add(categoryClient.category(cat.getPk().getCategory())));
			movies.add(movie);
		});
		return new PageImpl<>(movies, pageable, movies.size());
	}

	@Override
	public MovieDTO detail(Long id_movie) {
		Optional<MovieDTO> map = repo.findById(id_movie).map(MovieConverter.INSTANCE::toDTO);
		return map.get();
	}
}
