package com.example.vega.login.repository;

import com.example.vega.login.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    // Custom queries (if needed) can be added here
}