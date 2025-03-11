package com.example.orderservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE") // 🔥 Use Eureka service name
public interface InventoryServiceClient {
    @GetMapping("/api/inventory/{productCode}/is-in-stock")
    boolean isInStock(@RequestParam String productCode);
}
