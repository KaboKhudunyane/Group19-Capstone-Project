package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.ContactFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ContactControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:3306/group19-capstone-project/contact";

    private static Contact contact;

    @BeforeAll
    public static void setup() {
        contact = ContactFactory.createContact("1234567890", "test@example.com");
    }

    @Test
    void create() {
        String url = BASE_URL + "/save";
        ResponseEntity<Contact> postResponse = restTemplate.postForEntity(url, contact, Contact.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        Contact savedContact = postResponse.getBody();
        assertNotNull(savedContact);
        assertEquals(contact.getEmail(), savedContact.getEmail());
        System.out.println("Saved contact: " + savedContact);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + contact.getEmail();
        ResponseEntity<Contact> response = restTemplate.getForEntity(url, Contact.class);
        assertNotNull(response.getBody());
        assertEquals(contact.getEmail(), response.getBody().getEmail());
        System.out.println("Read contact: " + response.getBody());
    }

    @Test
    void update() {
        // Modify contact data for update
        Contact updatedContact = new Contact.Builder()
                .copyContact(contact)
                .setMobileNumber("9876543210")
                .buildContact();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedContact);

        // Retrieve updated contact and assert changes
        ResponseEntity<Contact> response = restTemplate.getForEntity(BASE_URL + "/read/" + contact.getEmail(), Contact.class);
        assertNotNull(response.getBody());
        assertEquals("9876543210", response.getBody().getMobileNumber());
        System.out.println("Updated contact: " + response.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + contact.getEmail();
        restTemplate.delete(url);

        // Ensure contact is deleted
        ResponseEntity<Contact> response = restTemplate.getForEntity(BASE_URL + "/read/" + contact.getEmail(), Contact.class);
        assertNull(response.getBody());
        System.out.println("Contact deleted successfully.");
    }

    @Test
    void getAllContacts() {
        String url = BASE_URL + "/getAllContacts";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Contact[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Contact[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        for (Contact c : response.getBody()) {
            System.out.println(c);
        }
    }
}
