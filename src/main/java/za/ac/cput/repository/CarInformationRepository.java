package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CarInformation;

import java.util.List;

@Repository
public interface CarInformationRepository extends JpaRepository<CarInformation, String> {

    CarInformation findByMake(String carInformationId);

    void deleteByCarInformationId(String carInformationId);

    //List<CarInformation> getAllCarInformation();

}
