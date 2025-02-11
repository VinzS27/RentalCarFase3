package com.si2001.rentalcarspringboot.repository;

import java.util.Optional;

import com.si2001.rentalcarspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

	Optional<User> findById(int id);

	Optional<User> findByUsernameAndPassword(String username, String password);

	Boolean existsByUsername(String username);
}
