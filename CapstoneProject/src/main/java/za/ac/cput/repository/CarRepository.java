package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Car;
import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    List<Car> findCar(String carID);
    void deleteCar(String carID);
    List <Car> getAllCars();
}
