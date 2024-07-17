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
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/car";
    private static CarInformation carInformation;
    private static CarInsurance carInsurance;
    private static Car car;
    @BeforeAll
    public static void setup() {
        carInformation = CarInformationFactory.buildCarInformation("BMW", "M4", "2017", "CA 123-456", "It is an M-performance", "800hps, twin turbo Injector");
        carInsurance = CarInsuranceFactory.buildCarInsurance("Insurance Co.", "12345", "Comprehensive", "100000");
        car = CarFactory.buildCar(carInformation, carInsurance, "100", "Available", null); // Assuming carPicture is not required for creation
    }
    @Test
    @Order(1)
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<Car> postResponse = restTemplate.postForEntity(url, car, Car.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Car savedCar = postResponse.getBody();
        assertNotNull(savedCar.getCarId()); // Ensure the saved car has an ID assigned
        assertEquals(car.getCarInformation().getModel(), savedCar.getCarInformation().getModel());
        assertEquals(car.getCarInsurance().getInsuranceCompany(), savedCar.getCarInsurance().getInsuranceCompany());
        System.out.println("Saved car: " + savedCar);
    }
    @Test
    @Order(2)
    void read() {
        // Assuming save() test has run successfully and savedCar is populated with an ID
        String url = BASE_URL + "/read/" + car.getCarId();
        ResponseEntity<Car> response = restTemplate.getForEntity(url, Car.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car.getCarInformation().getModel(), response.getBody().getCarInformation().getModel());
        assertEquals(car.getCarInsurance().getInsuranceCompany(), response.getBody().getCarInsurance().getInsuranceCompany());
        System.out.println("Read car: " + response.getBody());
    }
    @Test
    @Order(3)
    void update() {
        // Assuming read() test has run successfully and retrievedCar is populated with correct data
        Car updatedCar = restTemplate.getForObject(BASE_URL + "/read/" + car.getCarId(), Car.class);
        assertNotNull(updatedCar);
        updatedCar.set("150");

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedCar);

        // Retrieve updated car and assert changes
        ResponseEntity<Car> response = restTemplate.getForEntity(BASE_URL + "/read/" + car.getCarID(), Car.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("150", response.getBody().getRentalRate());
        System.out.println("Updated car: " + response.getBody());
    }
    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" + car.getCarId();
        restTemplate.delete(url);

        // Ensure car is deleted
        ResponseEntity<Car> response = restTemplate.getForEntity(BASE_URL + "/read/" + car.getCarID(), Car.class);
        assertNull(response.getBody());
        System.out.println("Car deleted successfully.");
    }
    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAllCars";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("All cars: ");
        System.out.println(response.getBody());
    }
}