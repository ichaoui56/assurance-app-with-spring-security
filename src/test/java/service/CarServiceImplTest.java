package org.example.lifesafe.service.impl;

import org.example.lifesafe.model.entities.Car;
import org.example.lifesafe.repository.ICarRepository;
import org.example.lifesafe.service.ICarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarServiceImplTest {

    @Mock
    private ICarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        car = new Car();
        car.setId(1);
        car.setModel("Toyota");
        car.setBrand("Corolla");
    }

    @Test
    public void testFindById_ReturnsCar_WhenCarExists() {
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        Optional<Car> result = carService.findById(1);

        assertTrue(result.isPresent(), "Car should be present");
        assertEquals(car, result.get(), "Returned car should match the mock car");
    }

    @Test
    public void testFindById_ReturnsEmpty_WhenCarDoesNotExist() {
        when(carRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Car> result = carService.findById(99);

        assertFalse(result.isPresent(), "Car should not be present for non-existing ID");
    }

    @Test
    public void testFindAllCars_ReturnsCarList_WhenCarsExist() {
        when(carRepository.findAll()).thenReturn(List.of(car));

        List<Car> result = carService.findAllCars();

        assertNotNull(result, "Car list should not be null");
        assertEquals(1, result.size(), "There should be one car in the list");
        assertEquals(car, result.get(0), "The first car in the list should match the mock car");
    }

    @Test
    public void testFindAllCars_ReturnsEmptyList_WhenNoCarsExist() {
        when(carRepository.findAll()).thenReturn(List.of());

        List<Car> result = carService.findAllCars();

        assertNotNull(result, "Car list should not be null");
        assertTrue(result.isEmpty(), "Car list should be empty if no cars are found");
    }

}
