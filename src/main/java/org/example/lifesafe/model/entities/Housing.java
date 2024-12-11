package org.example.lifesafe.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.example.lifesafe.model.enums.InsuranceType;

@Entity
@Table(name = "housing_insurances")

public class Housing extends Insurance {

    @Column(name = "home_value", nullable = false)
    private double homeValue;

    @Column(name = "home_type", nullable = false)
    private String homeType;

    @Column(nullable = false)
    private String location;

    @Column(name = "security_system", nullable = false)
    private String securitySystem;

    public Housing() {
    }

    public Housing(double quoteAmount, InsuranceType type, double homeValue, String homeType, String location, String security, User user) {
        super(300, type, user);
        this.homeValue = homeValue;
        this.homeType = homeType;
        this.location = location;
        this.securitySystem = security;
    }

    public double getHomeValue() {
        return homeValue;
    }

    public void setHomeValue(double value) {
        this.homeValue = value;
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSecuritySystem() {
        return securitySystem;
    }

    public void setSecuritySystem(String securitySystem) {
        this.securitySystem = securitySystem;
    }
}