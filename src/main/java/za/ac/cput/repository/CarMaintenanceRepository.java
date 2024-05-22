package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CarMaintenance;
import java.util.List;

@Repository
public interface CarMaintenanceRepository extends JpaRepository<CarMaintenance, String> {
    List<CarMaintenance> findByMaintenanceID(String maintenanceID);
    void deleteByMaintenanceID(String maintenanceID);
    List<CarMaintenance> findAll();
}
