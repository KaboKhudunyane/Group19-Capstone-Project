package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserFactoryTest {

    // Create a Name object for testing
    Name name = new Name.Builder()
            .setFirstName("John")
            .setMiddleName("Fred")
            .setLastName("Doe")
            .buildName();

    // Create a Contact object for testing
    Contact contact = new Contact.Builder()
            .setEmail("john@example.com")
            .setMobileNumber("123456789")
            .buildContact();

    // Create an Address object for testing
    Address address = new Address.Builder()
            .setStreetName("123 Main St")
            .setSuburb("Springfield")
            .setCity("CityName")
            .setState("StateName")
            .setZipCode("12345")
            .buildAddress();

    // Test case to verify successful User creation
    @Test
    public void testBuildUser(){
        User user = UserFactory.createUser("123", name,
                contact, address,
                true,
                "avatar.jpg");

        assertNotNull(user);  // Assert that the created User object is not null
        System.out.println(user);  // Print the created User object
    }

    // Test case to verify User creation with a failing condition
    @Test
    public void testBuildUserWithFail(){
        // Create a user with an empty picture so the factory returns null
        User user = UserFactory.createUser("125", name,
                contact, address,
                true, "");

        assertNull(user);  // Assert that the factory returns null
        System.out.println(user);  // Print the created User object (should be null)
    }
}
