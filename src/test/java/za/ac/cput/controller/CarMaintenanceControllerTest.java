package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.CarMaintenance;
import za.ac.cput.factory.CarMaintenanceFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarMaintenanceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/car-maintenance";

    private static CarMaintenance carMaintenance;

    @BeforeAll
    public static void setup() {
        carMaintenance = CarMaintenanceFactory.createCarMaintenance("001", "123",
                "2023-05-22", "Oil change and tire rotation", "200");
    }

    @Test
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<CarMaintenance> postResponse = restTemplate.postForEntity(url, carMaintenance, CarMaintenance.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        CarMaintenance savedCarMaintenance = postResponse.getBody();
        assertEquals(carMaintenance.getMaintenanceID(), savedCarMaintenance.getMaintenanceID());
        System.out.println("Saved car maintenance: " + savedCarMaintenance);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + carMaintenance.getMaintenanceID();
        ResponseEntity<CarMaintenance> response = restTemplate.getForEntity(url, CarMaintenance.class);
        assertEquals(carMaintenance.getMaintenanceID(), response.getBody().getMaintenanceID());
        System.out.println("Read car maintenance: " + response.getBody());
    }

    @Test
    void update() {

        CarMaintenance updatedCarMaintenance = carMaintenance;
        updatedCarMaintenance.setCost("250");

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedCarMaintenance);


        ResponseEntity<CarMaintenance> response = restTemplate.getForEntity(BASE_URL + "/read/" + carMaintenance.getMaintenanceID(), CarMaintenance.class);
        assertEquals("250", response.getBody().getCost());
        System.out.println("Updated car maintenance: " + response.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + carMaintenance.getMaintenanceID();
        restTemplate.delete(url);


        ResponseEntity<CarMaintenance> response = restTemplate.getForEntity(BASE_URL + "/read/" + carMaintenance.getMaintenanceID(), CarMaintenance.class);
        assertNull(response.getBody());
        System.out.println("Car maintenance deleted successfully.");
    }

    @Test
    void getAll() {
        String url = BASE_URL + "/get-all";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("All car maintenance records: ");
        System.out.println(response.getBody());
    }
}

