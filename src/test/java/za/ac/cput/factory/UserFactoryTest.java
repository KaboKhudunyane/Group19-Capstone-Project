package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryTest {
    //licence picture
    private byte[] loadPicture(String fileName) {
        try {
            Path path = Paths.get("src/images/img-prototype/" + fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }
   ;
    //building embeddables
    Account account = new Account.Builder().setUsername("Username").setPassword("password").buildAccount();
    Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("Cape Town").setProvince("Western Cape").setZipCode("12345").buildAddress();

    @Test
    public void testBuildUser() {
        User user = UserFactory.createUser(account, name, contact, address, loadPicture("lisence.jpg"), loadPicture("identity.jpg"));
        assertNotNull(user);
        System.out.println(user);
    }
    @Test
    public void testBuildUserWithFail() {
        User user = UserFactory.createUser(null ,name, contact, address,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));
        assertNull(user);
        System.out.println(user);
    }
}