package com.netflix.series.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.series.feignClients.factory.CategoryFallbackFactory;
import com.netflix.series.model.dto.CategoryDTO;

@Component
@FeignClient(name = "netflix-category", path = "/category", fallbackFactory = CategoryFallbackFactory.class)
public interface CategoryClient {

	@GetMapping(value = "/detail")
	CategoryDTO category(@RequestParam Long id);

}
