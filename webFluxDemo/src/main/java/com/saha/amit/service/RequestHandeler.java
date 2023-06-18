package com.saha.amit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.saha.amit.dto.Response;

import reactor.core.publisher.Mono;

public class RequestHandeler {
	
	@Autowired
	private ReactiveMathService reactiveMathService;
	
	public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Mono<Response> mono = reactiveMathService.findSquare(input);
		return ServerResponse.ok().body(mono, Response.class);
	}

}
