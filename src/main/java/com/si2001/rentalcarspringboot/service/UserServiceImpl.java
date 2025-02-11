package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.UserDTO;
import com.si2001.rentalcarspringboot.model.User;
import com.si2001.rentalcarspringboot.model.UserProfile;
import com.si2001.rentalcarspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return convertToDTO(user);
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        Set<String> roles = user.getUserProfiles().stream()
                .map(UserProfile::getType)
                .collect(Collectors.toSet());

        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), roles);
    }
}

