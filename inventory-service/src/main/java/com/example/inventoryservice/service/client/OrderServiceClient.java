package com.example.inventoryservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ORDER-SERVICE") // 🔥 Use Eureka service name
public interface OrderServiceClient {
    @GetMapping("/api/orders/validate-stock")
    boolean validateStock(@RequestParam String productCode, @RequestParam int quantity);
}
