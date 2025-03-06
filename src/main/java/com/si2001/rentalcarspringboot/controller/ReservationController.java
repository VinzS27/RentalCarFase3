package com.si2001.rentalcarspringboot.controller;

import com.si2001.rentalcarspringboot.DTO.ReservationDTO;
import com.si2001.rentalcarspringboot.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
    @GetMapping("/admin/reservation")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/any/reservation/{id}")
    public ResponseEntity<ReservationDTO> getReservationsById(@PathVariable int id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/any/reservation/user/{id}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsByUserId(@PathVariable int id) {
        return ResponseEntity.ok(reservationService.getAllReservationsByUserId(id));
    }

    @PostMapping("/customer/reservation")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservation));
    }

    @PutMapping("/customer/reservation/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable int id, @RequestBody ReservationDTO reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(reservation, id));
    }

    @PutMapping("/admin/reservation/approve/{id}")
    public ResponseEntity<ReservationDTO> approveReservation(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.approveReservation(id));
    }

    @PutMapping("/admin/reservation/decline/{id}")
    public ResponseEntity<ReservationDTO> declineReservation(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.declineReservation(id));
    }
   
    @DeleteMapping("/customer/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();

    }
}
