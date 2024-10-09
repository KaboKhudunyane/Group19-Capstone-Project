package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Account;

import static org.junit.jupiter.api.Assertions.*;

public class AdminFactoryTest {
    Name name = new Name.Builder()
            .setFirstName("Joden")
            .setMiddleName("Nathe")
            .setLastName("Garfield")
            .buildName();

    Contact contact = new Contact.Builder()
            .setEmail("jJoden@example.com")
            .setMobileNumber("564467")
            .buildContact();

    Account account = new Account.Builder()
            .setUsername("adminUsername")
            .setPassword("J1234565")  // Set the password for the Account
            .buildAccount();

    @Test
    void buildAdmin() {
        Admin a = AdminFactory.buildAdmin(name, contact, account);  // Pass the Account object
        assertNotNull(a);
        System.out.println(a.toString());
    }
}
