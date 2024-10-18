package za.ac.cput.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.enums.UserRole;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.UserFactory;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    private static Long userID;


    Address address = AddressFactory.createAddress("123 Main St", "Springfield",
            "CityName", "Western Cape", "12345");

    User user = UserFactory.createUser("John", "Doe", "username", "PassBD25XY", UserRole.ADMIN,
            "123456789", "john@example.com", address,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));

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
        User createdUser = userService.create(user);
        assertNotNull(createdUser);
        userID = createdUser.getUserID();
        System.out.println("Created User: " + createdUser);
    }

    @Test
    void read() {
        User readUser = userService.read(userID);
        assertNotNull(readUser);
        System.out.println("Read User: " + readUser);
    }



    @Test
    void delete() {
        userService.delete(userID);
        User deletedUser = userService.read(userID);
        assertNull(deletedUser);
        System.out.println("User deleted successfully.");
    }


}
