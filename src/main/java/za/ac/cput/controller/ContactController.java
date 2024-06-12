package za.ac.cput.controller;


import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;
    @PostMapping("/save")
    public Contact save(@RequestBody Contact contact){

        return contactService.create(contact);
    }

    @GetMapping("/read/{contactId}")
    public Contact read(@PathVariable String contactId){

        return contactService.read(contactId);
    }

    @PutMapping("/update")
    public ResponseEntity<Contact> updateContact( @RequestBody Contact contactDetails) {
        Contact updatedContact = contactService.update(contactDetails);
        return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{contactId}")
    public void deleteContact(@PathVariable String id) {
        contactService.delete(id);
    }
    @GetMapping("/getAllContact")
    public List<Contact> getAllContact(){
        return contactService.getAll();
    }
}

