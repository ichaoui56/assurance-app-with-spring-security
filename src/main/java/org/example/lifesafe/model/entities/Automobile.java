package org.example.lifesafe.model.entities;

import jakarta.persistence.*;
import org.example.lifesafe.model.enums.CarUse;
import org.example.lifesafe.model.enums.InsuranceType;

import java.time.LocalDate;

@Entity
@Table(name = "automobile_insurances")
public class Automobile extends Insurance {

    @Column(name="driver_age", nullable = false)
    private int driverAge;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(name="car_use",nullable = false)
    private CarUse carUse;

    @Column(name="driving_history",nullable = false)
    private String drivingHistory;

    @Column(name = "last_accident_date")
    private LocalDate lastAccidentDate;

    public Automobile(){}

    public Automobile(double quoteAmount, InsuranceType type, int driverAge, CarUse carUse, String drivingHistory, Car car, LocalDate lastAccidentDate, User user) {
        super(500, type, user);
        this.driverAge = driverAge;
        this.carUse = carUse;
        this.drivingHistory = drivingHistory;
        this.car = car;
        this.lastAccidentDate = lastAccidentDate;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarUse getCarUse() {
        return carUse;
    }

    public void setCarUse(CarUse carUse) {
        this.carUse = carUse;
    }

    public String getDrivingHistory() {
        return drivingHistory;
    }

    public void setDrivingHistory(String drivingHistory) {
        this.drivingHistory = drivingHistory;
    }

    public LocalDate getLastAccidentDate() {
        return lastAccidentDate;
    }

    public void setLastAccidentDate(LocalDate lastAccidentDate) {
        this.lastAccidentDate = lastAccidentDate;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "driverAge=" + driverAge +
                ", car=" + car +
                ", carUse='" + carUse + '\'' +
                ", drivingHistory='" + drivingHistory + '\'' +
                ", lastAccidentDate=" + lastAccidentDate +
                '}';
    }
}