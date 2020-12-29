package com.netflix.authorization.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.authorization.model.dto.UserDTO;

@Component
@FeignClient(name = "netflix-user", path = "/user")
public interface UserFeignClient {

	@GetMapping(value = "/by-mail")
	UserDTO findByEmail(@RequestParam String email);

}
