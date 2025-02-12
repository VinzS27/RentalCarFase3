package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.UserDTO;
import com.si2001.rentalcarspringboot.DTO.UserProfileDTO;
import com.si2001.rentalcarspringboot.model.User;
import com.si2001.rentalcarspringboot.model.UserProfile;
import com.si2001.rentalcarspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO updateUser(UserDTO userDTO, int id) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        updateUser.setUsername(userDTO.getUsername());
        updateUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setUserProfiles(userDTO.getUserProfiles());

        userRepository.save(updateUser);

        return convertToDTO(updateUser);
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    private User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setUserProfiles(userDTO.getUserProfiles());

        return user;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getUserProfiles());
    }
}

