package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.ReservationDTO;
import com.si2001.rentalcarspringboot.model.Reservation;
import com.si2001.rentalcarspringboot.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReservationDTO getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        return convertToDTO(reservation);
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getId(), reservation.getStartDate(), reservation.getEndDate(),
                reservation.getStatus(), reservation.getUser(), reservation.getCar());
    }
}
