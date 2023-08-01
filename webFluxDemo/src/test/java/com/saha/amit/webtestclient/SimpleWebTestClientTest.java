package com.saha.amit.webtestclient;

import com.saha.amit.dto.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
public class SimpleWebTestClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void stepVerifier() {
        Flux<Response> responseMono = this.webTestClient
                .get()
                .uri("/reactiveMath/square/{number}", 5)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(Response.class)
                .getResponseBody();

        StepVerifier.create(responseMono)
                .expectNextMatches(r -> r.getOutput() == 25)
                .verifyComplete();
    }

    @Test
    public void fluentAssertionTest(){
        this.webTestClient
                .get()
                .uri("/reactiveMath/square/{number}", 5)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Response.class)
                .value(r -> Assertions.assertEquals(25,r.getOutput()));

    }
}
