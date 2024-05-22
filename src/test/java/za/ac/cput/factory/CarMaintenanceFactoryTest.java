package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CarMaintenance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarMaintenanceFactoryTest {

    @Test
    public void testBuildCarMaintenance() {
        // Create a car maintenance record here
        CarMaintenance maintenance = CarMaintenanceFactory.createCarMaintenance("001", "123",
                "2023-05-22", "Oil change and tire rotation", "200");
        assertNotNull(maintenance);
        System.out.println(maintenance);
    }

    @Test
    public void testBuildCarMaintenanceWithFail() {
        // Create a car maintenance with a null parameter so test fails
        CarMaintenance maintenance = CarMaintenanceFactory.createCarMaintenance(null, null, null, null, null);
        assertNotNull(maintenance);
        System.out.println(maintenance);
    }
}

