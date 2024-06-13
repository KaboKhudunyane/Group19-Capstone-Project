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
    void create() {
        SupportTicket created1 = supportTicketService.create(supportTicket1);
        assertNotNull(created1);
        System.out.println("Saved SupportTicket1: " + created1);
        SupportTicket created2 = supportTicketService.create(supportTicket2);
        assertNotNull(created2);
        System.out.println("Saved SupportTicket 2: " + created2);
    }

  @Test
    @Order(2)
    void read() {
        SupportTicket read = supportTicketService.read(supportTicket2.getUserID());
        assertNotNull(read);
        System.out.println("Read UserID: " + read);
    }

  @Test
    @Order(3)
    void delete() {
        supportTicketService.delete(supportTicket1.getTicketID());
        System.out.println("Deleted TicketID 1");
    }

    @Test
    @Order(4)
    void update(){
        SupportTicket newSupportTicket = new SupportTicket.Builder().copy(supportTicket1).setUserID("T33").buildSupportTicket();

        SupportTicket updatedSupportTicket = SupportTicketService.update(newSupportTicket);
        assertNotNull(updatedSupportTicket);
        System.out.println("Updated SupportTicket: " + updatedSupportTicket);

    }

}
