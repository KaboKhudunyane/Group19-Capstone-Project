package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Car;
import za.ac.cput.repository.CarRepository;

import java.util.List;
@Service
public class CarService implements IService<Car, String>{
    private CarRepository carRepository;
    @Autowired
    ContactService(CarRepository carRepository){
        this.carRepository = carRepository;
    }
    @Override
    public Car create(Car car) {
        return carRepository.save(car);
    }
    @Override
    public Booking read(String carID) {
        return carRepository.findCar(carID);
    }
    @Override
    public Car update(Car car) {
        return carRepository.save(car);
    }
    @Override
    void delete(String carID) {
        carRepository.deleteCar(carID);
    }
    @Override
    public List<Car> getAllCars( ){
        return carRepository.getAllCars();
    }
}
