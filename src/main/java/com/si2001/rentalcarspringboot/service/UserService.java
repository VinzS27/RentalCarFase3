package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(int id);

    UserDTO getUserByUsername(String username);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, int id);

    void deleteUser(int id);
}
