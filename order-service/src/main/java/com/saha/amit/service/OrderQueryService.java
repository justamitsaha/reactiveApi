package com.saha.amit.service;

import com.saha.amit.dto.PurchaseOrderResponseDto;
import com.saha.amit.repository.PurchaseOrderRepository;
import com.saha.amit.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderQueryService {

    @Autowired
    private PurchaseOrderRepository orderRepository;

    public Flux<PurchaseOrderResponseDto> getProductsByUserId(int userId){
        return Flux.fromStream(() -> this.orderRepository.findByUserId(userId).stream()) // blocking
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
