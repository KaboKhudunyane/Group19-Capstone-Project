package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.Promotion;

import java.util.List;

@Repository
public interface CarInsuranceRepository extends JpaRepository<CarInsurance, String> {
    CarInsurance findByCarInsuranceID(String carInsuranceID);

    void deleteByCarInsuranceID(String carInsuranceID);

    List<CarInsurance> getAllCarInsurances();
}

