package com.si2001.rentalcarspringboot.controller;

import java.util.List;

import com.si2001.rentalcarspringboot.DTO.CarDTO;
import com.si2001.rentalcarspringboot.DTO.ReservationDTO;
import com.si2001.rentalcarspringboot.DTO.UserDTO;
import com.si2001.rentalcarspringboot.model.Car;
import com.si2001.rentalcarspringboot.model.Reservation;
import com.si2001.rentalcarspringboot.model.User;
import com.si2001.rentalcarspringboot.repository.CarRepository;
import com.si2001.rentalcarspringboot.repository.ReservationRepository;
import com.si2001.rentalcarspringboot.repository.UserRepository;
import com.si2001.rentalcarspringboot.service.CarService;
import com.si2001.rentalcarspringboot.service.ReservationService;
import com.si2001.rentalcarspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class RentalCarController {

    final CarRepository carRepository;
    final ReservationRepository reservationRepository;
    final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CarService carService;
    private final ReservationService reservationService;

    @Autowired
    public RentalCarController(CarRepository carRepository, ReservationRepository reservationRepository, UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder, CarService carService, ReservationService reservationService) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.carService = carService;
        this.reservationService = reservationService;
    }

    //su postman mettere su barer il token eyJhbGciOiJI... generato in fase di login

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

    //fare dto dei metodi create,delete,update
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setUsername(user.getUsername());
                    u.setPassword(user.getPassword());
                    u.setEmail(user.getEmail());
                    u.setUserProfiles(user.getUserProfiles()); //non lo salva
                    return ResponseEntity.ok(userRepository.save(u));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //---------------CAR----------------------

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/car")
    public ResponseEntity<List<CarDTO>> getAllCar() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carRepository.save(car));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/car/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody Car carDetails) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setBrand(carDetails.getBrand());
                    car.setModel(carDetails.getModel());
                    car.setYear(carDetails.getYear());
                    car.setLicensePlate(carDetails.getLicensePlate());
                    car.setAvailability(carDetails.getAvailability());
                    return ResponseEntity.ok(carRepository.save(car));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //------------RESERVATION-----------------

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/reservation")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/reservation/{id}")
    public ResponseEntity<ReservationDTO> getReservationsByUserId(@PathVariable int id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/customer/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationRepository.save(reservation));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/reservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        return reservationRepository.findById(id)
                .map(existingReservation -> {
                    existingReservation.setCar(reservation.getCar());
                    existingReservation.setStartDate(reservation.getStartDate());
                    existingReservation.setEndDate(reservation.getEndDate());
                    existingReservation.setStatus(reservation.getStatus());
                    existingReservation.setCar(reservation.getCar());
                    existingReservation.setUser(reservation.getUser());
                    return ResponseEntity.ok(reservationRepository.save(existingReservation));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
