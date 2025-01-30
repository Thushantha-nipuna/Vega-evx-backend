package com.example.vega.login.repository;

import com.example.vega.login.entity.DealerInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerInventoryRepository extends JpaRepository<DealerInventory, Long> {
}
