package com.netflix.movies.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.movies.feignClients.factory.UserFallbackFactory;
import com.netflix.movies.model.dto.UserDTO;

@Component
@FeignClient(name = "netflix-user", path = "/user", fallbackFactory = UserFallbackFactory.class)
public interface UserClient {

	@GetMapping(value = "/by-mail")
	UserDTO findByEmail(@RequestParam String email);

}
