package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Contact;
import za.ac.cput.repository.ContactRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class ContactServiceTest {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;
    private Contact contact1, contact2;

    @BeforeEach
    void setUp() {
        contactRepository = Mockito.mock(ContactRepository.class);
        contactService = new ContactService(contactRepository);

        contact1 = new Contact.Builder()
                .setContactId("C789")
                .setEmail("example@example.com")
                .setMobileNo(1234567890)
                .build();

        contact2 = new Contact.Builder()
                .setContactId("C790")
                .setEmail("sample@sample.com")
                .setMobileNo(987654310)
                .build();
    }

    @Test
        //passing test
    void create() {

        Contact created = contactService.create(contact1);
        assertNotNull(created);
        assertEquals(contact1, created);

    }

    @Test
        //failing test
    void read() {

        String found = String.valueOf(contactService.read("C789"));
        assertNotNull(found);
        assertTrue(found.contains("C789"));
    }

    @Test
        //passing test
    void update() {

        Contact updated = contactService.update(contact1);
        assertNull(updated, "Update method is not implemented yet");
    }

    @Test
        //failing test
    void delete() {
        contactService.delete(contact1.getContactId());
        Contact deletedContact = contactService.read(contact1.getContactId());
        assertNull(deletedContact);

    }

    @Test
        //passing test
    void getAll() {
        assertNull(contactService.getAll(), "GetAll method is not implemented yet");
    }
}
