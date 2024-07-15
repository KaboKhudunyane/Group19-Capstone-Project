package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Contact;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ContactFactoryTest {

    // Test case to verify successful Contact creation
    @Test
    public void testBuildContact() {
        Contact contact = ContactFactory.createContact("john@example.com", "123456789");

        assertNotNull(contact);  // Assert that the created Contact object is not null
        System.out.println(contact);  // Print the created Contact object
    }

    // Test case to verify Contact creation with a failing condition
    @Test
    public void testBuildContactWithFail() {
        // Create a contact with an empty email so the factory returns null
        Contact contact = ContactFactory.createContact("", "123456789");

        assertNull(contact);  // Assert that the factory returns null
        System.out.println(contact);  // Print the created Contact object (should be null)
    }
}
