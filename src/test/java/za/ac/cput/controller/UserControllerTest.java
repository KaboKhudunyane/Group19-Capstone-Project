package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.*;
import za.ac.cput.enums.UserRole;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.UserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Use a test profile if needed
class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/user";
    }

    Address address = AddressFactory.createAddress("123 Main St", "Springfield",
            "CityName", "Western Cape", "12345");

    User user = UserFactory.createUser("John", "Doe", "johndoe", "password123", UserRole.USER,
            "123456789", "john@example.com", address,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));


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
    void create() {
        String url = getBaseUrl() + "/create";
        ResponseEntity<User> postResponse = restTemplate.postForEntity(url, user, User.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        User savedUser = postResponse.getBody();
        assertNotNull(savedUser);
        assertEquals(user.getUserID(), savedUser.getUserID());
        System.out.println("Saved user: " + savedUser);
    }

    @Test
    void read() {
        String url = getBaseUrl() + "/read/" + user.getUserID();
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        assertNotNull(response.getBody());
        assertEquals(user.getUserID(), response.getBody().getUserID());
        System.out.println("Read user: " + response.getBody());
    }


    @Test
    void delete() {
        String url = getBaseUrl() + "/delete/" + user.getUserID();
        restTemplate.delete(url);
        ResponseEntity<User> response = restTemplate.getForEntity(getBaseUrl() + "/read/" + user.getUserID(), User.class);
        assertNull(response.getBody());
        System.out.println("User deleted successfully.");
    }

    @Test
    void getAll() {
        String url = getBaseUrl() + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<User[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        for (User u : response.getBody()) {
            System.out.println(u);
        }
    }


}