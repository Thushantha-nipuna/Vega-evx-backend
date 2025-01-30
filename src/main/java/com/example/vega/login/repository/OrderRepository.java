package com.example.vega.login.repository;

import com.example.vega.login.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    // Add custom query methods if needed
}
