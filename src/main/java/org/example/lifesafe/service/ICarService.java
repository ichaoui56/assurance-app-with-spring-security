package org.example.lifesafe.service;

import org.example.lifesafe.model.entity.Car;

import java.util.List;
import java.util.Optional;

public interface ICarService {
    Optional<Car> findById(int id);

    List<Car> findAllCars();
}
