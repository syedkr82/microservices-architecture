package com.example.inventoryservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service", url = "http://localhost:8081/api/orders")
public interface OrderServiceClient {
    @GetMapping("/validate-stock")
    boolean validateStock(@RequestParam String productCode, @RequestParam int quantity);
}
