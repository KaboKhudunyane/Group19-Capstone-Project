package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContactControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/Address";

    private static Contact cntct;
    @BeforeAll
    public static void setup() {
        Contact contact = ContactFactory.createContact("011", "575", 78736);
    }
    @Test
    void create() {
        String url = BASE_URL + "/save";
        ResponseEntity<Contact> postResponse = restTemplate.postForEntity(url, cntct, Contact.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Contact savedContact= postResponse.getBody();
        assertEquals(cntct.getContactId(), savedContact.getContactId());
        System.out.println("Contact saved: " + savedContact);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + cntct.getContactId();
        ResponseEntity<Contact> response = restTemplate.getForEntity(url, Contact.class);
        assertEquals(cntct.getContactId(), response.getBody().getContactId());
        System.out.println("Read Contact: " + response.getBody());
    }
    @Test
    void update() {
        // Modify Contact data for update
        Contact updatedContact = new Contact.Builder()
                .copyContact(cntct)
                .buildContact();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedContact);

        // Retrieve updated Contact and assert changes
        ResponseEntity<Contact> response = restTemplate.getForEntity(BASE_URL + "/read/" + cntct.getContactId(), Contact.class);
        assertNull(response.getBody());
        System.out.println(" Updated Contact: " + response.getBody());
    }
    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + cntct.getContactId();
        restTemplate.delete(url);

        // Ensure Contact is deleted
        ResponseEntity<Contact> response = restTemplate.getForEntity(BASE_URL + "/read/" + cntct.getContactId(), Contact.class);
        assertNull(response.getBody());
        System.out.println("Contact deleted successfully.");
    }

    @Test
    void getAllContact() {
        String url = BASE_URL + "/getAllContact";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("All Contacts: ");
        System.out.println(response.getBody());
    }
}
