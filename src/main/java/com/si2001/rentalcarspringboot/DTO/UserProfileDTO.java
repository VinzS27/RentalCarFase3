package com.si2001.rentalcarspringboot.DTO;

import java.util.Objects;

public class UserProfileDTO {
    private int id;
    private String type;

    public UserProfileDTO(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileDTO that = (UserProfileDTO) o;
        return id == that.id && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
