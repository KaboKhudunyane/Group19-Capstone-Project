package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Method to find a Contact by email
    Contact findByEmail(String email);

    // Method to delete a Contact by email
    void deleteByEmail(String email);

    // Method to retrieve all contacts
    List<Contact> findAll();
}
