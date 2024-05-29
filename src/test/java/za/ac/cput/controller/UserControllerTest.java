package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/user";

    private static User user;

    @BeforeAll
    public static void setup() {
        Name name = NameFactory.createName("Kabo", "Kb", "Khudunyane");
        Contact contact = ContactFactory.createContact("010", "216273293@mycput.ac.za", 012345);
        Address address = AddressFactory.createAddress("011", "575", "123 Street","District 6", 7441);
        user = UserFactory.createUser("216273293", name, contact, address, true, "Admin", "Kabo.jpeg", true);
    }
    @Test
    void create() {
        String url = BASE_URL + "/save";
        ResponseEntity<User> postResponse = restTemplate.postForEntity(url, user, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        User savedUser = postResponse.getBody();
        assertEquals(user.getUserID(), savedUser.getUserID());
        System.out.println("Saved user: " + savedUser);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + user.getUserID();
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        assertEquals(user.getUserID(), response.getBody().getUserID());
        System.out.println("Read user: " + response.getBody());
    }
    @Test
    void update() {
        // Modify user data for update
        User updatedUser = new User.Builder()
                .copyUser(user)
                .setVerified(false) // Update the verification status
                .buildUser();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedUser);

        // Retrieve updated user and assert changes
        ResponseEntity<User> response = restTemplate.getForEntity(BASE_URL + "/read/" + user.getUserID(), User.class);
        assertFalse(response.getBody().getVerified());
        System.out.println("Updated user: " + response.getBody());
    }
    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + user.getUserID();
        restTemplate.delete(url);

        // Ensure user is deleted
        ResponseEntity<User> response = restTemplate.getForEntity(BASE_URL + "/read/" + user.getUserID(), User.class);
        assertNull(response.getBody());
        System.out.println("User deleted successfully.");
    }

    @Test
    void getAllUsers() {
        String url = BASE_URL + "/getAllUsers";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("All users: ");
        System.out.println(response.getBody());
    }
}
