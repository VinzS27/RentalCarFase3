package com.si2001.rentalcarspringboot.DTO;

import java.util.Objects;

public class CarDTO {
    private int id;
    private String model;
    private String brand;
    private int year;
    private String licensePlate;
    private boolean availability;

    public CarDTO(int id, String model, String brand, int year, String licensePlate, boolean availability) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return id == carDTO.id && year == carDTO.year && availability == carDTO.availability && Objects.equals(model, carDTO.model) && Objects.equals(brand, carDTO.brand) && Objects.equals(licensePlate, carDTO.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand, year, licensePlate, availability);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", licensePlate='" + licensePlate + '\'' +
                ", availability=" + availability +
                '}';
    }
}
