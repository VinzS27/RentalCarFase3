package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.UserProfileDTO;
import com.si2001.rentalcarspringboot.model.User;
import com.si2001.rentalcarspringboot.model.UserProfile;
import com.si2001.rentalcarspringboot.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileDTO getUserProfileById(int id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProfile not found"));

        return convertToDTO(userProfile);
    }

    public UserProfileDTO getUserProfileByType(String type) {
        UserProfile userProfile = (UserProfile) userProfileRepository.findByType(type)
                .orElseThrow(() -> new RuntimeException("UserProfile not found"));

        return convertToDTO(userProfile);
    }

    public List<UserProfileDTO> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();

        return userProfiles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserProfileDTO convertToDTO(UserProfile userProfile) {
        return new UserProfileDTO(userProfile.getId(), userProfile.getType());
    }
}
