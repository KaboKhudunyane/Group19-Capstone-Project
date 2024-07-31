package za.ac.cput.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;
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
    private static final String USER_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";
    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    byte[] userPicture = readFileAsBytes(USER_PICTURE_PATH);
    Name name = NameFactory.createName("Kabo", "Kb", "Khudunyane");
    Contact contact = ContactFactory.createContact("216273293@mycput.ac.za", "0658595712");
    Address address = AddressFactory.createAddress("123 Street", "Suburb", "City", "State", "12345");
    User user = UserFactory.createUser(name, contact, address, true, userPicture);
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
    void update() {
        // Modify user data for update
        User updatedUser = new User.Builder()
                .copyUser(user)
                .setPicture(userPicture)
                .buildUser();
        String url = getBaseUrl() + "/update";
        restTemplate.put(url, updatedUser);
        ResponseEntity<User> response = restTemplate.getForEntity(getBaseUrl() + "/read/" + user.getUserID(), User.class);
        assertNotNull(response.getBody());
        assertArrayEquals(userPicture, response.getBody().getPicture()); // Adjusted this check for picture
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
}