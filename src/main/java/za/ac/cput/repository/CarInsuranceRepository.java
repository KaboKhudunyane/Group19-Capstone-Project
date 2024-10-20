package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.CarInsurance;

import java.util.List;

public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {

    List<CarInsurance> findCarInsuranceByUserUserID(Long userID);


}
