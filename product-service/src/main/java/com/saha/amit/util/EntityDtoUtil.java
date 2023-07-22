package com.saha.amit.util;

import com.saha.amit.dto.ProductDTO;
import com.saha.amit.entity.Products;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static ProductDTO toDto(Products product){
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Products toEntity(ProductDTO productDto){
        Products product = new Products();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

}