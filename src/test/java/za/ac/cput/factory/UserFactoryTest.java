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
    Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("Cape Town").setProvince("Western Cape").setZipCode("12345").buildAddress();
    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static final String LICENSE_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";
    private static final String ID_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG2.PNG";
    byte[] licensePicture = readFileAsBytes(LICENSE_PICTURE_PATH);
    byte[] idPicture = readFileAsBytes(ID_PICTURE_PATH);
    @Test
    public void testBuildUser() {
        User user = UserFactory.createUser( User.Role.USER,"username", "password",  name, contact, address, licensePicture, idPicture);
        assertNotNull(user);
        System.out.println(user);
    }
    @Test
    public void testBuildUserWithAdminRole() {
        // Create a user with the role of ROLE_ADMIN
        User adminUser = UserFactory.createUser(User.Role.ROLE_ADMIN,"username", "password", name, contact, address, licensePicture, idPicture);
        assertNotNull(adminUser);
        System.out.println(adminUser);
    }

    @Test
    public void testBuildUserWithFail() {
        User user = UserFactory.createUser(User.Role.USER,"", "password", name, contact, address, licensePicture, idPicture);
        assertNull(user);
        System.out.println(user);
    }
}
