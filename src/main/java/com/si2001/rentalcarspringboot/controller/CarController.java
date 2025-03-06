package com.si2001.rentalcarspringboot.controller;

import com.si2001.rentalcarspringboot.DTO.CarDTO;
import com.si2001.rentalcarspringboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/any/car")
    public ResponseEntity<List<CarDTO>> getAllCar() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/admin/car/{id}")
    public ResponseEntity<CarDTO> getCarsById(@PathVariable int id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping("/any/car/available")
    public ResponseEntity<List<CarDTO>> getAllAvailableCar() {
        return ResponseEntity.ok(carService.getAllAvailableCars());
    }

    @PostMapping("/admin/car")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.createCar(car));
    }

    @PutMapping("/admin/car/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable int id, @RequestBody CarDTO car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.updateCar(car, id));
    }

    @DeleteMapping("/admin/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}
