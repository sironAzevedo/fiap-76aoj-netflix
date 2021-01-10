package com.netflix.gateway.configuration;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;

	private static final String[] PUBLIC = {
			"/netflix-authorization/oauth/token",
			"/netflix-help-desk/ticket/**",
			"/netflix-user/user",
			"/h2-console/**",
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/v2/api-docs",
			"/webjars/**"
	};

	private static final String[] CLIENT = { 
			"/netflix-category/category/**",
			"/netflix-movies/movies/**",
			"/netflix-series/series/**",
			"/netflix-likes/likes/**"
	};

	private static final String[] ADMIN = { 
			"/actuator/**",
			"/netflix-user/user/by-mail", 
			"/netflix-category/actuator/**",
			"/netflix-movies/movies",
			"/netflix-series/series",
			"/netflix-movies/actuator/**",
			"/netflix-authorization/actuator/**" 
	};
	
	private static final String[] ALLOWED_METHODS = {
			HttpMethod.POST.name(), 
			HttpMethod.GET.name(), 
			HttpMethod.PUT.name(), 
			HttpMethod.DELETE.name(), 
			HttpMethod.PATCH.name()
	};

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers(PUBLIC).permitAll()
			.antMatchers(HttpMethod.GET, CLIENT).hasAnyRole("USER", "ADMIN")
			.antMatchers(ADMIN).hasRole("ADMIN").anyRequest().authenticated();

		http.cors().configurationSource(corsConfigurationSource());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.stream(ALLOWED_METHODS).collect(Collectors.toList()));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
