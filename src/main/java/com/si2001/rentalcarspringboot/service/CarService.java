package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();
    CarDTO getCarById(int id);
    List<CarDTO> getAllAvailableCars();
    CarDTO createCar(CarDTO carDTO);
    CarDTO updateCar(CarDTO carDTO, int id);
    void deleteCar(int id);
}
