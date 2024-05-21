package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.domain.Contact;
class ContactFactoryTest {

    @Test
    void createContact_validInputs() {
        Contact contact = ContactFactory.createContact("C789", "example@example.com", 1234567890);
        assertNotNull(contact);
        assertEquals("C789", contact.getContactId());
        assertEquals("example@example.com", contact.getEmail());
        assertEquals(1234567890, contact.getMobileNo());
    }

    @Test
    void createContact_nullContactId() {
        Contact contact = ContactFactory.createContact(null, "example@example.com", 1234567890);
        assertNull(contact, "Contact creation with null contactId should return null");
    }

    @Test
    void createContact_emptyContactId() {
        Contact contact = ContactFactory.createContact("", "example@example.com", 1234567890);
        assertNull(contact, "Contact creation with empty contactId should return null");
    }

    @Test
    void createContact_nullEmail() {
        Contact contact = ContactFactory.createContact("C789", null, 1234567890);
        assertNull(contact, "Contact creation with null email should return null");
    }

    @Test
    void createContact_emptyEmail() {
        Contact contact = ContactFactory.createContact("C789", "", 1234567890);
        assertNull(contact, "Contact creation with empty email should return null");
    }

    @Test
    void createContact_invalidEmail() {
        Contact contact = ContactFactory.createContact("C789", "invalid-email", 1234567890);
        assertNull(contact, "Contact creation with invalid email should return null");
    }

    @Test
    void createContact_invalidMobileNo() {
        Contact contact = ContactFactory.createContact("C789", "example@example.com", -1);
        assertNull(contact, "Contact creation with invalid mobileNo should return null");
    }
}