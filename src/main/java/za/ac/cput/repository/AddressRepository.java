package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Method to find an Address by street name
    Address findByStreetName(String streetName);

    // Method to delete an Address by street name
    void deleteByStreetName(String streetName);

    // Method to retrieve all addresses
    List<Address> findAll();
}
