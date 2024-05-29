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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private static Name name = NameFactory.createName("Kabo", "Kb", "Khudunyane");
    private static Contact contact = ContactFactory.createContact("123" ,"216273293@mycput.ac.za",05665665254);
    private static Address address = AddressFactory.createAddress("1", "216273293", "123 Street", "City", 12345);
    private static User user = UserFactory.createUser("216273293", name, contact, address, true, "Admin", "Kabo.jpeg", true);

    // Test case for creating a user
    @Test
    void create(){
        User createUser = userService.create(user);
        assertNotNull(createUser);
        System.out.println("Created User: " + createUser);
    }
    // Test case for reading a user
    @Test
    void read(){
        User readUser = userService.read(user.getUserID());
        assertNotNull(readUser);
        System.out.println("Read User: " + readUser);
    }
    // Test case for updating a user
    @Test
    void update(){
        User newUser = new User.Builder().copyUser(user).setUserID("222273293").buildUser();

        User updatedUser = userService.update(newUser);
        assertNotNull(updatedUser);
        System.out.println("Updated User: " + updatedUser);
    }
    // Test case for deleting a user
    @Test
    void delete(){
        userService.delete(user.getUserID());
        User deletedUser = userService.read(user.getUserID());
        assertNull(deletedUser);
        System.out.println("User deleted successfully.");
    }
}
