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

    public CarDTO getCarById(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("car not found"));
        return convertToDTO(car);
    }

    public List<CarDTO> getAllAvailableCars() {
        List<Car> cars = carRepository.findAllByAvailabilityTrue();

        return cars.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CarDTO createCar(CarDTO carDTO) {
        Car car = dtoToCar(carDTO);
        Car savedCar = carRepository.save(car);
        return convertToDTO(savedCar);
    }

    public CarDTO updateCar(CarDTO carDTO, int id) {
        Car updateCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        updateCar.setBrand(carDTO.getBrand());
        updateCar.setModel(carDTO.getModel());
        updateCar.setYear(carDTO.getYear());
        updateCar.setAvailability(carDTO.getAvailability());
        updateCar.setLicensePlate(carDTO.getLicensePlate());

        carRepository.save(updateCar);
        return convertToDTO(updateCar);
    }

    public void deleteCar(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        carRepository.delete(car);
    }

    private Car dtoToCar(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setAvailability(carDTO.getAvailability());
        car.setLicensePlate(carDTO.getLicensePlate());

        return car;
    }

    private CarDTO convertToDTO(Car car) {
        return new CarDTO(car.getId(), car.getModel(), car.getBrand(),
                car.getYear(), car.getLicensePlate(), car.getAvailability());
    }
}
