package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.SupportTicketFactory;
import za.ac.cput.factory.UserFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupportTicketServiceTest {
    @Autowired
    private SupportTicketService supportTicketService;
    @Autowired
    private UserService userService;

    private User user;
    private SupportTicket supportTicket;

    @BeforeEach
    void setUp() {
       user = userService.read(1L);
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
    @Test
    void testCount(){
        System.out.println("Numbers of Support Tickets: "+supportTicketService.countSupportTickets());
    }
}
