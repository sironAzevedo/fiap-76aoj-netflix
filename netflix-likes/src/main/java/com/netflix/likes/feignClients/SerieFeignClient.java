package com.netflix.likes.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.likes.model.dto.SerieDTO;

@Component
@FeignClient(name = "netflix-series", path = "/series")
public interface SerieFeignClient {

	@GetMapping("/detail")
	SerieDTO detail(@RequestParam Long serie);

}
