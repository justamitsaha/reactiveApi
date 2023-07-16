package com.saha.amit.webclient;

import com.saha.amit.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxResponseWebClientTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    public void stepVerifierFlux() {
        Flux<Response> responseFlux = this.webClient
                .get()
                .uri("reactiveMath/table/{number}", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseFlux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    public void stepVerifierFluxStreamingEndPont() {
        Flux<Response> responseFlux = this.webClient
                .get()
                .uri("reactiveMath/table/{number}/stream", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseFlux)
                .expectNextCount(10)
                .verifyComplete();
    }


}
