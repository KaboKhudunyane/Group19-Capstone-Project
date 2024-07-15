package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService implements IService<Address, String> {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address read(String id) {
        // Since streetName is the primary key, find by streetName
        return addressRepository.findByStreetName(id);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    public void delete(String id) {
        // Since streetName is the primary key, delete by streetName
        addressRepository.deleteByStreetName(id);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
}
