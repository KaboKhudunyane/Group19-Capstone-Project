package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.ContactFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    private static Contact contact = ContactFactory.createContact("123456789", "test@example.com");

    @Test
    void create() {
        Contact createdContact = contactService.create(contact);
        assertNotNull(createdContact);
        System.out.println("Created Contact: " + createdContact);
    }

    @Test
    void read() {
        Contact readContact = contactService.read(contact.getEmail());
        assertNotNull(readContact);
        System.out.println("Read Contact: " + readContact);
    }

    @Test
    void update() {
        Contact newContact = new Contact.Builder()
                .copyContact(contact)
                .setMobileNumber("987654321")
                .buildContact();
        Contact updatedContact = contactService.update(newContact);
        assertNotNull(updatedContact);
        System.out.println("Updated Contact: " + updatedContact);
    }

    @Test
    void delete() {
        contactService.delete(contact.getEmail());
        Contact deletedContact = contactService.read(contact.getEmail());
        assertNull(deletedContact);
        System.out.println("Contact deleted successfully.");
    }
}
