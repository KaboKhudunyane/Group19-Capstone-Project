package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private static Long userID1;
    private static Long userID2;
    private static Long userID3;

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
        // Create first user
        User user1 = UserFactory.createUser(
                new Account.Builder().setUsername("User1").setPassword("password1").buildAccount(),
                new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName(),
                new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact(),
                new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("Cape Town").setProvince("Western Cape").setZipCode("12345").buildAddress(),
                loadPicture("lisence.jpg"),
                loadPicture("identity.jpg")
        );
        User createUser1 = userService.create(user1);
        assertNotNull(createUser1);
        userID1 = createUser1.getUserID(); // Save user ID for read tests
        System.out.println("Created User 1: " + createUser1);

        // Create second user
        User user2 = UserFactory.createUser(
                new Account.Builder().setUsername("User2").setPassword("password2").buildAccount(),
                new Name.Builder().setFirstName("Jane").setMiddleName("Ann").setLastName("Smith").buildName(),
                new Contact.Builder().setEmail("jane@example.com").setMobileNumber("987654321").buildContact(),
                new Address.Builder().setStreetName("456 Elm St").setSuburb("Shelbyville").setCity("Cape Town").setProvince("Western Cape").setZipCode("67890").buildAddress(),
                loadPicture("lisence.jpg"),
                loadPicture("identity.jpg")
        );
        User createUser2 = userService.create(user2);
        assertNotNull(createUser2);
        userID2 = createUser2.getUserID(); // Save user ID for read tests
        System.out.println("Created User 2: " + createUser2);

        // Create third user
        User user3 = UserFactory.createUser(
                new Account.Builder().setUsername("User3").setPassword("password3").buildAccount(),
                new Name.Builder().setFirstName("Alice").setMiddleName("Mary").setLastName("Johnson").buildName(),
                new Contact.Builder().setEmail("alice@example.com").setMobileNumber("555555555").buildContact(),
                new Address.Builder().setStreetName("789 Oak St").setSuburb("Evergreen").setCity("Cape Town").setProvince("Western Cape").setZipCode("54321").buildAddress(),
                loadPicture("lisence.jpg"),
                loadPicture("identity.jpg")
        );
        User createUser3 = userService.create(user3);
        assertNotNull(createUser3);
        userID3 = createUser3.getUserID(); // Save user ID for read tests
        System.out.println("Created User 3: " + createUser3);
    }

    @Test
    void read() {
        // Read and assert first user
        User readUser1 = userService.read(userID1);
        assertNotNull(readUser1);
        System.out.println("Read User 1: " + readUser1);

        // Read and assert second user
        User readUser2 = userService.read(userID2);
        assertNotNull(readUser2);
        System.out.println("Read User 2: " + readUser2);

        // Read and assert third user
        User readUser3 = userService.read(userID3);
        assertNotNull(readUser3);
        System.out.println("Read User 3: " + readUser3);
    }

    @Test
    void testCount() {
        System.out.println("Number of Users: " + userService.countUser());
    }
}
