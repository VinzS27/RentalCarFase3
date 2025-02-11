package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.CarDTO;
import com.si2001.rentalcarspringboot.model.Car;
import com.si2001.rentalcarspringboot.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();

        return cars.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CarDTO convertToDTO(Car car) {
        return new CarDTO(car.getId(),car.getModel(),car.getBrand(),
                car.getYear(),car.getLicensePlate(),car.getAvailability());
    }
}
