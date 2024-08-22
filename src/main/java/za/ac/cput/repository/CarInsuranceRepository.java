package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.CarInsurance;

public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {
}
