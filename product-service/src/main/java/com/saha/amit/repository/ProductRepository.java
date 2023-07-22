package com.saha.amit.repository;

import com.saha.amit.entity.Products;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Products, String> {
}
