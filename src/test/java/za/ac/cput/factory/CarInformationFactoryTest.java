package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;

import static org.junit.jupiter.api.Assertions.*;

class CarInformationFactoryTest {

    @Test
    void buildCarInformation() {
        CarInformation carInformation = CarInformationFactory.buildCarInformation("BMW", "M4",
                "2018", "CA123-456", "M performance", "800hps");
        assertNotNull(carInformation);
        System.out.println(carInformation);
    }

    @Test
    void testBuildCarInformationWithFail() {
        CarInformation carInformation = CarInformationFactory.buildCarInformation( "BMW", "M4",
                "2018", "CA123-456", "", "");;
        assertNotNull(carInformation);
        System.out.println(carInformation);
    }
}

