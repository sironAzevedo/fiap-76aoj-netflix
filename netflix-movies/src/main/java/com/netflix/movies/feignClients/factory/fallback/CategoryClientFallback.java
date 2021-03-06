package com.netflix.movies.feignClients.factory.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.movies.feignClients.CategoryClient;
import com.netflix.movies.model.dto.CategoryDTO;

public class CategoryClientFallback implements CategoryClient {
	private static final Logger log = LoggerFactory.getLogger(CategoryClientFallback.class);

	private final Throwable cause;

	public CategoryClientFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public CategoryDTO category(Long id) {
		log.info("Error API Category: " + cause.getLocalizedMessage());
		return null;
	}
}
