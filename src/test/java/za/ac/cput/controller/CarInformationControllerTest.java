package za.ac.cput.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.CarInsuranceFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarInformationControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/car";
    private byte[] loadPicture(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }
    CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance(
            "MiWay", 15447841, "Insurance", 1200
    );
    CarInformation carInformation = CarInformationFactory.buildCarInformation(
            "Toyota", "Scarlet", "2020", "Manual", "Plate-123",
            "A stylish and comfortable SUV.", "Leather seats, Navigation system, Bluetooth", carInsurance,
            200, "Available",
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet1.jpg"), // Load the first picture
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet2.jpg"), // Load the second picture
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet3.jpg")  // Load the third picture
    );

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + carInformation.getCarInformationID();
        System.out.println("URL: " + url);
        ResponseEntity<CarInformation> response = restTemplate.getForEntity(url, CarInformation.class);
        assertEquals(carInformation.getCarInformationID(), response.getBody().getCarInformationID());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" + carInformation.getCarInformationID();
        restTemplate.delete(url);

        // Ensure car is deleted
        ResponseEntity<CarInformation> response = restTemplate.getForEntity(BASE_URL + "/read/" + carInformation.getCarInformationID(), CarInformation.class);
        assertNull(response.getBody());
        System.out.println("Car deleted successfully.");
    }
    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        List<CarInformation> carList = response.getBody();
        assertNotNull(carList);
        assertTrue(carList.size() >= 0); // Ensure it's greater than or equal to 0 depending on test data
        System.out.println("All Cars:");
        for (CarInformation c : carList) {
            System.out.println(c);
        }
    }
}