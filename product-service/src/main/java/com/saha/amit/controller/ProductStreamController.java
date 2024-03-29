package com.saha.amit.controller;

import com.saha.amit.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("product")
public class ProductStreamController {

    @Autowired
    private Flux<ProductDTO> flux;

    @GetMapping(value = "stream/{maxPrice}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDTO> getProductUpdates(@PathVariable int maxPrice){
        return this.flux
                .filter(dto -> dto.getPrice() <= maxPrice);
    }

}
