package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Car;
import za.ac.cput.repository.CarRepository;

import java.util.List;
@Service
public class CarService implements IService<Car, Long> {
    private final CarRepository carRepository;
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Override
    public Car create(Car car) {
        return carRepository.save(car);
    }
    @Override
    public Car read(Long carID) {
        return carRepository.findByCarID(carID) ;
    }
    @Override
    public Car update(Car car) {
        return carRepository.save(car);
    }
    @Override
    public void delete(Long carID) {
        carRepository.deleteByCarID(carID);
    }
    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }
}