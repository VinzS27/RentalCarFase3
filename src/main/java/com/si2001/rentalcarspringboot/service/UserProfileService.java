package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.DTO.UserProfileDTO;

import java.util.List;

public interface UserProfileService {

    UserProfileDTO getUserProfileById(int id);

    UserProfileDTO getUserProfileByType(String type);

    List<UserProfileDTO> getAllUserProfiles();
}
