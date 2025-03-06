package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.CarDTO;
import com.si2001.rentalcarspringboot.DTO.ReservationDTO;
import com.si2001.rentalcarspringboot.DTO.UserDTO;
import com.si2001.rentalcarspringboot.DTO.UserProfileDTO;
import com.si2001.rentalcarspringboot.model.Car;
import com.si2001.rentalcarspringboot.model.Reservation;
import com.si2001.rentalcarspringboot.model.User;
import com.si2001.rentalcarspringboot.repository.CarRepository;
import com.si2001.rentalcarspringboot.repository.ReservationRepository;
import com.si2001.rentalcarspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  CarRepository carRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReservationDTO getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reservation not found"));
        return convertToDTO(reservation);
    }

    public List<ReservationDTO> getAllReservationsByUserId(int id) {
        List<Reservation> reservations = reservationRepository.findByUserId(id);

        return reservations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = dtoToReservation(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDTO(savedReservation);
    }

    public ReservationDTO updateReservation(ReservationDTO reservationDTO, int id) {
        Reservation updateReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        setReservationData(reservationDTO, updateReservation);
        updateReservation.setStatus("in approvazione");
        reservationRepository.save(updateReservation);

        return convertToDTO(updateReservation);
    }

    public ReservationDTO approveReservation(int id) {
        Reservation approveReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        approveReservation.setStatus("approvato");
        reservationRepository.save(approveReservation);

        changeCarStatus(approveReservation.getCar(), false);

        return convertToDTO(approveReservation);
    }

    public ReservationDTO declineReservation(int id) {
        Reservation declineReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        declineReservation.setStatus("non approvato");
        reservationRepository.save(declineReservation);

        changeCarStatus(declineReservation.getCar(), true);

        return convertToDTO(declineReservation);
    }

    public void deleteReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservationRepository.delete(reservation);
    }

    private void setReservationData(ReservationDTO reservationDTO, Reservation updateReservation) {
        updateReservation.setStartDate(reservationDTO.getStartDate());
        updateReservation.setEndDate(reservationDTO.getEndDate());
        updateReservation.setStatus(reservationDTO.getStatus());

        User user = userRepository.findById(reservationDTO.getUserDTO().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateReservation.setUser(user);

        Car car = carRepository.findById(reservationDTO.getCarDTO().getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        updateReservation.setCar(car);
    }

    private void changeCarStatus(Car updateReservation, boolean availability) {
        updateReservation.setAvailability(availability);
        carRepository.save(updateReservation);
    }

    private Reservation dtoToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        setReservationData(reservationDTO, reservation);

        return reservation;
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        UserDTO userDTO = new UserDTO(
                reservation.getUser().getId(),
                reservation.getUser().getUsername(),
                reservation.getUser().getPassword(),
                reservation.getUser().getEmail(),
                reservation.getUser().getUserProfiles().stream()
                        .map(profile -> new UserProfileDTO(profile.getId(), profile.getType()))
                        .collect(Collectors.toSet())
        );

        CarDTO carDTO = new CarDTO(reservation.getCar().getId(),
                reservation.getCar().getBrand(), reservation.getCar().getModel(),
                reservation.getCar().getYear(), reservation.getCar().getLicensePlate(),
                reservation.getCar().getAvailability());

        return new ReservationDTO(reservation.getId(),
                reservation.getStartDate(), reservation.getEndDate(), reservation.getStatus(), userDTO, carDTO);
    }

}
