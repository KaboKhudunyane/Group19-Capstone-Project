package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class NameControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:3306/group19-capstone-project/name";

    private static Name name;

    @BeforeAll
    public static void setup() {
        name = NameFactory.createName("John", "Doe", "Smith");
    }

    @Test
    void create() {
        String url = BASE_URL + "/save";
        ResponseEntity<Name> postResponse = restTemplate.postForEntity(url, name, Name.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        Name savedName = postResponse.getBody();
        assertNotNull(savedName);
        assertEquals(name.getFirstName(), savedName.getFirstName());
        System.out.println("Saved name: " + savedName);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + name.getFirstName();
        ResponseEntity<Name> response = restTemplate.getForEntity(url, Name.class);
        assertNotNull(response.getBody());
        assertEquals(name.getFirstName(), response.getBody().getFirstName());
        System.out.println("Read name: " + response.getBody());
    }

    @Test
    void update() {
        // Modify name data for update
        Name updatedName = new Name.Builder()
                .copyName(name)
                .setLastName("Brown")
                .buildName();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedName);

        // Retrieve updated name and assert changes
        ResponseEntity<Name> response = restTemplate.getForEntity(BASE_URL + "/read/" + name.getFirstName(), Name.class);
        assertNotNull(response.getBody());
        assertEquals("Brown", response.getBody().getLastName());
        System.out.println("Updated name: " + response.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + name.getFirstName();
        restTemplate.delete(url);

        // Ensure name is deleted
        ResponseEntity<Name> response = restTemplate.getForEntity(BASE_URL + "/read/" + name.getFirstName(), Name.class);
        assertNull(response.getBody());
        System.out.println("Name deleted successfully.");
    }

    @Test
    void getAllNames() {
        String url = BASE_URL + "/getAllNames";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Name[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Name[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        for (Name n : response.getBody()) {
            System.out.println(n);
        }
    }
}
