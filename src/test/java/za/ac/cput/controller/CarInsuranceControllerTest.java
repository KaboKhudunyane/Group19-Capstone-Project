package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarInsuranceFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarInsuranceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:3306/carinsurance";

    private static CarInsurance carInsurance;

    @BeforeAll
    public static void setup() {
        carInsurance = CarInsuranceFactory.buildCarInsurance("Outsurance", "POL12345", "Comprehensive", "100000");
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<CarInsurance> postResponse = restTemplate.postForEntity(url, carInsurance, CarInsurance.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        CarInsurance carInsuranceSaved = postResponse.getBody();
        assertEquals(carInsurance.getPolicyNumber(), carInsuranceSaved.getPolicyNumber());
        System.out.println("Saved data: " + carInsuranceSaved);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + carInsurance.getPolicyNumber();
        System.out.println("URL: " + url);
        ResponseEntity<CarInsurance> response = restTemplate.getForEntity(url, CarInsurance.class);
        assertNotNull(response.getBody());
        assertEquals(carInsurance.getPolicyNumber(), response.getBody().getPolicyNumber());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        String url = BASE_URL + "/update";
        CarInsurance updatedCarInsurance = new CarInsurance.Builder()
                .copyCarInsurance(carInsurance)
                .setCoverageAmount("150000")
                .buildCarInsurance();
        ResponseEntity<CarInsurance> postResponse = restTemplate.postForEntity(url, updatedCarInsurance, CarInsurance.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        CarInsurance carInsuranceUpdated = postResponse.getBody();
        assertEquals(updatedCarInsurance.getPolicyNumber(), carInsuranceUpdated.getPolicyNumber());
        System.out.println("Data Updated: " + carInsuranceUpdated);
    }

    @Test
    void d_delete() {
        String url = BASE_URL + "/delete/" + carInsurance.getPolicyNumber();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("CarInsurance Deleted");
    }

    @Test
    void e_getAllCarInsurance() {
        String url = BASE_URL + "/getAllCarInsurance";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        System.out.println("Show All:");
        System.out.println(response.getBody());
    }
}
