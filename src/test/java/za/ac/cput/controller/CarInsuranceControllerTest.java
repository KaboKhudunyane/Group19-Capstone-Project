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
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarInsuranceFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarInsuranceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL ="mysql://${MYSQL_HOST:localhost}:3306/CarShare";

    private static CarInsurance carInsurance;

    @BeforeAll
    public static void setup(){
        carInsurance = CarInsuranceFactory.buildCarInsurance("PM305", "Outsurance", "MT55", "PN354");
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity <CarInsurance> postResponse = restTemplate.postForEntity(url,carInsurance, CarInsurance.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        CarInsurance  carInsuranceSaved = postResponse.getBody();
        assertEquals(carInsurance.getInsuranceID(), carInsuranceSaved.getInsuranceID());
        System.out.println("Saved data:" + carInsuranceSaved);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + carInsurance.getInsuranceID();
        System.out.println("URL:" + url);
        ResponseEntity<CarInsurance> response = restTemplate.getForEntity(url, CarInsurance.class);
        assertEquals(carInsurance.getInsuranceID(), response.getBody().getInsuranceID());
        System.out.println("read" + response.getBody());
    }

    @Test
    void c_update() {
        String url = BASE_URL + "/update";
        CarInsurance newCarInsurance = new CarInsurance.Builder().copy(carInsurance)
                .setInsuranceName("Auto & General").build();
        ResponseEntity<CarInsurance> postResponse = restTemplate.postForEntity(url, newCarInsurance,CarInsurance.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        CarInsurance carInsuranceUpdated = postResponse.getBody();
        assertEquals(newCarInsurance.getInsuranceID(), carInsuranceUpdated.getInsuranceID());
        System.out.println("data Updated:" + carInsuranceUpdated);
    }

    @Test
    void d_delete() {
        String url = BASE_URL + "/delete/" + carInsurance.getInsuranceID();
        System.out.println("URL:" + url);
        restTemplate.delete(url);
        System.out.println("CarInsurance Deleted");

    }

    @Test
    void e_getAllCarInsurance() {
        String url =BASE_URL +"/getAllCarInsurance";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println("Show All:");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
