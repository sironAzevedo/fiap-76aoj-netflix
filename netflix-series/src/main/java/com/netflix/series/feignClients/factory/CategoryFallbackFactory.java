package com.netflix.series.feignClients.factory;

import org.springframework.stereotype.Component;

import com.netflix.series.feignClients.CategoryClient;
import com.netflix.series.feignClients.factory.fallback.CategoryClientFallback;

import feign.hystrix.FallbackFactory;

@Component
public class CategoryFallbackFactory implements FallbackFactory<CategoryClient> {

	@Override
	public CategoryClient create(Throwable cause) {
		return new CategoryClientFallback(cause);
	}
}
