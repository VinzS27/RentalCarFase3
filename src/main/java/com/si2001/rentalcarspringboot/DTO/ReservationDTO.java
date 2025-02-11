package com.si2001.rentalcarspringboot.DTO;

import com.si2001.rentalcarspringboot.model.Car;
import com.si2001.rentalcarspringboot.model.User;

import java.time.LocalDate;
import java.util.Objects;

public class ReservationDTO {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private User user;
    private Car car;

    public ReservationDTO(int id, LocalDate startDate, LocalDate endDate, String status, User user, Car car) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.user = user;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return id == that.id && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(status, that.status) && Objects.equals(user, that.user) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, status, user, car);
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
}
