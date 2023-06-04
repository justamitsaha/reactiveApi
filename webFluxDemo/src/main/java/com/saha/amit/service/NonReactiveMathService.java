package com.saha.amit.service;

import com.saha.amit.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class NonReactiveMathService {

    public Response findSquare(int num) {
        return new Response(num * num);
    }

    public List<Response> getMultiplicationTable(int num){
        return IntStream.rangeClosed(1,10)
                .peek(i-> DelayService.addDelayInSeconds(1))
                .peek(i-> System.out.println("Getting response for "+i))
                .mapToObj(i -> new Response(i* num))
                .collect(Collectors.toList());
    }

}
