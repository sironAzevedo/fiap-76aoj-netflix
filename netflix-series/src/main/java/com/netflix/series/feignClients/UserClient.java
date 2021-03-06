package com.netflix.series.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.series.model.dto.UserDTO;

@Component
@FeignClient(name = "netflix-user", path = "/user")
public interface UserClient {

	@GetMapping(value = "/by-mail")
	UserDTO findByEmail(@RequestParam String email);
}
