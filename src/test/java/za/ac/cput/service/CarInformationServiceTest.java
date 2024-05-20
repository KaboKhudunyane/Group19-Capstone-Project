package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.factory.CarInformationFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarInformationServiceTest {

    @Autowired
    private CarInformationService carInformationService;

    private static CarInformation carInformation1;
    private static CarInformation carInformation2;

    @BeforeEach
    void setUp() {
        carInformation1 = CarInformationFactory.buildCarInformation("011", "BMW", "M4",
                "2017", "CA123-143", "M performance", "Twin turbo", "CAR01");
        assertNotNull(carInformation1);
        carInformation2 = CarInformationFactory.buildCarInformation("012", "Toyota", "Corolla",
                "2012", "CA143-764", "Standard", "Single turbo", "CAR02");
        assertNotNull(carInformation2);
    }

    @Test
    @Order(1)
    void save() {
        CarInformation created1 = carInformationService.save(carInformation1);
        assertNotNull(created1);
        System.out.println("Saved CarInformation 1: " + created1);
        CarInformation created2 = carInformationService.save(carInformation2);
        assertNotNull(created2);
        System.out.println("Saved CarInformation 2: " + created2);
    }

    @Test
    @Order(2)
    void read() {
        CarInformation read = carInformationService.read(carInformation2.getCarInformationId());
        assertNotNull(read);
        System.out.println("Read CarInformation: " + read);
    }

    @Test
    @Order(3)
    void delete() {
        boolean deleted = carInformationService.delete(carInformation1.getCarInformationId());
        assertTrue(deleted);
        System.out.println("Deleted CarInformation 1");
    }

    @Test
    @Order(4)
    void getAll() {
        List<CarInformation> allCarInformation = carInformationService.getAll();
        assertNotNull(allCarInformation);
        assertTrue(allCarInformation.size() > 0);
        System.out.println("All CarInformation: " + allCarInformation);
    }

}

