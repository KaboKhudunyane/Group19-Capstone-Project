package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;

import za.ac.cput.domain.CarInformation;
import za.ac.cput.factory.CarInformationFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarInformationControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/carInformation";
    CarInformation carInformation = CarInformationFactory.buildCarInformation("Toyota", "Corolla", "2021", "CA 129",
            "Corolla", "Standard");

    @Test
    @Order(1)
    void save() {
        String url = BASE_URL + "/create";
        ResponseEntity<CarInformation> postResponse = restTemplate.postForEntity(url, carInformation, CarInformation.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody());

        CarInformation savedCarInformation = postResponse.getBody();
        assertEquals(carInformation.getMake(), savedCarInformation.getMake());
        System.out.println("Saved data: " + savedCarInformation);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + carInformation.getMake();
        System.out.println("URL: " + url);
        ResponseEntity<CarInformation> response = restTemplate.getForEntity(url, CarInformation.class);
        assertEquals(carInformation.getMake(), response.getBody().getMake());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        // Modify carInformation data for update
        CarInformation updatedCarInformation = new CarInformation.Builder()
                .copyCarInformation(carInformation)
                .setModel("Updated Model")
                .buildCarInformation();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedCarInformation);

        // Retrieve updated carInformation and assert changes
        ResponseEntity<CarInformation> response = restTemplate.getForEntity(BASE_URL + "/read/" + carInformation.getMake(), CarInformation.class);
        assertNotNull(response.getBody());
        assertEquals("Updated Model", response.getBody().getModel());
        System.out.println("Updated CarInformation: " + response.getBody());
    }

    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" + carInformation.getMake();
        restTemplate.delete(url);

        // Ensure carInformation is deleted
        ResponseEntity<CarInformation> response = restTemplate.getForEntity(BASE_URL + "/read/" + carInformation.getMake(), CarInformation.class);
        assertNull(response.getBody());
        System.out.println("CarInformation deleted successfully.");
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        List<CarInformation> carInformationList = response.getBody();
        assertNotNull(carInformationList);
        assertTrue(carInformationList.size() >= 0); // Ensure it's greater than or equal to 0 depending on test data
        System.out.println("All CarInformation:");
        for (CarInformation ci : carInformationList) {
            System.out.println(ci);
        }
    }
}
