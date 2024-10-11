package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserFactoryTest {
    // Licence picture path
    private static final String LICENSE_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";

    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    byte[] licensePicture = readFileAsBytes(LICENSE_PICTURE_PATH);

    // User ID picture path
    private static final String ID_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG2.PNG";
    byte[] idPicture = readFileAsBytes(ID_PICTURE_PATH);

    // Building embeddables
    Account account = new Account.Builder().setUsername("Username").setPassword("password").buildAccount();
    Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("Cape Town").setProvince("Western Cape").setZipCode("12345").buildAddress();

    @Test
    public void testBuildUser() {
        // Create a user with the role of USER
        User user = UserFactory.createUser(account, name, contact, address, licensePicture, idPicture, User.Role.USER);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testBuildUserWithAdminRole() {
        // Create a user with the role of ADMIN
        User adminUser = UserFactory.createUser(account, name, contact, address, licensePicture, idPicture, User.Role.ADMIN);
        assertNotNull(adminUser);
        System.out.println(adminUser);
    }

    @Test
    public void testBuildUserWithFail() {
        // Test with null account to simulate failure
        User user = UserFactory.createUser(null, name, contact, address, licensePicture, idPicture, User.Role.USER);
        assertNull(user);
        System.out.println(user);
    }
}
