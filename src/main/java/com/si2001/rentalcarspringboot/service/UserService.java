package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(int id);

    UserDTO getUserByUsername(String username);
}
