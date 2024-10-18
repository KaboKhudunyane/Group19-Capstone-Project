package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.User;
import za.ac.cput.enums.UserRole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserFactoryTest {

    // License picture path
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

    // Example address
    Address address = new Address.Builder()
            .setStreetName("123 Main St")
            .setSuburb("Springfield")
            .setCity("Cape Town")
            .setProvince("Western Cape")
            .setZipCode("12345")
            .build();

    @Test
    public void testBuildUser() {
        // Create a user with the role of USER
        User user = UserFactory.createUser("John", "Doe", "johndoe", "password123", UserRole.USER,
                "123456789", "john@example.com", address, licensePicture, idPicture);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testBuildUserWithAdminRole() {
        // Create a user with the role of ADMIN
        User adminUser = UserFactory.createUser("Jane", "Doe", "janedoe", "adminpassword", UserRole.ADMIN,
                "987654321", "jane@example.com", address, licensePicture, idPicture);
        assertNotNull(adminUser);
        System.out.println(adminUser);
    }

    @Test
    public void testBuildUserWithNullFields() {
        // Test with null username to simulate failure
        User user = UserFactory.createUser(null, "Doe", "johndoe", "password123", UserRole.USER,
                "123456789", "john@example.com", address, licensePicture, idPicture);
        assertNull(user);
        System.out.println(user);
    }
}
