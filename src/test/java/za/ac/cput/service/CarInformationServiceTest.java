package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.CarInformationFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarInformationServiceTest {

    @Autowired
    private CarInformationService carInformationService;
    private static CarInformation carInformation1  = CarInformationFactory.buildCarInformation("011", "BMW", "M4",
            "2017", "CA123-143", "M performance", "Twin turbo");


    @Test
    void create() {
        CarInformation createdCarInformation = carInformationService.create(carInformation1);
        assertNotNull(createdCarInformation);
        System.out.println("Created Car: " + createdCarInformation);
    }
    @Test
    @Order(2)
    void Update() {
        CarInformation newCarInformation = new CarInformation.Builder()
                .copyCarInformation(carInformation1)
                .setCarInformationId("22")
                .setMake("VW")
                .buildCarInformation();
        CarInformation updatedCarInformation = carInformationService.update(newCarInformation);
        assertNotNull(updatedCarInformation);
        System.out.println("Updated Car: " + updatedCarInformation);
    }
    @Test
    @Order(2)
    void read() {
        CarInformation read = carInformationService.read(carInformation1.getCarInformationId());
        assertNotNull(read);
        System.out.println("Read CarInformation: " + read);
    }

    @Test
    @Order(3)
    void delete() {
            carInformationService.delete(carInformation1.getCarInformationId());
            System.out.println("Car deleted successfully");
        }
    /*@Test
    @Order(4)
    void getAllCarInformation() {
        List<CarInformation> allCarInformation = carInformationService.getAllCarInformation();
        assertNotNull(allCarInformation);
        assertTrue(allCarInformation.size() > 0);
        System.out.println("All CarInformation: " + allCarInformation);
    }*/
}

