package org.example.lifesafe.repository.impl;

import org.example.lifesafe.model.entities.Car;
import org.example.lifesafe.model.entities.Insurance;
import org.example.lifesafe.repository.ICarRepository;
import org.example.lifesafe.repository.IInsuranceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl  extends DefaultRepositoryImpl<Car> implements ICarRepository {

    public CarRepositoryImpl() {
        super(Car.class);
    }
}
