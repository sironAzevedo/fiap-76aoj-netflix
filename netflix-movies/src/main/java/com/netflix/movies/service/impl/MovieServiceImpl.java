package com.netflix.movies.service.impl;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.netflix.movies.model.MovieEntity;
import com.netflix.movies.model.MovieLikeEntity;
import com.netflix.movies.model.MovieWatchFutureEntity;
import com.netflix.movies.model.MovieWatchedEntity;
import com.netflix.movies.model.dto.CategoryDTO;
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
	private UserFeignClient userClient;
	
	@Autowired
	private IMovieCategoryRepository mcRepo;
	
	@Autowired
	private IMovieCategoryCustomRepository mcCustomRepo;
	
	@Autowired
	private IMovieLikeRepository movieLikeRepo;
	
	@Autowired
	private CategoryFeignClient categoryClient;
	
	@Autowired
	private IMovieWatchFutureRepository watchFuture;
	
	@Autowired
    private IMovieWatchedRepository movieWatchedRepo;
	
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
		MovieWatchedEntity entity = convert.toMovieWatched(dto, getUser(dto.getUser()).getId());
		movieWatchedRepo.save(entity);
		this.deleteWatchFuture(dto.getUser(), dto.getMovie().getId());
	}
	 

	@Override
	public Page<MovieWatchedDTO> watched(String user, Pageable pageable) {
		List<MovieWatchedDTO> movies = new ArrayList<>();
		movieWatchedRepo.findByUser(getUser(user).getId()).forEach(mc -> {
			MovieWatchedDTO movie = convert.toDTO(mc);
			mcRepo.getCategoryByMovie(mc.getPk().getMovie()).forEach(
					cat -> movie.getMovie().getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			movies.add(movie);			
		});
		return new PageImpl<>(movies, pageable, movies.size());
	}
	
	@Override
	public void like(MovieLikeDTO dto) {
		findById(dto.getMovie().getId());
		MovieLikeEntity entity = convert.toMovieLike(dto, getUser(dto.getUser()).getId());
		movieLikeRepo.save(entity);
	}

	@Override
	public Page<MovieLikeDTO> likes(String user, Pageable pageable) {
		List<MovieLikeDTO> movies = new ArrayList<>();
		movieLikeRepo.findByUser(getUser(user).getId()).forEach(ml -> {
			MovieLikeDTO movie = convert.toDTO(ml);
			mcRepo.getCategoryByMovie(ml.getPk().getMovie()).forEach(
					cat -> movie.getMovie().getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			movies.add(movie);			
		});
		
		return new PageImpl<>(movies, pageable, movies.size());
	}
	
	@Override
	public void watchFuture(String user, Long movie) {
		MovieWatchFutureEntity entity = convert.toMovieWatchFuture(findById(movie), getUser(user).getId());
		watchFuture.save(entity);
	}
	
	@Override
	public Page<MovieUserDTO> watchFuture(String user, Pageable pageable) {
		List<MovieUserDTO> movies = new ArrayList<>();
		watchFuture.findByUser(getUser(user).getId()).forEach(wf -> {
			MovieUserDTO movie = convert.toDTO(wf);
			mcRepo.getCategoryByMovie(wf.getPk().getMovie()).forEach(
					cat -> movie.getMovie().getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			movies.add(movie);			
		});
		
		return new PageImpl<>(movies, pageable, movies.size());
	}
	
	@Override
	public void deleteWatchFuture(String user, Long movie) {
		MovieWatchFutureEntity entity = convert.toMovieWatchFuture(findById(movie), getUser(user).getId());
		Optional<MovieWatchFutureEntity> res = watchFuture.findByUserAndMovie(entity.getPk().getUser(), entity.getPk().getMovie());
		if(res.isPresent()) {
			watchFuture.delete(entity);
		}
	}
	
	@Override
	public List<TopMovieCategoryResponseDTO> getTopMovieByCategory(Pageable pageable) {
		List<TopMovieCategoryResponseDTO> resp = new ArrayList<>();
		List<TopMovieCategoryDTO> movies = mcCustomRepo.getTopMovieByCategory().stream()
		.collect(
				collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
						comparingLong(TopMovieCategoryDTO::getCodMovie))), ArrayList::new));
		
		
		Map<CategoryDTO, List<TopMovieCategoryDTO>> collect = movies.stream().collect(Collectors.groupingBy(c -> categoryClient.category(c.getCategory())));
		for (Entry<CategoryDTO, List<TopMovieCategoryDTO>> entry : collect.entrySet()) {
			TopMovieCategoryResponseDTO dtoResp = new TopMovieCategoryResponseDTO();
			dtoResp.setCategory(entry.getKey().getName());
			
			for (TopMovieCategoryDTO top : entry.getValue()) {
				TopMovieDTO dto = new TopMovieDTO();
				dto.setViews(top.getAmount());
				dto.setMovie(convert.toDTO(findById(top.getCodMovie())));
				dtoResp.getMovies().add(dto);
			}
			
			resp.add(dtoResp);
	    }
		
		return resp;
	}
	
	private MovieEntity findById(Long movie) {
		return repo.findById(movie).orElseThrow(() -> new NotFoundException("movie not found"));
	}
	
	private List<MovieDTO> findBykeywords(String word) {
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
