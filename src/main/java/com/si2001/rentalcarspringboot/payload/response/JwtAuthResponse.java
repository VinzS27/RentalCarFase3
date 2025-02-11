package com.si2001.rentalcarspringboot.payload.response;

import com.si2001.rentalcarspringboot.DTO.UserDTO;

public class JwtAuthResponse {
    private String message;
    private Boolean status;
    private String accessToken;
    private UserDTO userDTO;

    public JwtAuthResponse(Boolean status, String accessToken, UserDTO userDTO) {
        this.status = status;
        this.accessToken = accessToken;
        this.userDTO = userDTO;
    }

    public JwtAuthResponse(String message, Boolean status, UserDTO userDTO) {
        this.message = message;
        this.status = status;
        this.userDTO = userDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
