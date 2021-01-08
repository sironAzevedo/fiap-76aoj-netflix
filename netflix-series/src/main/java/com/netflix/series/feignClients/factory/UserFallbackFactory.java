package com.netflix.series.feignClients.factory;

import org.springframework.stereotype.Component;

import com.netflix.series.feignClients.UserClient;
import com.netflix.series.feignClients.factory.fallback.UserClientFallback;

import feign.hystrix.FallbackFactory;

@Component
public class UserFallbackFactory implements FallbackFactory<UserClient> {

	@Override
	public UserClient create(Throwable cause) {
		return new UserClientFallback(cause);
	}
}
