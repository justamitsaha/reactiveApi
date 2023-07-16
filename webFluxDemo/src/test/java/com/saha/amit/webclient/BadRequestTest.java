package com.saha.amit.webclient;

import com.saha.amit.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BadRequestTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void badRequestTest() {
        Mono<Response> responseMono = this.webClient
                .get()
                .uri("reactiveMath/square/{input}/throw", 12)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));


//        StepVerifier.create(responseMono)
//                .expectNextCount(1)
//                .verifyComplete();

        StepVerifier.create(responseMono)
                .verifyError(WebClientResponseException.BadRequest.class);
    }
}
