package com.netflix.movies.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.movies.handler.StandardError;
import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.model.dto.MovieLikeDTO;
import com.netflix.movies.model.dto.MovieUserDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;
import com.netflix.movies.model.dto.TopMovieCategoryResponseDTO;
import com.netflix.movies.service.IMovieService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/movies")
@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class) })
public class MovieController {

	@Autowired
	private IMovieService service;
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Save movie")
	public void create(@Valid @RequestBody MovieDTO dto) {
		service.create(dto);
	}

	@GetMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Find All")
	public Page<MovieDTO> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@ResponseBody
	@GetMapping("/detail")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Detail movie")
	public MovieDTO detail(@RequestParam(value = "movie") Long movie, @RequestParam(value = "user", required = false) String user) {
		return service.detail(movie, user);
	}

	@ResponseBody
	@GetMapping("/by-category")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Search By category")
	public Page<MovieDTO> byCategory(@RequestParam(value = "category") Long category, Pageable pageable) {
		return service.byCategory(Long.valueOf(category), pageable);
	}

	@ResponseBody
	@GetMapping("/keyword")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Search By keyword")
	public Page<MovieDTO> getKeyword(@RequestParam(value = "word") String word, Pageable pageable) {
		return service.getKeyword(word, pageable);
	}

	@ResponseBody
	@PostMapping("/watched")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Save movie watched")
	public void movieWatched(@Valid @RequestBody MovieWatchedDTO dto) {
		service.watched(dto);
	}

	@ResponseBody
	@GetMapping("/watched")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Search movie watched")
	public Page<MovieWatchedDTO> movieWatched(@RequestParam(value = "user") String user, Pageable pageable) {
		return service.watched(user, pageable);
	}

	@ResponseBody
	@GetMapping("/likes")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MovieLikeDTO> likes(@RequestParam(value = "user") String user, Pageable pageable) {
		return service.likes(user, pageable);
	}

	@ResponseBody
	@PostMapping("/future")
	@ApiOperation(value = "Save movie watch future")
	@ResponseStatus(value = HttpStatus.OK)
	public void future(@Valid @RequestBody MovieUserDTO dto) {
		service.watchFuture(dto.getUser(), dto.getMovie().getId());
	}

	@ResponseBody
	@GetMapping("/futures")
	@ApiOperation(value = "Search movie watch future")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MovieUserDTO> futures(@RequestParam(value = "user") String user, Pageable pageable) {
		return service.watchFuture(user, pageable);
	}

	@ResponseBody
	@GetMapping("/top/by-category")
	@ApiOperation(value = "Search top movie by category")
	@ResponseStatus(value = HttpStatus.OK)
	public List<TopMovieCategoryResponseDTO> getTopMovieByCategory(Pageable pageable) {
		return service.getTopMovieByCategory(pageable);
	}
}
