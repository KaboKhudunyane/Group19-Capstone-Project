package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarInformationFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarInformationServiceTest {

    @Autowired
    private CarInformationService carInformationService;

    @Autowired
    private CarInsuranceService carInsuranceService;

    private static Long carInformationID;
    private CarInsurance carInsurance;
    private CarInformation carInformation;

    @BeforeEach
    void setUp() {
        carInsurance = carInsuranceService.read(1L);
        carInformation = CarInformationFactory.buildCarInformation(
                "Toyota", "Scarlet", "2020", "Manual", "Plate-123",
                "A stylish and comfortable SUV.", "Leather seats, Navigation system, Bluetooth", carInsurance,
                200, "Available",
                loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet1.jpg"), // Load the first picture
                loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet2.jpg"), // Load the second picture
                loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet3.jpg")  // Load the third picture
        );
    }

    private byte[] loadPicture(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }

    @Test
    @Order(1)
    void create() {
        CarInformation created = carInformationService.create(carInformation);
        assertNotNull(created);
        carInformationID = created.getCarInformationID();
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        CarInformation read = carInformationService.read(carInformationID);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(carInformationService.getAll());
    }
}
