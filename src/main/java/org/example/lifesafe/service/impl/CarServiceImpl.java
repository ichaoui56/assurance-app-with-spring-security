package org.example.lifesafe.service.impl;

import org.example.lifesafe.model.entity.Car;
import org.example.lifesafe.repository.ICarRepository;
import org.example.lifesafe.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {
    private final ICarRepository carRepository;

    @Autowired
    public CarServiceImpl(ICarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findAllCars(){
        return carRepository.findAll();
    }
}
