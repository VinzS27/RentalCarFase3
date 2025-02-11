package com.si2001.rentalcarspringboot.repository;

import java.util.List;

import com.si2001.rentalcarspringboot.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	@Query("SELECT r FROM Reservation r WHERE r.user = (select u from User u where u.id = ?1)")
	List<Reservation> findAllByUserID(Integer id);
}
