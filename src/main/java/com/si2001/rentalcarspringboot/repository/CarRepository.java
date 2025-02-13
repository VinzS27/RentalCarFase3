package com.si2001.rentalcarspringboot.repository;

import com.si2001.rentalcarspringboot.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
