package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Contact;
import za.ac.cput.repository.ContactRepository;

import java.util.List;

@Service
public class ContactService implements IService<Contact, String> {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact read(String id) {
        // Since email is the primary key, find by email
        return contactRepository.findByEmail(id);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    public void delete(String id) {
        // Since email is the primary key, delete by email
        contactRepository.deleteByEmail(id);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
