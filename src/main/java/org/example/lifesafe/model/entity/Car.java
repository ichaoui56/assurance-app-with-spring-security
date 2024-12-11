package org.example.lifesafe.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(name = "vehicle_type", nullable = false)
    private String type;

    public Car() {}

    public Car(String model, String brand, String type) {
        this.model = model;
        this.brand = brand;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + type + ")";
    }
}