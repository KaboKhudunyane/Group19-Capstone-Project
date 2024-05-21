package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import za.ac.cput.domain.Contact;
import za.ac.cput.repository.ContactRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    private ContactRepository contactRepository;
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
        when(contactRepository.save(any(Contact.class))).thenReturn(contact1);
        Contact created = contactService.create(contact1);
        assertNotNull(created);
        assertEquals(contact1, created);
        verify(contactRepository, times(1)).save(contact1);
    }

    @Test
        //failing test
    void read() {
        when(contactRepository.findById("C789")).thenReturn(Optional.of(contact1));
        String found = contactService.read("C789");
        assertNotNull(found);
        assertTrue(found.contains("C789"));
        verify(contactRepository, times(1)).findById("C789");
    }

    @Test
    //passing test
    void update() {
        when(contactRepository.save(any(Contact.class))).thenReturn(contact1);
        Contact updated = contactService.update(contact1);
        assertNull(updated, "Update method is not implemented yet");
    }

    @Test
        //failing test
    void delete() {
        doNothing().when(contactRepository).deleteById("C789");
        contactService.delete("C789");
        verify(contactRepository, times(1)).deleteById("C789");
    }

    @Test
    //passing test
    void getAll() {
        assertNull(contactService.getAll(), "GetAll method is not implemented yet");
    }
}