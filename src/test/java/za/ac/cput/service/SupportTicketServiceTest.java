package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.SupportTicketFactory;
import za.ac.cput.factory.UserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupportTicketServiceTest {

    @Autowired
    private SupportTicketService supportTicketService;

    private static final String USER_PICTURE_PATH = "C:/Users/bokam/OneDrive/Desktop/Example.jpeg";

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

    private final Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    private final Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    private final Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("CityName").setState("StateName").setZipCode("12345").buildAddress();
    private final User user = UserFactory.createUser(name, contact, address, true, userPicture);
    private final LocalDate dateCreated = LocalDate.of(2024, 4, 3);
    private final SupportTicket supportTicket = SupportTicketFactory.buildSupportTicket(user, "Technical Support", "I am facing login issues.", dateCreated);
    @Test
    void create() {
        SupportTicket createdSupportTicket = supportTicketService.create(supportTicket);
        assertNotNull(createdSupportTicket);
        assertEquals(supportTicket.getUser(), createdSupportTicket.getUser());
        assertEquals(supportTicket.getSubject(), createdSupportTicket.getSubject());
        assertEquals(supportTicket.getMessage(), createdSupportTicket.getMessage());
        assertEquals(supportTicket.getDateCreated(), createdSupportTicket.getDateCreated());
        System.out.println("Created SupportTicket: " + createdSupportTicket);
    }
    @Test
    void read() {
        SupportTicket readSupportTicket = supportTicketService.read(supportTicket.getTicketID());
        assertNotNull(readSupportTicket);
        System.out.println("Read SupportTicket: " + readSupportTicket);
    }
    @Test
    void update() {
        SupportTicket newSupportTicket = new SupportTicket.Builder()
                .setUser(user)
                .setSubject("Updated Subject")
                .setMessage("Updated Message")
                .setDateCreated(dateCreated)
                .build();
        SupportTicket updatedSupportTicket = supportTicketService.update(newSupportTicket);
        assertNotNull(updatedSupportTicket);
        assertEquals("Updated Subject", updatedSupportTicket.getSubject());
        assertEquals("Updated Message", updatedSupportTicket.getMessage());
        System.out.println("Updated SupportTicket: " + updatedSupportTicket);
    }
    @Test
    void delete() {
        supportTicketService.delete(supportTicket.getTicketID());
        SupportTicket deletedSupportTicket = supportTicketService.read(supportTicket.getTicketID());
        assertNull(deletedSupportTicket);
        System.out.println("SupportTicket deleted successfully.");
    }
    @Test
    void getAll() {
        assertNotNull(supportTicketService.getAll());
        System.out.println("All SupportTickets: " + supportTicketService.getAll());
    }
}