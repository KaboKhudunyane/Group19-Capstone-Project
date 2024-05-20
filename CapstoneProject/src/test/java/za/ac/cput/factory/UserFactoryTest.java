package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserFactoryTest {

    Name name = new Name.Builder()
            .setFirstName("John")
            .setMiddleName("Fred")
            .setLastName("Doe")
            .buildName();

    Contact contact = new Contact.Builder()
            .setEmail("john@example.com")
            .setPhoneNumber("123456789")
            .buildContact();

    Address address = new Address.Builder()
            .setStreet("123 Main St")
            .setCity("Springfield")
            .setZipCode("12345")
            .build();

    @Test
    public void testBuildUser(){
        User user = UserFactory.createUser("123", name,
                contact, address,
                true,
                "Admin",
                "avatar.jpg",
                true);

        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testBuildUsrWithFail(){
        // Create a user with a null parameter so test fails
        User user = UserFactory.createUser("125", name,
                contact, address,
                true, "",
                "Avatar.jpg", true);

        assertNotNull(user);
        System.out.println(user);
    }
}
