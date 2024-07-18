package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
public class SupportTicketFactoryTest {
    private Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    private Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    private Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("CityName").setState("StateName").setZipCode("12345").buildAddress();
    private User user = UserFactory.createUser(name, contact, address, true, "avatar.jpg");
    @Test
    void buildSupportTicket() {
        LocalDate dateCreated = LocalDate.of(2024, 4, 3);
        SupportTicket supportTicket = SupportTicketFactory.buildSupportTicket(user, "Technical Support", "I am facing login issues.", dateCreated);
        assertNotNull(supportTicket);
        assertEquals(user, supportTicket.getUser());
        assertEquals("Technical Support", supportTicket.getSubject());
        assertEquals("I am facing login issues.", supportTicket.getMessage());
        assertEquals(dateCreated, supportTicket.getDateCreated());
    }
    @Test
    void testBuildSupportTicketWithFail() {
        LocalDate dateCreated = LocalDate.of(2024, 4, 3);
        SupportTicket supportTicket = SupportTicketFactory.buildSupportTicket(user, "", "I am facing login issues.", dateCreated);
        assertNull(supportTicket);
        System.out.println(supportTicket);
    }
}