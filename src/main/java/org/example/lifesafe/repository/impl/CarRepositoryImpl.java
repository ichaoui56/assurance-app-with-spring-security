package org.example.lifesafe.repository.impl;

import org.example.lifesafe.model.entity.Car;
import org.example.lifesafe.repository.ICarRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl  extends DefaultRepositoryImpl<Car> implements ICarRepository {

    public CarRepositoryImpl() {
        super(Car.class);
    }
}
