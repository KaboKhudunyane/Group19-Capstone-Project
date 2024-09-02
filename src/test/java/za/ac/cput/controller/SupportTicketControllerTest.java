package za.ac.cput.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SupportTicketControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/supportTicket";

    private byte[] loadPicture(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }

    private static final String LICENSE_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";
    private static final String ID_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";

    Account account = new Account.Builder().setUsername("Username").setPassword("password").buildAccount();
    Name name = NameFactory.createName("Thenjiwe", "TM", "Mbi");
    Contact contact = ContactFactory.createContact("123", "MBi@example.com");
    Address address = AddressFactory.createAddress("123 Street", "Suburb", "City", "State", "12345");
    User user = UserFactory.createUser(account, name, contact, address, licensePicture, idPicture);
    LocalDate dateCreated = LocalDate.of(2024, 4, 3);
    SupportTicket supportTicket = SupportTicketFactory.buildSupportTicket(user, "Technical Support", "I am facing login issues.", dateCreated);

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<SupportTicket> postResponse = restTemplate.postForEntity(url, supportTicket, SupportTicket.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        SupportTicket supportTicketSaved = postResponse.getBody();
        assertNotNull(supportTicketSaved.getTicketID()); // Check if ticketID is returned
        assertEquals(supportTicket.getTicketID(), supportTicketSaved.getTicketID()); // Check if ticketID matches
        System.out.println("Saved data: " + supportTicketSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + supportTicket.getTicketID();
        System.out.println("URL: " + url);
        ResponseEntity<SupportTicket> response = restTemplate.getForEntity(url, SupportTicket.class);
        assertEquals(supportTicket.getTicketID(), response.getBody().getTicketID()); // Check if ticketID matches
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        SupportTicket newSupportTicket = new SupportTicket.Builder().copy(supportTicket)
                .setMessage("Not Approved").build();
        ResponseEntity<SupportTicket> postResponse = restTemplate.postForEntity(url, newSupportTicket, SupportTicket.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        SupportTicket supportTicketUpdated = postResponse.getBody();
        assertEquals(newSupportTicket.getTicketID(), supportTicketUpdated.getTicketID()); // Check if ticketID matches
        System.out.println("Data Updated: " + supportTicketUpdated);
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + supportTicket.getTicketID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("SupportTicket Deleted");
    }

    @Test
    void getAll() {
        String url = BASE_URL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show All:");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
