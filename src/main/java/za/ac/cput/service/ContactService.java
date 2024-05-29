package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.repository.ContactRepository;

import java.util.List;

@Service
public class ContactService implements IService<Contact,String>{
    
    @Autowired
    private ContactRepository contactRepository;

    ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }
    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact read(String contactID) {
        return  contactRepository.findByContactID(contactID);
    }
    @Override
    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    public void delete(String contactID) {
        contactRepository.deleteByContactID(contactID);
    }
    public List<Contact> getAll() {
        return contactRepository.getAllContacts();
    }
}
