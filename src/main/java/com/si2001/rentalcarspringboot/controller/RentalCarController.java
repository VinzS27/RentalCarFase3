package com.si2001.rentalcarspringboot.controller;

import java.util.List;

import com.si2001.rentalcarspringboot.DTO.CarDTO;
import com.si2001.rentalcarspringboot.DTO.ReservationDTO;
import com.si2001.rentalcarspringboot.DTO.UserDTO;
import com.si2001.rentalcarspringboot.service.CarService;
import com.si2001.rentalcarspringboot.service.ReservationService;
import com.si2001.rentalcarspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class RentalCarController {

    private final UserService userService;
    private final CarService carService;
    private final ReservationService reservationService;

    @Autowired
    public RentalCarController(UserService userService, CarService carService,
                               ReservationService reservationService) {
        this.userService = userService;
        this.carService = carService;
        this.reservationService = reservationService;
    }

    //su postman mettere su barer il token ey... generato in fase di login

    //---------------USER---------------------

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/user")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/user/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(user, id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //---------------CAR----------------------

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/car")
    public ResponseEntity<List<CarDTO>> getAllCar() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/car")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.createCar(car));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/car/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable int id, @RequestBody CarDTO car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.updateCar(car, id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    //------------RESERVATION-----------------

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/reservation")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/reservation/{id}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsByUserId(@PathVariable int id) {
        return ResponseEntity.ok(reservationService.getAllReservationById(id));
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/customer/reservation")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservation));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/reservation/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable int id, @RequestBody ReservationDTO reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(reservation, id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();

    }

}
