package com.saha.amit.service;

import com.saha.amit.dto.MultiplyDto;
import com.saha.amit.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int n){

        return Mono.just(new Response(n*n));
    }

    public Flux<Response> getMultiplicationTable(int n){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing " + i))
                .map(i -> new Response(i*n));
    }

    public  Mono<Response> multiply(Mono<MultiplyDto> dtoMono){
        return dtoMono
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }
}
