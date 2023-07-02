package com.saha.amit.config;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.saha.amit.dto.ExceptionResponse;
import com.saha.amit.exception.InputValidationException;
import com.saha.amit.service.RequestHandeler;

import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {

	@Autowired
	private RequestHandeler requestHandeler;

	@Bean
	public RouterFunction<ServerResponse> serverResponseRouterFunction() {
		return RouterFunctions.route()
				.GET("router/square/{input}", requestHandeler::squareHandler)
				.GET("router/table/{input}", requestHandeler::tableHandler)
				.POST("router/multiply",requestHandeler::multiplyHandler)
				.GET("router/square/{input}/validation", requestHandeler::squareHandlerWithValidation)
                .onError(InputValidationException.class, exceptionHandler())
				.build();
	}
	
	
	private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (err, req) -> {
            InputValidationException ex = (InputValidationException) err;
            ExceptionResponse response = new ExceptionResponse();
            response.setInput(ex.getInput());
            response.setMessage(ex.getMessage());
            response.setErrorCode(ex.getErrorCode());
            return ServerResponse.badRequest().bodyValue(response);
        };
    }
}
