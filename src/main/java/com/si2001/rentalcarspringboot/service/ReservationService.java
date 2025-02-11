package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();
    ReservationDTO getReservationById(int id);
}
