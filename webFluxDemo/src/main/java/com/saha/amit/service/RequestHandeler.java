package com.saha.amit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.saha.amit.dto.MultiplyDto;
import com.saha.amit.dto.Response;
import com.saha.amit.exception.InputValidationException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandeler {
	
	@Autowired
	private ReactiveMathService reactiveMathService;
	
	public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Mono<Response> mono = reactiveMathService.findSquare(input);
		return ServerResponse.ok().body(mono, Response.class);
	}
	
	public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Flux<Response> mono = reactiveMathService.getMultiplicationTable(input);
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(mono, Response.class);
	}
	
	public Mono<ServerResponse>multiplyHandler(ServerRequest serverRequest){
		Mono<MultiplyDto> requestDtoMono = serverRequest.bodyToMono(MultiplyDto.class);
		Mono<Response> mono = reactiveMathService.multiply(requestDtoMono);
		return ServerResponse.ok().body(mono, Response.class);
	}
	
	public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		if(input<5) {
			return Mono.error(new InputValidationException(input));
		}
		Mono<Response> mono = reactiveMathService.findSquare(input);
		return ServerResponse.ok().body(mono, Response.class);
	}

}
