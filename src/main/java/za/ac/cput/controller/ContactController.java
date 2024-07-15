package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Contact;
import za.ac.cput.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/save")
    public Contact save(@RequestBody Contact contact) {
        return contactService.create(contact);
    }

    @GetMapping("/read/{email}")
    public Contact read(@PathVariable String email) {
        return contactService.read(email);
    }

    @PutMapping("/update")
    public Contact update(@RequestBody Contact contact) {
        return contactService.update(contact);
    }

    @DeleteMapping("/delete/{email}")
    public void delete(@PathVariable String email) {
        contactService.delete(email);
    }

    @GetMapping("/getAllContacts")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
}
