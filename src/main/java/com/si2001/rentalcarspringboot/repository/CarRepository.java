package com.si2001.rentalcarspringboot.repository;

import com.si2001.rentalcarspringboot.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByAvailabilityTrue();
}
