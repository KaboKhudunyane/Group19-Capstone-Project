package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;

import static org.junit.jupiter.api.Assertions.*;

class CarInformationFactoryTest {

    @Test
    void buildCarInformation() {
        CarInformation carInformation = null;
        Car carId = CarFactory.createCar("00", "90", carInformation, "2", "false", "not available");
        carInformation = CarInformationFactory.buildCarInformation("11", "BMW", "M4",
                "2018", "CA123-456", "M performance", "800hps", carId);
        assertNotNull(carInformation);
        System.out.println(carInformation);
    }

    @Test
    void testBuildCarInformationWithFail() {
        CarInformation carInformation = null;
        Car carId = CarFactory.createCar("00", "90", carInformation, "2", "false", "not available");
        carInformation = CarInformationFactory.buildCarInformation("11", "BMW", "M4",
                "2018", "CA123-456", "", "", carId);
        assertNotNull(carInformation);
    }
}

