package com.saha.amit.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.saha.amit.dto.Response;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class SingleResponseWebclientTest extends BaseTest {
	
	@Autowired
	private WebClient webClient;
	
	@Test
	public void blockTest() {
		var response  =this.webClient
		.get()
		.uri("reactiveMath/square/{number}",5)
		.retrieve()
		.bodyToMono(Response.class)
		.block();
		
		System.out.println(response);
	}


	@Test
	public void stepVerifier() {
		Mono<Response> responseMono=this.webClient
				.get()
				.uri("reactiveMath/square/{number}",5)
				.retrieve()
				.bodyToMono(Response.class);


		StepVerifier.create(responseMono)
				.expectNextMatches(r -> r.getOutput() == 25)
				.verifyComplete();
	}

}
