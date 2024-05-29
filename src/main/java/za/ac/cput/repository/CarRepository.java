package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    // Method to find a car by its ID
    Car findByCarID(String carID);

    // Method to delete a car by its ID
    void deleteByCarID(String carID);

    // Method to get all cars
    List<Car> getAllCars();
}
