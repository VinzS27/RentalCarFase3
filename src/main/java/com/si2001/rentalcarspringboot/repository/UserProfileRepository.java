package com.si2001.rentalcarspringboot.repository;

import com.si2001.rentalcarspringboot.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    Optional<Object> findByType(String type);
}