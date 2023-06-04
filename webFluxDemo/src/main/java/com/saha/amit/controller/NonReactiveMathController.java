package com.saha.amit.controller;

import com.saha.amit.dto.Response;
import com.saha.amit.service.NonReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("nonReactiveMath")
public class NonReactiveMathController {

    @Autowired
    NonReactiveMathService nonReactiveMathService;

    //http://localhost:8080/nonReactiveMath/square/6
    @GetMapping("square/{input}")
    public Response findSquare(@PathVariable int input) {
        return nonReactiveMathService.findSquare(input);

    }

    //http://localhost:8080/nonReactiveMath/table/6
    @GetMapping("table/{input}")
    public List<Response> getMultiplicationTable(@PathVariable int input){
        return nonReactiveMathService.getMultiplicationTable(input);
    }
}
