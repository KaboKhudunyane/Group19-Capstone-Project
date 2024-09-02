package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdminFactoryTest {
    Name name = new Name.Builder().setFirstName("Joden").setMiddleName("Nathe").setLastName("Garfield").buildName();
    Contact contact = new Contact.Builder().setEmail("Joden@example.com").setMobileNumber("564467").buildContact();

    @Test
    void buildAdmin() {

        Admin a = AdminFactory.buildAdmin(name, contact, "jodenG", "J1234565");
        assertNotNull(a);
        System.out.println(a.toString());
    }
}
