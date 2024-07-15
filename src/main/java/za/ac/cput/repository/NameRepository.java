package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Name;

import java.util.List;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {

    // Method to find a Name by first name
    Name findByFirstName(String firstName);

    // Method to delete a Name by first name
    void deleteByFirstName(String firstName);

    // Method to retrieve all names
    List<Name> findAll();
}
