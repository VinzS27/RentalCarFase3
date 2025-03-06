package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();
    ReservationDTO getReservationById(int id);
    List<ReservationDTO> getAllReservationsByUserId(int id);
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO updateReservation(ReservationDTO reservationDTO, int id);
    void deleteReservation(int id);
    ReservationDTO approveReservation(int id);
    ReservationDTO declineReservation(int id);
}
