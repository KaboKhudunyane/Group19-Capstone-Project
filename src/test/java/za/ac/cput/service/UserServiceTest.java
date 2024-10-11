package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    private static Long userID;

    Account account = new Account.Builder().setUsername("Username").setPassword("password").buildAccount();
    Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("Cape Town").setProvince("Western Cape").setZipCode("12345").buildAddress();
    User user = UserFactory.createUser(account, name, contact, address, loadPicture("lisence.jpg"), loadPicture("identity.jpg"));



    private byte[] loadPicture(String fileName) {
        try {
            Path path = Paths.get("src/images/img-prototype/" + fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }

    @Test
    void create() {
        User createUser = userService.create(user);
        assertNotNull(createUser);
        userID = createUser.getUserID();
        System.out.println("Created User: " + createUser);
    }
    @Test
    void read() {
        User readUser = userService.read(userID);
        assertNotNull(readUser);
        System.out.println("Read User: " + readUser);
    }

    @Test
    void delete() {
        userService.delete(user.getUserID());
        User deletedUser = userService.read(user.getUserID());
        assertNull(deletedUser);
        System.out.println("User deleted successfully.");
    }
    @Test
    void testCount(){
        System.out.println("Numbers of User: "+userService.countUser());
    }
}
