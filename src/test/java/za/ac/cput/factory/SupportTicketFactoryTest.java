package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.factory.SupportTicketFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SupportTicketFactoryTest {

    @Test
    void buildSupportTicket() {
        SupportTicket supportTicket = SupportTicketFactory.createSupportTicket("T22", "Pmv6588", "Hire", "Aproved", "03-04-24", "Approved");
        assertNotNull(supportTicket);
        System.out.println(supportTicket);
    }

    @Test
    void testBuildSupportTicketWithFail() {
        SupportTicket supportTicket = SupportTicketFactory.createSupportTicket("T22", "Pmv6588", "Hire", "Aproved", "03-04-24", "Approved");
        assertNotNull(supportTicket);
        System.out.println(supportTicket);
    }
}
