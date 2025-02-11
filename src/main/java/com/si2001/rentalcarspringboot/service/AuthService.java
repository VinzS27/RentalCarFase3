package com.si2001.rentalcarspringboot.service;

import com.si2001.rentalcarspringboot.payload.request.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}