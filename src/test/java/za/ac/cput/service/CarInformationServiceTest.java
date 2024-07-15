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
    private static CarInformation carInformation1  = CarInformationFactory.buildCarInformation("vw","golf 8","2024", "343 434 GP", "M4",
            "Akrapovic Exhaust");


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
                .setYear("2002")
                .setMake("VW")
                .buildCarInformation();
        CarInformation updatedCarInformation = carInformationService.update(newCarInformation);
        assertNotNull(updatedCarInformation);
        System.out.println("Updated Car: " + updatedCarInformation);
    }
    @Test
    @Order(2)
    void read() {
        CarInformation read = carInformationService.read(carInformation1.getCarInformationId().toString());
        assertNotNull(read);
        System.out.println("Read CarInformation: " + read);
    }

    @Test
    @Order(3)
    void delete() {
            carInformationService.delete(carInformation1.getCarInformationId().toString());
            System.out.println("Car deleted successfully");
        }
    @Test
    @Order(4)
    void getAllCarInformation() {
        List<CarInformation> allCarInformation = carInformationService.findAllCarInformation();
        assertNotNull(allCarInformation);
        assertTrue(allCarInformation.size() > 0);
        System.out.println("All CarInformation: " + allCarInformation);
    }
}