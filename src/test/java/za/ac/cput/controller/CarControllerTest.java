package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.factory.CarFactory;
import za.ac.cput.factory.CarInformationFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/car";

    private static CarInformation carInformation;
    private static Car car;

    @BeforeAll
    public static void setup() {
        carInformation = CarInformationFactory.buildCarInformation("011", "BMW", "M4", "2017", "CA 123-456", "It is an M-performance", "800hps, twin turbo Injector","12");
        car = CarFactory.createCar("123", "456", carInformation, "100", "Available", "Active");
    }
    @Test
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<Car> postResponse = restTemplate.postForEntity(url, car, Car.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Car savedCar = postResponse.getBody();
        assertEquals(car.getCarID(), savedCar.getCarID());
        System.out.println("Saved car: " + savedCar);
    }
    @Test
    void read() {
        String url = BASE_URL + "/read/" + car.getCarID();
        ResponseEntity<Car> response = restTemplate.getForEntity(url, Car.class);
        assertEquals(car.getCarID(), response.getBody().getCarID());
        System.out.println("Read car: " + response.getBody());
    }
    @Test
    void update() {
        // Modify car data for update
        Car updatedCar = car;
        updatedCar.setRate("150");

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedCar);

        // Retrieve updated car and assert changes
        ResponseEntity<Car> response = restTemplate.getForEntity(BASE_URL + "/read/" + car.getCarID(), Car.class);
        assertEquals("150", response.getBody().getRate());
        System.out.println("Updated car: " + response.getBody());
    }
    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + car.getCarID();
        restTemplate.delete(url);

        // Ensure car is deleted
        ResponseEntity<Car> response = restTemplate.getForEntity(BASE_URL + "/read/" + car.getCarID(), Car.class);
        assertNull(response.getBody());
        System.out.println("Car deleted successfully.");
    }
    @Test
    void getAll() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("All cars: ");
        System.out.println(response.getBody());
    }
}