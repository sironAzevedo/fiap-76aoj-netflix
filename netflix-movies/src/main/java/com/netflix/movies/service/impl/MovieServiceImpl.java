package com.netflix.movies.service.impl;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netflix.movies.converter.MovieConverter;
import com.netflix.movies.feignClients.CategoryFeignClient;
import com.netflix.movies.feignClients.UserFeignClient;
import com.netflix.movies.handler.exception.NotFoundException;
import com.netflix.movies.handler.exception.UserException;
import com.netflix.movies.model.MovieWatchedEntity;
import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;
import com.netflix.movies.model.dto.UserDTO;
import com.netflix.movies.repository.IMovieCategoryRepository;
import com.netflix.movies.repository.IMovieRepository;
import com.netflix.movies.repository.IMovieWatchedRepository;
import com.netflix.movies.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository repo;
	
	@Autowired
	private IMovieCategoryRepository mcRepo;
	
	@Autowired
	private CategoryFeignClient categoryClient;
	
	@Autowired
	private UserFeignClient userClient;
	
	@Autowired
    private IMovieWatchedRepository movieWatchedRepo;
	
	@Autowired
	private MovieConverter convert;
	
	@Override
	public Page<MovieDTO> byCategory(Long idCategory, Pageable pageable) {

		List<MovieDTO> movies = new ArrayList<>();
		mcRepo.getMoviesByCategory(idCategory).forEach(mc -> {
			MovieDTO movie = convert.toDTO(mc.getPk().getMovie());
			mcRepo.getCategoryByMovie(mc.getPk().getMovie()).forEach(
					cat -> movie.getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			movies.add(movie);
		});
		return new PageImpl<>(movies, pageable, movies.size());
	}

	@Override
	public MovieDTO detail(Long id_movie) {
		Optional<MovieDTO> map = repo.findById(id_movie).map(convert::toDTO);
		return map.get();
	}

	@Override
	public Page<MovieDTO> getKeyword(String word, Pageable pageable) {
		final String[] queries = word.split(" ");
		List<MovieDTO> movies = new ArrayList<>();
		
		Arrays.stream(queries)
			.forEach(w -> movies.addAll(findBykeywordsKeywordContainingIgnoreCase(w)));
		
		List<MovieDTO> result = movies.stream()
				.collect(
						collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
								comparingLong(MovieDTO::getId))), ArrayList::new));
		
		return new PageImpl<>(result, pageable, result.size());
	}
	
	@Override
	public void movieWatched(MovieWatchedDTO dto) {
		UserDTO u = getUser(dto.getUser());
		repo.findById(dto.getMovie().getId()).orElseThrow(() -> new NotFoundException("movie not found"));
		MovieWatchedEntity entity = convert.toMovieWatched(dto, u.getId());
		movieWatchedRepo.save(entity);
	}
	
	@Override
	public Page<MovieWatchedDTO> movieWatched(String user, Pageable pageable) {
		UserDTO u = getUser(user);
		List<MovieWatchedDTO> movies = new ArrayList<>();
		movieWatchedRepo.findByUser(u.getId()).forEach(mc -> {
			MovieWatchedDTO movie = convert.toDTO(mc);
			mcRepo.getCategoryByMovie(mc.getPk().getMovie()).forEach(
					cat -> movie.getMovie().getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			movies.add(movie);			
		});
		return new PageImpl<>(movies, pageable, movies.size());
	}
	
	private List<MovieDTO> findBykeywordsKeywordContainingIgnoreCase(String word) {
		List<MovieDTO> movies = new ArrayList<>();
		repo.findBykeywordsKeywordContainingIgnoreCase(word).forEach(r -> {
			MovieDTO movie = convert.toDTO(r);
			mcRepo.getCategoryByMovie(r).forEach(cat -> movie.getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			movies.add(movie);
		}); 
		return movies;
	}
	
	private UserDTO getUser(String user) {
		UserDTO u = userClient.findByEmail(user);
		if (u == null) {
			throw new UserException("User not found");
		}
		return u;
	}
}
