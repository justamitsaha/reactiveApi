package com.saha.amit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.saha.amit.service.RequestHandeler;

@Configuration
public class RouterConfig {

	@Autowired
	private RequestHandeler requestHandeler;

	@Bean
	public RouterFunction<ServerResponse> serverResponseRouterFunction() {
		return RouterFunctions.route()
				.GET("router/square/{input}", requestHandeler::squareHandler)
				.GET("router/table/{input}", requestHandeler::tableHandler)
				.build();
	}
}
