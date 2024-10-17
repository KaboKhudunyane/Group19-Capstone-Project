package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.*;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;

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

    private static final String LICENSE_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";
    private static final String USER_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG2.PNG";

    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    byte[] licensePicture = readFileAsBytes(LICENSE_PICTURE_PATH);
    byte[] identityDocumentPicture = readFileAsBytes(USER_PICTURE_PATH); // Consider renaming for clarity

    // Create the User object directly with username and password
    User user = new User.Builder()
            .setUsername("Username")
            .setPassword("password") // Ensure this is hashed in your UserService
            .setRole(User.Role.USER)
            .setName(NameFactory.createName("Kabo", "Kb", "Khudunyane"))
            .setContact(ContactFactory.createContact("216273293@mycput.ac.za", "0658595712"))
            .setAddress(AddressFactory.createAddress("123 Street", "Suburb", "City", "State", "12345"))
            .setLicense(licensePicture)
            .setIdentityDocument(identityDocumentPicture)
            .buildUser();

    @Test
    void create() {
        String url = getBaseUrl() + "/create";
        ResponseEntity<User> postResponse = restTemplate.postForEntity(url, user, User.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode()); // Update to expect OK status
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
    void update() {
        // Set a new license picture (or modify other details as needed)
        byte[] newLicensePicture = readFileAsBytes(LICENSE_PICTURE_PATH); // Update the image path as needed
        // Modify user data for update
        User updatedUser = new User.Builder()
                .copyUser(user)
                .setLicense(newLicensePicture)
                .buildUser();
        String url = getBaseUrl() + "/update";
        restTemplate.put(url, updatedUser);
        ResponseEntity<User> response = restTemplate.getForEntity(getBaseUrl() + "/read/" + user.getUserID(), User.class);
        assertNotNull(response.getBody());
        assertArrayEquals(newLicensePicture, response.getBody().getLicense());
        System.out.println("Updated user: " + response.getBody());
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

    @Test
    void login() {
        String url = getBaseUrl() + "/login";
        // Create a user object with username and password for the login test
        User loginUser = new User.Builder()
                .setUsername("Username")
                .setPassword("password") // Ensure this matches the created user's credentials
                .buildUser();
        ResponseEntity<String> response = restTemplate.postForEntity(url, loginUser, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful!", response.getBody());
        System.out.println("Login test response: " + response.getBody());
    }
}
