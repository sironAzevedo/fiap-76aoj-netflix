package com.netflix.movies.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.movies.model.dto.MovieDTO;
import com.netflix.movies.model.dto.MovieLikeDTO;
import com.netflix.movies.model.dto.MovieUserDTO;
import com.netflix.movies.model.dto.MovieWatchedDTO;
import com.netflix.movies.model.dto.TopMovieCategoryResponseDTO;
import com.netflix.movies.service.IMovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private IMovieService service;

	@ResponseBody
	@GetMapping("/{id_movie}")
	@ResponseStatus(value = HttpStatus.OK)
	public MovieDTO detail(@PathVariable final Long id_movie) {
		return service.detail(id_movie);
	}

	@ResponseBody
	@GetMapping("/by-category")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MovieDTO> byCategory(@RequestParam(value = "category") Long category, Pageable pageable) {
		return service.byCategory(Long.valueOf(category), pageable);
	}

	@ResponseBody
	@GetMapping("/keyword")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MovieDTO> getKeyword(@RequestParam(value = "word") String word, Pageable pageable) {
		return service.getKeyword(word, pageable);
	}

	@ResponseBody
	@PostMapping("/watched")
	@ResponseStatus(value = HttpStatus.OK)
	public void movieWatched(@Valid @RequestBody MovieWatchedDTO dto) {
		service.watched(dto);
	}

	@ResponseBody
	@GetMapping("/watched")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MovieWatchedDTO> movieWatched(@RequestParam(value = "user") String user, Pageable pageable) {
		return service.watched(user, pageable);
	}
	
	@ResponseBody
	@PostMapping("/like")
	@ResponseStatus(value = HttpStatus.OK)
	public void like(@Valid @RequestBody MovieLikeDTO dto) {
		service.like(dto);
	}
	
	@ResponseBody
	@GetMapping("/likes")
	@ResponseStatus(value = HttpStatus.OK)
    public Page<MovieLikeDTO> likes(@RequestParam(value = "user") String user, Pageable pageable) {
        return service.likes(user, pageable);
    }
	
	@ResponseBody
	@PostMapping("/future")
	@ResponseStatus(value = HttpStatus.OK)
	public void future(@Valid @RequestBody MovieUserDTO dto) {
		service.watchFuture(dto.getUser(), dto.getMovie().getId());
	}
	
	@ResponseBody
	@GetMapping("/futures")
	@ResponseStatus(value = HttpStatus.OK)
    public Page<MovieUserDTO> futures(@RequestParam(value = "user") String user, Pageable pageable) {
        return service.watchFuture(user, pageable);
    }
	
	@ResponseBody
	@GetMapping("/top/by-category")
	@ResponseStatus(value = HttpStatus.OK)
    public List<TopMovieCategoryResponseDTO> getTopMovieByCategory(Pageable pageable) {
        return service.getTopMovieByCategory(pageable);
    }
}
