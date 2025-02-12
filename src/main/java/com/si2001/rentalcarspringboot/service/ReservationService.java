package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();
    List<ReservationDTO> getAllReservationById(int id);
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO updateReservation(ReservationDTO reservationDTO, int id);
    void deleteReservation(int id);
}
