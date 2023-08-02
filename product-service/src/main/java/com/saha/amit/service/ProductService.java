package com.saha.amit.service;

import com.saha.amit.dto.ProductDTO;
import com.saha.amit.repository.ProductRepository;
import com.saha.amit.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Sinks.Many<ProductDTO> sink;

    public Flux<ProductDTO> getAll() {
        return productRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }


    public Mono<ProductDTO> getProductById(String id) {
        return productRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDTO> insertProduct(Mono<ProductDTO> productDTOMono) {
        return productDTOMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.productRepository::insert)
                .map(EntityDtoUtil::toDto)
                .doOnNext(this.sink::tryEmitNext);
    }

    public Mono<ProductDTO> updateProduct(String id, Mono<ProductDTO> productDTOMono){
        return this.productRepository.findById(id)
                .flatMap(p ->productDTOMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(this.productRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteProduct(String id){
        return this.productRepository.deleteById(id);
    }
}
