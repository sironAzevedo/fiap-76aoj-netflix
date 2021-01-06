package com.netflix.likes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.likes.service.ILikeService;

@RestController
@RequestMapping(value = "/likes")
public class LikeController {
	
	@Autowired
	private ILikeService service;
	
	@ResponseBody
	@PostMapping("/movie")
	@ResponseStatus(value = HttpStatus.OK)
	public void movie(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "movie") Long movie,
			@RequestParam(value = "like") String like) {
		service.movieLike(email, movie, like);
	}
	
	@ResponseBody
	@PostMapping("/serie")
	@ResponseStatus(value = HttpStatus.OK)
	public void serie(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "serie") Long serie,
			@RequestParam(value = "like") String like) {
		service.serieLike(email, serie, like);
	} 
}
