package com.si2001.rentalcarspringboot.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class ReservationDTO {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private UserDTO userDTO;
    private CarDTO carDTO;

    public ReservationDTO(int id, LocalDate startDate, LocalDate endDate, String status, UserDTO userDTO, CarDTO carDTO) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.userDTO = userDTO;
        this.carDTO = carDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDTO that = (ReservationDTO) o;
        return id == that.id && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(status, that.status) && Objects.equals(userDTO, that.userDTO) && Objects.equals(carDTO, that.carDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, status, userDTO, carDTO);
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", userDTO=" + userDTO +
                ", carDTO=" + carDTO +
                '}';
    }
}
