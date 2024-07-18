package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Car;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    Car findByCarID(Long carID);
    void deleteByCarID(Long carID);
    List<Car> getAll();
}