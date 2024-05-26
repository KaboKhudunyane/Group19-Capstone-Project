package za.ac.cput.controller;


import za.ac.cput.domain.Contact;
import za.ac.cput.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAll();
    }

    @GetMapping("/{id}")
    public String getContactById(@PathVariable String id) {
        String contact = contactService.read(id);
        return contact ;
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.create(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact( @RequestBody Contact contactDetails) {
        Contact updatedContact = contactService.update(contactDetails);
        return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        boolean isDeleted = contactService.delete(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

