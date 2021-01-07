package com.netflix.movies.feignClients.factory;

import org.springframework.stereotype.Component;

import com.netflix.movies.feignClients.CategoryClient;
import com.netflix.movies.feignClients.factory.fallback.CategoryClientFallback;

import feign.hystrix.FallbackFactory;

@Component
public class CategoryFallbackFactory implements FallbackFactory<CategoryClient> {

	@Override
	public CategoryClient create(Throwable cause) {
		return new CategoryClientFallback(cause);
	}
}
