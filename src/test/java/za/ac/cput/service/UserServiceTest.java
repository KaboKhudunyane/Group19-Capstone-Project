package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.UserFactory;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    // Image paths
    private static final String LICENSE_PICTURE_PATH = "C:\\Users\\bokam\\OneDrive\\Documents\\GitHub\\Group19-Capstone-Project\\src\\Images\\img-prototype\\bmw1.jpg";
    private static final String ID_PICTURE_PATH = "C:\\Users\\bokam\\OneDrive\\Documents\\GitHub\\Group19-Capstone-Project\\src\\Images\\img-prototype\\bmw1.jpg";

    private byte[] compressImage(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
            return null;
        }
    }

    private byte[] licensePicture;
    private byte[] idPicture;
    private Account account;
    private Name name;
    private Contact contact;
    private Address address;
    private User user;

    @BeforeEach
    void setUp() {
        licensePicture = compressImage(LICENSE_PICTURE_PATH);
        idPicture = compressImage(ID_PICTURE_PATH);

        account = new Account.Builder().setUsername("Username").setPassword("password").buildAccount();
        name = NameFactory.createName("Thato", "Emeka", "Nwamadi");
        contact = ContactFactory.createContact("295732963@mycput.ac.za", "0654545212");
        address = AddressFactory.createAddress("89 St Marks", "District 10", "Cape Town", "Western Cape", "8000");

        // Create user with role
        user = UserFactory.createUser(account, name, contact, address, licensePicture, idPicture, User.Role.USER); // Set the role here
    }

    @Test
    void create() {
        User createdUser = userService.create(user);
        assertNotNull(createdUser);
        System.out.println("Created User: " + createdUser);
    }

    @Test
    void read() {
        User readUser = userService.read(user.getUserID());
        assertNotNull(readUser);
        System.out.println("Read User: " + readUser);
    }

    @Test
    void update() {
        // Set a new license picture (or modify other details as needed)
        byte[] newLicensePicture = compressImage(LICENSE_PICTURE_PATH); // Update the image path as needed

        User newUser = new User.Builder().copyUser(user).setLicense(newLicensePicture).buildUser();
        User updatedUser = userService.update(newUser);
        assertNotNull(updatedUser);
        System.out.println("Updated User: " + updatedUser);
    }

    @Test
    void delete() {
        userService.delete(user.getUserID());
        User deletedUser = userService.read(user.getUserID());
        assertNull(deletedUser);
        System.out.println("User deleted successfully.");
    }

    @Test
    void testCount() {
        System.out.println("Number of Users: " + userService.countUser());
    }
}
