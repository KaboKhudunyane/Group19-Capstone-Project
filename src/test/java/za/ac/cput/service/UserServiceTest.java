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

    private static Long userID_1;
    private static Long userID_2;
    private static Long userID_3;
    private static Long userID_4;
    private static Long userID_5;
    private static Long userID_6;



    Address address1 = AddressFactory.createAddress("123 Main St", "Springfield",
            "Cape town", "Western Cape", "12356");

    User user1= UserFactory.createUser("Kamogelo", "Mahlangu", "kamo", "password123", UserRole.ADMIN,
            "0617542365", "kamo@gmail.com", address1,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));



    Address address2 = AddressFactory.createAddress("222 Dorset St", "Foreshore",
            "Cape Town", "Western Cape", "12345");

    User user2= UserFactory.createUser("Lehlogonolo", "Mahlangu", "nolo", "nolo123", UserRole.ADMIN,
            "071458621", "nolo@gmail.com", address2,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));


    Address address3 = AddressFactory.createAddress("123 bel St", "Soshanguve",
            "Pretoria", "Gauteng", "8546");

    User user3= UserFactory.createUser("Phiwe", "Semoda", "phiwe", "phiwe123", UserRole.USER,
            "0665894125", "phiwe@gmail.com", address3,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));


    Address address4 = AddressFactory.createAddress("123 Lovely St", "Woodstock",
            "Cape Town", "Western Cape", "12345");

    User user4= UserFactory.createUser("Barron", "Inkwell", "barron", "barron123", UserRole.USER,
            "06412563897", "barron@gmail.com", address4,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));


    Address address5 = AddressFactory.createAddress("123 tell St", "Park",
            "Johannesburg", "Gauteng", "44444");

    User user5= UserFactory.createUser("Khethiwe", "Maboya", "khethiwe", "khethiwe123", UserRole.USER,
            "074569823", "khethiwe@gmail.com", address5,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));


    Address address6 = AddressFactory.createAddress("123 Small St", "Bellville",
            "Cape town", "Western Cape", "85641");

    User user6= UserFactory.createUser("Lira", "Worthwothy", "lira", "lira123", UserRole.USER,
            "074897544", "lira@gmail.com", address6,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));

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
        User createdUser1 = userService.create(user1);
        User createdUser2 = userService.create(user2);
        User createdUser3 = userService.create(user3);
        User createdUser4 = userService.create(user4);
        User createdUser5 = userService.create(user5);
        User createdUser6 = userService.create(user6);

        assertNotNull(createdUser1);
        assertNotNull(createdUser2);
        assertNotNull(createdUser3);
        assertNotNull(createdUser4);
        assertNotNull(createdUser5);
        assertNotNull(createdUser6);

        userID_1 = createdUser1.getUserID();
        userID_2 = createdUser2.getUserID();
        userID_3 = createdUser3.getUserID();
        userID_4 = createdUser4.getUserID();
        userID_5 = createdUser5.getUserID();
        userID_6 = createdUser6.getUserID();

        System.out.println("Created User: " + createdUser1);
        System.out.println("Created User: " + createdUser2);
        System.out.println("Created User: " + createdUser3);
        System.out.println("Created User: " + createdUser4);
        System.out.println("Created User: " + createdUser5);
        System.out.println("Created User: " + createdUser6);

    }

    @Test
    void read() {
        User readUser1= userService.read(userID_1);
        User readUser2 = userService.read(userID_2);
        User readUser3 = userService.read(userID_3);
        User readUser4 = userService.read(userID_4);
        User readUser5 = userService.read(userID_5);
        User readUser6 = userService.read(userID_6);

        assertNotNull(readUser1);
        assertNotNull(readUser2);
        assertNotNull(readUser3);
        assertNotNull(readUser4);
        assertNotNull(readUser5);
        assertNotNull(readUser6);

        System.out.println("Read User: " + readUser1);
        System.out.println("Read User: " + readUser2);
        System.out.println("Read User: " + readUser3);
        System.out.println("Read User: " + readUser4);
        System.out.println("Read User: " + readUser5);
        System.out.println("Read User: " + readUser6);

    }


/*
    @Test
    void delete() {
        userService.delete(userID);
        User deletedUser = userService.read(userID);
        assertNull(deletedUser);
        System.out.println("User deleted successfully.");
    }
*/

}
