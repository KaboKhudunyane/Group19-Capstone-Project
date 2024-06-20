package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.factory.CarInformationFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarInformationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/carInformation";

    private static CarInformation carInformation;

    @BeforeAll
    public static void setup() {
        carInformation = CarInformationFactory.buildCarInformation("VIN123", "Toyota", "Corolla", "2021","CA 129",
                "Corolla","Standard");
    }

    @Test
    @Order(1)
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<CarInformation> postResponse = restTemplate.postForEntity(url, carInformation, CarInformation.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        CarInformation savedCarInformation = postResponse.getBody();
        assertEquals(carInformation.getCarInformationId(), savedCarInformation.getCarInformationId());
        System.out.println("Saved data: " + savedCarInformation);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + carInformation.getCarInformationId();
        System.out.println("URL: " + url);
        ResponseEntity<CarInformation> response = restTemplate.getForEntity(url, CarInformation.class);
        assertEquals(carInformation.getCarInformationId(), response.getBody().getCarInformationId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void getAll() {
        String url = BASE_URL + "/getAllCarInformation";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("View all: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}

