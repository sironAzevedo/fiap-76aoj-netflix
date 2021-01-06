package com.netflix.likes.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.likes.model.dto.MovieDTO;

@Component
@FeignClient(name = "netflix-movies", path = "/movies")
public interface MovieFeignClient {

	@GetMapping("/detail")
	MovieDTO detail(@RequestParam Long movie);

}
