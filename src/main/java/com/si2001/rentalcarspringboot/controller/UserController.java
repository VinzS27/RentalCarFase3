package com.si2001.rentalcarspringboot.controller;

import com.si2001.rentalcarspringboot.DTO.UserDTO;
import com.si2001.rentalcarspringboot.DTO.UserProfileDTO;
import com.si2001.rentalcarspringboot.service.UserProfileService;
import com.si2001.rentalcarspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class UserController {

    private final UserService userService;
    private final UserProfileService userProfileService;

    @Autowired
    public UserController(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/admin/user")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/any/user/username/{username}")
    public ResponseEntity<UserDTO> getUsersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/admin/user/roles")
    public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles() {
        return ResponseEntity.ok(userProfileService.getAllUserProfiles());
    }

    @PostMapping("/admin/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/admin/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(user, id));
    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
