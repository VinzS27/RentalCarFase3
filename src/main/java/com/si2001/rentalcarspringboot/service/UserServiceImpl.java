package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.UserDTO;
import com.si2001.rentalcarspringboot.DTO.UserProfileDTO;
import com.si2001.rentalcarspringboot.model.User;
import com.si2001.rentalcarspringboot.model.UserProfile;
import com.si2001.rentalcarspringboot.repository.UserProfileRepository;
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
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertToDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        setUserData(userDTO, user);

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    private void setUserData(UserDTO userDTO, User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());

        // Converte i DTO di UserProfile in entità UserProfile
        Set<UserProfile> userProfiles = userDTO.getUserProfilesDTO().stream()
                .map(dto -> userProfileRepository.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("UserProfile not found")))
                .collect(Collectors.toSet());

        user.setUserProfiles(userProfiles);
    }

    private User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        setUserData(userDTO, user);

        return user;
    }

    private UserDTO convertToDTO(User user) {
        Set<UserProfileDTO> userProfileDTOs = user.getUserProfiles().stream()
                .map(profile -> new UserProfileDTO(profile.getId(), profile.getType()))
                .collect(Collectors.toSet());

        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(),
                user.getEmail(), userProfileDTOs);
    }
}

