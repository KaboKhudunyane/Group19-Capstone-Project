package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Car;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarFactoryTest {

    @Test
    public void testBuildCar(){
        // Create a car here
        Car car = CarFactory.createCar("123", "456",
                "Toyota Corolla", "150",
                "Available", "New");
        assertNotNull(car);
        System.out.println(car);
    }

    @Test
    public void testBuildCarWithFail(){
        // Create a car with a null parameter so test fails
        Car car = CarFactory.createCar(null, null, null, null, null, null);
        assertNotNull(car);
        System.out.println(car);
    }
}
