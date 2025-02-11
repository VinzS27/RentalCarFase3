package com.si2001.rentalcarspringboot.DTO;

import java.util.Objects;
import java.util.Set;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private Set<String> userProfiles;

    public UserDTO(int id, String username, String password, String email, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userProfiles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<String> userProfiles) {
        this.userProfiles = userProfiles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password) && Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
