package za.ac.cput.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.UserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    private static final String USER_PICTURE_PATH = "C:/Users/bokam/OneDrive/Desktop/Example.jpeg";

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
    private Name name = NameFactory.createName("Kabo", "Kb", "Khudunyane");
    private Contact contact = ContactFactory.createContact("123" ,"216273293@mycput.ac.za");
    private Address address = AddressFactory.createAddress("1", "216273293", "123 Street", "City", "7441");
    private User user = UserFactory.createUser(name, contact, address, true,  userPicture);
    
    @Test
    void create(){
        User createUser = userService.create(user);
        assertNotNull(createUser);
        System.out.println("Created User: " + createUser);
    }
    @Test
    void read(){
        User readUser = userService.read(user.getUserID());
        assertNotNull(readUser);
        System.out.println("Read User: " + readUser);
    }
    @Test
    void update(){
        User newUser = new User.Builder().copyUser(user).setLicense(false).buildUser();
        User updatedUser = userService.update(newUser);
        assertNotNull(updatedUser);
        System.out.println("Updated User: " + updatedUser);
    }
    @Test
    void delete(){
        userService.delete(user.getUserID());
        User deletedUser = userService.read(user.getUserID());
        assertNull(deletedUser);
        System.out.println("User deleted successfully.");
    }
}
