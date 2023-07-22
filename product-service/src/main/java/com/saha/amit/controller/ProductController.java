package com.saha.amit.controller;

import com.saha.amit.dto.ProductDTO;
import com.saha.amit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value ="all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDTO> all(){
        return this.service.getAll();
    }

//    @GetMapping("price-range")
//    public Flux<ProductDto> getByPriceRange(@RequestParam("min") int min,
//                                            @RequestParam("max") int max){
//        return this.service.getProductByPriceRange(min, max);
//    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable String id){
        //this.simulateRandomException();
        return this.service.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDTO> insertProduct(@RequestBody Mono<ProductDTO> productDtoMono){
        return this.service.insertProduct(productDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDTO> productDtoMono){
        return this.service.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return this.service.deleteProduct(id);
    }

    private void simulateRandomException(){
        int nextInt = ThreadLocalRandom.current().nextInt(1, 10);
        if(nextInt > 5)
            throw new RuntimeException("something is wrong");
    }

}