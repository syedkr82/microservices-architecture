package com.example.inventoryservice.controller;

import com.example.inventoryservice.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productCode}/is-in-stock")
    public ResponseEntity<Boolean> isInStock(@PathVariable String productCode) {
        boolean stockAvailable = inventoryService.isInStock(productCode);
        return ResponseEntity.ok(stockAvailable);
    }

    @PostMapping("/{productCode}/reduce-stock")
    public ResponseEntity<Boolean> reduceStock(@PathVariable String productCode, @RequestParam int quantity) {
        boolean stockUpdated = inventoryService.reduceStock(productCode, quantity);
        return ResponseEntity.ok(stockUpdated);
    }
}
