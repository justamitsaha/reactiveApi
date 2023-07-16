package com.saha.amit.webclient;

import com.saha.amit.dto.Response;
import com.saha.amit.exception.InputValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class F_Exchange extends BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    public void badRequestTestExchange() {
        Mono<Object> responseMono = this.webClient
                .get()
                .uri("reactiveMath/square/{input}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse clr){
        if (clr.rawStatusCode() == 400){
            return clr.bodyToMono(InputValidationException.class);
        }else
            return clr.bodyToMono(Response.class);
    }
}
