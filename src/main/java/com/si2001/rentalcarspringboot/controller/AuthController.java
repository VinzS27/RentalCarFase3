package com.si2001.rentalcarspringboot.controller;

import com.si2001.rentalcarspringboot.DTO.UserDTO;
import com.si2001.rentalcarspringboot.payload.request.LoginRequest;
import com.si2001.rentalcarspringboot.payload.response.JwtAuthResponse;
import com.si2001.rentalcarspringboot.repository.UserRepository;

import com.si2001.rentalcarspringboot.service.AuthService;
import com.si2001.rentalcarspringboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final PasswordEncoder encoder;
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder, AuthService authService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest);
            UserDTO userDTO = userService.getUserByUsername(loginRequest.getUsername());

            return ResponseEntity.ok(new JwtAuthResponse(true, token, userDTO));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JwtAuthResponse("User or password not correct", false, null));
        }
    }
}
