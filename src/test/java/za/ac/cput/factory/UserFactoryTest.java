package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserFactoryTest {

    private static final String USER_PICTURE_PATH = "C:\\Users\\user\\Downloads\\V P I\\Group19-Capstone-Project\\image";

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

    Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("CityName").setState("StateName").setZipCode("12345").buildAddress();

    @Test
    public void testBuildUser() {
        User user = UserFactory.createUser(name, contact, address, true, userPicture);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testBuildUserWithFail() {
        User user = UserFactory.createUser(null, contact, address, true, userPicture); // Passing null for name
        assertNull(user); // Expecting user creation to fail and return null
        System.out.println(user);
    }
}
