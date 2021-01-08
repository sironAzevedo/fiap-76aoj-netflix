package com.netflix.series.feignClients.factory.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.series.feignClients.UserClient;
import com.netflix.series.model.dto.UserDTO;

public class UserClientFallback implements UserClient {
	private static final Logger log = LoggerFactory.getLogger(UserClientFallback.class);
	
	private final Throwable cause;
	
	public UserClientFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public UserDTO findByEmail(String email) {
		log.info("Error API Category: " + ((RuntimeException) cause).getCause().getLocalizedMessage());
		return null;
	} 
}
