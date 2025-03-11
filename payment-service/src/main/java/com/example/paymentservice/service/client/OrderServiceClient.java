package com.example.paymentservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ORDER-SERVICE") // 🔥 Use Eureka service name
public interface OrderServiceClient {
    @PatchMapping("/api/orders/{id}/status")
    void updateOrderStatus(@PathVariable Long id, @RequestParam String status);
}
