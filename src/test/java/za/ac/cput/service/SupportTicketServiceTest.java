package za.ac.cput.repository.factory;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.factory.SupportTicketFactory;
import za.ac.cput.service.SupportTicketService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SupportTicketServiceTest {
    @Autowired
    private SupportTicketService SupportTicketService;

    private static SupportTicket SupportTicket1;
    private static SupportTicket SupportTicket2;

    @BeforeEach
    void setUp() {
        SupportTicket1 = SupportTicketFactory.buildSupportTicket("T24", "ccv6588", "Hire", "Aproved", "03-04-24", "Approved");
        assertNotNull(SupportTicket1);
        SupportTicket2 = SupportTicketFactory.buildSupportTicket("T24", "ccv6588", "Hire", "pending", "03-08-23", "pending");
        assertNotNull(SupportTicket2);
    }

    @Test
    @Order(1)
    void save() {
        SupportTicket created1 = SupportTicketService.save(SupportTicket1);
        assertNotNull(created1);
        System.out.println("Saved SupportTicket1: " + created1);
        SupportTicket created2 = SupportTicketService.save(SupportTicket2);
        assertNotNull(created2);
        System.out.println("Saved SupportTicket 2: " + created2);
    }

    @Test
    @Order(2)
    void read() {
        CarInsurance read = SupportTicketService.read(SupportTicket2.getSupportTicketId());
        assertNotNull(read);
        System.out.println("Read SupportTicket: " + read);
    }

    @Test
    @Order(3)
    void delete() {
        boolean deleted = SupportTicketService.delete(SupportTicket1.getSupportTicketId());
        assertTrue(deleted);
        System.out.println("Deleted SupportTicket 1");
    }

    @Test
    @Order(4)
    void getAll() {
        List<SupportTicket> allSupportTicket = SupportTicketService.getAll();
        assertNotNull(allSupportTicket);
        assertTrue(allSupportTicket.size() > 0);
        System.out.println("All CarInsurance: " + allSupportTicket);
    }

}
