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
import com.netflix.movies.feignClients.CategoryClient;
import com.netflix.movies.feignClients.UserClient;
import com.netflix.movies.handler.exception.NotFoundException;
import com.netflix.movies.handler.exception.UserException;
import com.netflix.movies.kafka.producer.MovieWatchedProducer;
import com.netflix.movies.model.MovieEntity;
import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.model.dto.MovieLikeDTO;
import com.netflix.movies.model.dto.MovieUserDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;
import com.netflix.movies.model.dto.TopMovieCategoryDTO;
import com.netflix.movies.model.dto.TopMovieCategoryResponseDTO;
import com.netflix.movies.model.dto.TopMovieDTO;
import com.netflix.movies.model.dto.UserDTO;
import com.netflix.movies.repository.IMovieCategoryRepository;
import com.netflix.movies.repository.IMovieLikeRepository;
import com.netflix.movies.repository.IMovieRepository;
import com.netflix.movies.repository.IMovieWatchFutureRepository;
import com.netflix.movies.repository.IMovieWatchedRepository;
import com.netflix.movies.repository.custom.IMovieCategoryCustomRepository;
import com.netflix.movies.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository repo;
	
	@Autowired
	private MovieConverter convert;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private IMovieCategoryRepository mcRepo;
	
	@Autowired
	private IMovieCategoryCustomRepository mcCustomRepo;
	
	@Autowired
	private IMovieLikeRepository movieLikeRepo;
	
	@Autowired
	private CategoryClient categoryClient;
	
	@Autowired
	private IMovieWatchFutureRepository watchFuture;
	
	@Autowired
    private IMovieWatchedRepository movieWatchedRepo;
	
	@Autowired
	private MovieWatchedProducer producer;
	
	@Override
	public Page<MovieDTO> byCategory(Long idCategory, Pageable pageable) {
		List<MovieDTO> movies = new ArrayList<>();
		mcRepo.findByCategory(idCategory).forEach(mc -> movies.add(convert.toDTO(mc.getMovie())));
		return new PageImpl<>(movies, pageable, movies.size());
	}

	@Override
	public MovieDTO detail(Long id_movie) {
		return repo.findById(id_movie).map(convert::toDTO).get();
	}

	@Override
	public Page<MovieDTO> getKeyword(String word, Pageable pageable) {
		final String[] queries = word.split(" ");
		List<MovieDTO> movies = new ArrayList<>();
		
		Arrays.stream(queries)
			.forEach(w -> movies.addAll(findBykeywords(w)));
		
		List<MovieDTO> result = movies.stream()
				.collect(
						collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
								comparingLong(MovieDTO::getId))), ArrayList::new));
		
		return new PageImpl<>(result, pageable, result.size());
	}
	
	@Override
	public void watched(MovieWatchedDTO dto) {
		findById(dto.getMovie().getId());
		movieWatchedRepo.save(convert.toMovieWatched(dto, getUser(dto.getUser()).getId()));
		producer.sendMessage(dto);
	}

	@Override
	public Page<MovieWatchedDTO> watched(String user, Pageable pageable) {
		List<MovieWatchedDTO> movies = new ArrayList<>();
		movieWatchedRepo.findByUser(getUser(user).getId()).forEach(mc -> movies.add(convert.toDTO(mc)));
		return new PageImpl<>(movies, pageable, movies.size());
	}
	
	@Override
	public void like(MovieLikeDTO dto) {
		findById(dto.getMovie().getId());
		movieLikeRepo.save(convert.toMovieLike(dto, getUser(dto.getUser()).getId()));
	}

	@Override
	public Page<MovieLikeDTO> likes(String user, Pageable pageable) {
		List<MovieLikeDTO> movies = new ArrayList<>();
		movieLikeRepo.findByUser(getUser(user).getId()).forEach(ml -> movies.add(convert.toDTO(ml)));
		return new PageImpl<>(movies, pageable, movies.size());
	}
	
	@Override
	public void watchFuture(String user, Long movie) {
		watchFuture.save(convert.toMovieWatchFuture(findById(movie), getUser(user).getId()));
	}
	
	@Override
	public Page<MovieUserDTO> watchFuture(String user, Pageable pageable) {
		List<MovieUserDTO> movies = new ArrayList<>();
		watchFuture.findByUser(getUser(user).getId()).forEach(wf -> movies.add(convert.toDTO(wf)));
		return new PageImpl<>(movies, pageable, movies.size());
	}
	
	@Override
	public void deleteWatchFuture(String user, Long movie) {
		watchFuture.findByUserAndMovie(getUser(user).getId(), findById(movie)).ifPresent(r -> watchFuture.delete(r));
	}
	
	@Override
	public List<TopMovieCategoryResponseDTO> getTopMovieByCategory(Pageable pageable) {
		List<TopMovieCategoryResponseDTO> resp = new ArrayList<>();
		
		List<TopMovieCategoryDTO> movies = mcCustomRepo.getTopMovieByCategory().stream()
		.collect(
				collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
						comparingLong(TopMovieCategoryDTO::getCodMovie))), ArrayList::new));
		
		movies.stream().collect(Collectors.groupingBy(c -> c.getCategory())).entrySet().forEach(entry -> {
			Optional.ofNullable(categoryClient.category(entry.getKey())).ifPresent(category -> {
				TopMovieCategoryResponseDTO dtoResp = new TopMovieCategoryResponseDTO();
				dtoResp.setCategory(category.getName());
				entry.getValue().forEach(top -> {
					TopMovieDTO dto = new TopMovieDTO();
					dto.setViews(top.getAmount());
					dto.setMovie(convert.toDTO(findById(top.getCodMovie())));
					dtoResp.getMovies().add(dto);
				});

				resp.add(dtoResp);				
			});
		});
		
		return resp;
	}
	
	private MovieEntity findById(Long movie) {
		return repo.findById(movie).orElseThrow(() -> new NotFoundException("movie not found"));
	}
	
	private List<MovieDTO> findBykeywords(String word) {
		List<MovieDTO> movies = new ArrayList<>();
		repo.findBykeywordsKeywordContainingIgnoreCase(word).forEach(r -> movies.add(convert.toDTO(r))); 
		return movies;
	}
	
	private UserDTO getUser(String user) {
		return Optional.ofNullable(userClient.findByEmail(user)).orElseThrow(() -> new UserException("User not found"));
	}
}
