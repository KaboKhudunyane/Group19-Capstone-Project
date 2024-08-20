package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;

import static org.junit.jupiter.api.Assertions.*;

public class AdminFactoryTest {
        Name name = new Name.Builder().setFirstName("Joden").setMiddleName("Nathe").setLastName("Garfield").buildName();
        Contact contact = new Contact.Builder().setEmail("jJoden@example.com").setMobileNumber("564467").buildContact();

    @Test
    void buildAdmin() {
        Admin a = AdminFactory.bluidAdmin(name,contact) ;
        assertNotNull(a);
        System.out.println(a.toString());
    }


}