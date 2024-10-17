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
    private Name name;
    private Contact contact;
    private Address address;
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
    private static final String LICENSE_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";
    private static final String ID_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG2.PNG";
    private byte[] licensePicture;
    private byte[] idPicture;
    private User user;
    @BeforeEach
    void setUp() {
        name = NameFactory.createName("Thato", "Emeka", "Nwamadi");
        contact = ContactFactory.createContact("295732963@mycput.ac.za", "0654545212");
        address = AddressFactory.createAddress("89 St Marks", "District 10", "Cape Town", "Western Cape", "8000");
        licensePicture = compressImage(LICENSE_PICTURE_PATH);
        idPicture = compressImage(ID_PICTURE_PATH);
        user = UserFactory.createUser(User.Role.USER,"Username", "password", name, contact, address, licensePicture, idPicture);
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
        byte[] newLicensePicture = compressImage(LICENSE_PICTURE_PATH);
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
