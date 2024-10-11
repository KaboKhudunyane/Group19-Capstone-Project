package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.*;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.CarInsuranceFactory;
import za.ac.cput.factory.UserFactory;

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

    private final String BASE_URL = "http://localhost:8080/group19-capstone-project/api/carInformation";

    private User user;
    private CarInformation carInformation;

    @BeforeEach
    void setUp() {

        Account account = new Account.Builder().setUsername("Username").setPassword("password").buildAccount();
        Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
        Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
        Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("Cape Town").setProvince("Western Cape").setZipCode("12345").buildAddress();
        user = UserFactory.createUser(account, name, contact, address, loadPicture("lisence.jpg"), loadPicture("identity.jpg"));
        carInformation = CarInformationFactory.buildCarInformation(
                "Toyota", "Scarlet", "2020", "Manual", "Plate-123",
                "Red 5 door car with 50 000km mileage", "Leather seats, Navigation system, Bluetooth", user,
                2000, "Available",
                loadPicture("scarlet1.jpg"),
                loadPicture("scarlet2.jpg"),
                loadPicture("scarlet3.jpg")
        );


    }

    private byte[] loadPicture(String fileName) {
        try {
            Path path = Paths.get("src/images/img-prototype/" + fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity(url, carInformation, String.class);

        assertNotNull(response);
        System.out.println("Response: " + response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/1";

        ResponseEntity<CarInformation> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity(url, CarInformation.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CarInformation carInfo = response.getBody();
        assertNotNull(carInfo);
        System.out.println("Car Information: " + carInfo);
        assertEquals("Toyota", carInfo.getMake());
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<List> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity(url, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CarInformation> carList = response.getBody();
        assertNotNull(carList);
        assertTrue(carList.size() >= 0);

        System.out.println("All Cars:");
        for (Object c : carList) {
            System.out.println(c);
        }
    }
}
