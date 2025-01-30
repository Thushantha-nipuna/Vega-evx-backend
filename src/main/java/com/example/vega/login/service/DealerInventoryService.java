package com.example.vega.login.service;

import com.example.vega.login.entity.DealerInventory;
import com.example.vega.login.repository.DealerInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerInventoryService {
    @Autowired
    private DealerInventoryRepository inventoryRepository;

    public DealerInventory addInventory(DealerInventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<DealerInventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<DealerInventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
