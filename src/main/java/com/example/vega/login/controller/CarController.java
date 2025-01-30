package com.example.vega.login.controller;

import com.example.vega.login.entity.CarEntity;
import com.example.vega.login.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    @Autowired
    private CarService carService;

    // Add a new car
    @PostMapping("/cars")
    public ResponseEntity<CarEntity> addCar(
            @RequestParam("name") String name,
            @RequestParam("features") String features,
            @RequestParam("price") String price,
            @RequestParam("image") MultipartFile image
    ) {
        try {
            CarEntity savedCar = carService.addCar(name, features, Double.parseDouble(price), image);
            return ResponseEntity.ok(savedCar);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get all cars
    @GetMapping("/cars")
    public ResponseEntity<List<CarEntity>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    // Get a single car by ID
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    // Delete a car
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}