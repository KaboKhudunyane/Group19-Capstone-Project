package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.User;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.repository.UserRepository;
import java.util.List;

@Service
public class AddressService implements IService<Address, String> {
    private final AddressRepository addressRepository;

    @Autowired
    AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    // Method to create a new user
    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }
    // Method to read a user by their userID
    @Override
    public Address read(String addressID) {
        return addressRepository.findByAddressId(addressID);
    }
    // Method to update an existing user
    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }
    // Method to delete a user by their userID
    public void delete(String addressID) {
        addressRepository.deleteByAddressId(addressID);
    }
    // Method to get all users
    public List<Address> getAllAddress() {
        return addressRepository.getAllAddress();
    }
}