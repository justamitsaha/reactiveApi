package com.saha.amit.webclient;

import com.saha.amit.dto.MultiplyDto;
import com.saha.amit.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class I_AttributeTest extends  BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    public void headersTest() {
        Mono<Response> responseMono = this.webClient
                .post()
                .uri("reactiveMath/multiply")
                .bodyValue(getMultipleDto(76, 37))
                //.attribute("auth","basic")
                .attribute("auth","jwt")
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private MultiplyDto getMultipleDto(int a, int b) {
        MultiplyDto multiplyDto = new MultiplyDto();
        multiplyDto.setFirst(a);
        multiplyDto.setSecond(b);
        return multiplyDto;
    }
}
