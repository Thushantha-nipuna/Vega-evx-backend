package com.example.vega.login.service;

import com.example.vega.login.entity.CarEntity;
import com.example.vega.login.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Add Car with Image Upload
    public CarEntity addCar(String name, String features, Double price, MultipartFile image) throws IOException {
        // Create upload directory if it doesn’t exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate unique filename
        String originalFileName = image.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        // Save image to directory
        Path filePath = uploadPath.resolve(newFileName);
        Files.copy(image.getInputStream(), filePath);

        // Create and save CarEntity
        CarEntity car = new CarEntity();
        car.setName(name);
        car.setFeatures(features);
        car.setPrice(price);
        car.setImageName(newFileName);

        return carRepository.save(car);
    }

    // Get all cars
    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    // Get car by ID
    public CarEntity getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    // Delete car (also deletes image file)
    public void deleteCar(Long id) {
        CarEntity car = getCarById(id);

        try {
            Path imagePath = Paths.get(uploadDir).resolve(car.getImageName());
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            throw new RuntimeException("Error deleting image file", e);
        }

        carRepository.deleteById(id);
    }
}
