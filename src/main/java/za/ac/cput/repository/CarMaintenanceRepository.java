package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CarMaintenance;

import java.util.List;

@Repository
public interface CarMaintenanceRepository extends JpaRepository<CarMaintenance, String> {
    CarMaintenance findByCarMaintenanceID(String carMaintenanceID);

    void deleteByCarMaintenanceID(String carMaintenanceID);

    List<CarMaintenance> getAllCarMaintainences();
}
