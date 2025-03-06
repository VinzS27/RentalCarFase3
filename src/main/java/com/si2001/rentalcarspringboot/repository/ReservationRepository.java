package com.si2001.rentalcarspringboot.repository;

import java.util.List;

import com.si2001.rentalcarspringboot.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	List<Reservation> findByUserId(int userId);
}
