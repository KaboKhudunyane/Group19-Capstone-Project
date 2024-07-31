package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupportTicketServiceTest {

    @Autowired
    private SupportTicketService supportTicketService;

    @Autowired
    private UserService userService;

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

    private User user;
    private SupportTicket supportTicket;

    @BeforeEach
    void setUp() {
        Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
        Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
        Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("CityName").setProvince("Western Cape").setZipCode("12345").buildAddress();
        user = UserFactory.createUser(name, contact, address, true, userPicture);
        user = userService.create(user); // Save the user first
        assertNotNull(user, "User should be saved and not null");

        LocalDate dateCreated = LocalDate.of(2024, 4, 3);
        supportTicket = SupportTicketFactory.buildSupportTicket(user, "Technical Support", "I am facing login issues.", dateCreated);
    }

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
        SupportTicket createdSupportTicket = supportTicketService.create(supportTicket);
        assertNotNull(createdSupportTicket);

        SupportTicket readSupportTicket = supportTicketService.read(createdSupportTicket.getTicketID());
        assertNotNull(readSupportTicket);
        System.out.println("Read SupportTicket: " + readSupportTicket);
    }

    @Test
    void update() {
        SupportTicket createdSupportTicket = supportTicketService.create(supportTicket);
        assertNotNull(createdSupportTicket);

        SupportTicket updatedSupportTicket = new SupportTicket.Builder()
                .copy(createdSupportTicket)
                .setSubject("Updated Subject")
                .setMessage("Updated Message")
                .build();

        SupportTicket savedSupportTicket = supportTicketService.update(updatedSupportTicket);
        assertNotNull(savedSupportTicket);
        assertEquals("Updated Subject", savedSupportTicket.getSubject());
        assertEquals("Updated Message", savedSupportTicket.getMessage());
        System.out.println("Updated SupportTicket: " + savedSupportTicket);
    }

    @Test
    void delete() {
        SupportTicket createdSupportTicket = supportTicketService.create(supportTicket);
        assertNotNull(createdSupportTicket);

        supportTicketService.delete(createdSupportTicket.getTicketID());
        SupportTicket deletedSupportTicket = supportTicketService.read(createdSupportTicket.getTicketID());
        assertNull(deletedSupportTicket);
        System.out.println("SupportTicket deleted successfully.");
    }

    @Test
    void getAll() {
        SupportTicket createdSupportTicket1 = supportTicketService.create(supportTicket);

        SupportTicket supportTicket2 = SupportTicketFactory.buildSupportTicket(user, "Billing Issue", "Incorrect charges on my bill.", LocalDate.of(2024, 4, 5));
        SupportTicket createdSupportTicket2 = supportTicketService.create(supportTicket2);

        assertNotNull(createdSupportTicket1);
        assertNotNull(createdSupportTicket2);

        List<SupportTicket> supportTickets = supportTicketService.getAll();
        assertNotNull(supportTickets);
        assertTrue(supportTickets.size() >= 2);
        System.out.println("All SupportTickets: " + supportTickets);
    }
}
