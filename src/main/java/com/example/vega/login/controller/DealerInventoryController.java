package com.example.vega.login.controller;

import com.example.vega.login.entity.DealerInventory;
import com.example.vega.login.service.DealerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dealer/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class DealerInventoryController {

    @Autowired
    private DealerInventoryService inventoryService;

    // Add a new vehicle to inventory
    @PostMapping
    public ResponseEntity<DealerInventory> addInventory(@RequestBody DealerInventory inventory) {
        inventory.setStatus(updateStockStatus(inventory.getStock()));
        return ResponseEntity.ok(inventoryService.addInventory(inventory));
    }

    // Get all inventory records
    @GetMapping
    public ResponseEntity<List<DealerInventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    // Get a single inventory item by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable Long id) {
        Optional<DealerInventory> inventory = inventoryService.getInventoryById(id);
        return inventory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update inventory (stock & status)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable Long id, @RequestBody DealerInventory inventoryDetails) {
        Optional<DealerInventory> inventoryOpt = inventoryService.getInventoryById(id);
        if (inventoryOpt.isPresent()) {
            DealerInventory inventory = inventoryOpt.get();
            inventory.setStock(inventoryDetails.getStock());
            inventory.setStatus(updateStockStatus(inventoryDetails.getStock()));
            inventoryService.addInventory(inventory); // Save updated record
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete inventory item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }

    // Helper method to determine stock status
    private String updateStockStatus(int stock) {
        if (stock == 0) return "Out of Stock";
        if (stock <= 5) return "Low Stock";
        return "Available";
    }
}
