package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Use a test profile if needed
class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:3306/group19-capstone-project/user";
    Name name = NameFactory.createName("Kabo", "Kb", "Khudunyane");
    Contact contact = ContactFactory.createContact("123", "kabo@example.com");
    Address address = AddressFactory.createAddress("123 Street", "Suburb", "City", "State", "12345");
    User user = UserFactory.createUser(name, contact, address, true, "profile.jpg");

    @Test
    void create() {
        String url = BASE_URL + "/create";
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
        String url = BASE_URL + "/read/" + user.getUserID();
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
                .setPicture("new_profile.jpg")
                .buildUser();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedUser);

        // Retrieve updated user and assert changes
        ResponseEntity<User> response = restTemplate.getForEntity(BASE_URL + "/read/" + user.getUserID(), User.class);
        assertNotNull(response.getBody());
        assertEquals("new_profile.jpg", response.getBody().getPicture());
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
    void getAll() {
        String url = BASE_URL + "/getAll";
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