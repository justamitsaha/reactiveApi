package com.saha.amit.controller;

import com.saha.amit.dto.MultiplyDto;
import com.saha.amit.dto.Response;
import com.saha.amit.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;


@RestController
@RequestMapping("reactiveMath")
public class ReactiveController {

    @Autowired
    ReactiveMathService reactiveMathService;

    //http://localhost:8080/reactiveMath/square/6
    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        return reactiveMathService.findSquare(input);

    }

    /*If MediaType.TEXT_EVENT_STREAM_VALUE not used then Spring boot uses AbstractJackson2Decoder
    which will collect the response in a Mono of List and return
    http://localhost:8080/reactiveMath/table/8
    */
    @GetMapping(value = "table/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> getMultiplicationTable(@PathVariable int input){
        return reactiveMathService.getMultiplicationTable(input);
    }

    @PostMapping("multiply")
    public  Mono<Response> multiply(@RequestBody Mono<MultiplyDto> dtoMono,
                                    @RequestHeader Map<String,String > header){
        System.out.println(header);
        return reactiveMathService.multiply(dtoMono);
    }


}
