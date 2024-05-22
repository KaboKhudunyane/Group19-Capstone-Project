package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CarMaintenance;
import za.ac.cput.factory.CarMaintenanceFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CarMaintenanceServiceTest {

    @Autowired
    private CarMaintenanceService carMaintenanceService;

    @Test
    void testCreate() {
        CarMaintenance maintenance = CarMaintenanceFactory.createCarMaintenance("001", "123",
                "2023-05-22", "Oil change and tire rotation", "200");
        CarMaintenance createdMaintenance = carMaintenanceService.create(maintenance);
        assertNotNull(createdMaintenance);
        System.out.println("Created Car Maintenance: " + createdMaintenance);
    }

    @Test
    void testRead() {
        CarMaintenance readMaintenance = carMaintenanceService.read("001");
        assertNotNull(readMaintenance);
        System.out.println("Read Car Maintenance: " + readMaintenance);
    }

    @Test
    void testUpdate() {
        CarMaintenance newMaintenance = new CarMaintenance.Builder()
                .copyCarMaintenance(CarMaintenanceFactory.createCarMaintenance("001", "123",
                        "2023-05-22", "Oil change and tire rotation", "200"))
                .setCost("250")
                .buildCarMaintenance();
        CarMaintenance updatedMaintenance = carMaintenanceService.update(newMaintenance);
        assertNotNull(updatedMaintenance);
        System.out.println("Updated Car Maintenance: " + updatedMaintenance);
    }

    @Test
    void testDelete() {
        carMaintenanceService.delete("001");
        System.out.println("Car Maintenance deleted successfully");
    }
}
