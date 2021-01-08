package com.netflix.movies.feignClients.factory;

import org.springframework.stereotype.Component;

import com.netflix.movies.feignClients.UserClient;
import com.netflix.movies.feignClients.factory.fallback.UserClientFallback;

import feign.hystrix.FallbackFactory;

@Component
public class UserFallbackFactory implements FallbackFactory<UserClient> {

	@Override
	public UserClient create(Throwable cause) {
		return new UserClientFallback(cause);
	}

}
