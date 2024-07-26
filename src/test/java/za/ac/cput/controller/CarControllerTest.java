package za.ac.cput.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarFactory;
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
class CarControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/car";
    // Example path to a car picture file
    private static final String CAR_PICTURE_PATH = "C:\\Users\\bokam\\OneDrive\\Desktop\\Example.jpeg";

    // Method to read file content as byte array
    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    byte[] carPicture = readFileAsBytes(CAR_PICTURE_PATH);
    CarInformation carInformation = CarInformationFactory.buildCarInformation("BMW", "M4", "2017", "CA 123-456", "It is an M-performance", "800hps, twin turbo Injector");
    CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance("Insurance Co.", "12345", "Comprehensive", "100000");
    Car car = CarFactory.buildCar(98348L, carInformation, carInsurance, "100", "Available", null); // Assuming carPicture is not required for creation
    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Car> postResponse = restTemplate.postForEntity(url, car, Car.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody());

        Car savedCar = postResponse.getBody();
        assertEquals(car.getCarInformation().getMake(), savedCar.getCarInformation().getMake());
        System.out.println("Saved data: " + savedCar);
    }
    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + car.getCarInformation().getMake();
        System.out.println("URL: " + url);
        ResponseEntity<Car> response = restTemplate.getForEntity(url, Car.class);
        assertEquals(car.getCarInformation().getMake(), response.getBody().getCarInformation().getMake());
        System.out.println("Read: " + response.getBody());
    }
    @Test
    @Order(3)
    void update() {
        // Modify car data for update
        Car updatedCar = new Car.Builder()
                .copyCar(car)
                .setRentalRate("150")
                .buildCar();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedCar);

        // Retrieve updated car and assert changes
        ResponseEntity<Car> response = restTemplate.getForEntity(BASE_URL + "/read/" + car.getCarInformation().getMake(), Car.class);
        assertNotNull(response.getBody());
        assertEquals("150", response.getBody().getRentalRate());
        System.out.println("Updated Car: " + response.getBody());
    }
    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" + car.getCarInformation().getMake();
        restTemplate.delete(url);

        // Ensure car is deleted
        ResponseEntity<Car> response = restTemplate.getForEntity(BASE_URL + "/read/" + car.getCarInformation().getMake(), Car.class);
        assertNull(response.getBody());
        System.out.println("Car deleted successfully.");
    }
    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        List<Car> carList = response.getBody();
        assertNotNull(carList);
        assertTrue(carList.size() >= 0); // Ensure it's greater than or equal to 0 depending on test data
        System.out.println("All Cars:");
        for (Car c : carList) {
            System.out.println(c);
        }
    }
}