package com.example.inventoryservice.service;

import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Check stock availability
    public boolean isInStock(String productCode) {
        Optional<Inventory> inventory = inventoryRepository.findByProductCode(productCode);
        return inventory.isPresent() && inventory.get().getQuantity() > 0;
    }

    // Reduce stock when order is placed
    @Transactional
    public boolean reduceStock(String productCode, int quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductCode(productCode);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            if (inventory.getQuantity() >= quantity) {
                inventory.setQuantity(inventory.getQuantity() - quantity);
                inventoryRepository.save(inventory);
                return true;
            }
        }
        return false;
    }
}
