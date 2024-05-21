package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;

import java.util.List;
@Service
public class AddressService implements IService<Address, String>{
    private AddressRepository addressRepository;
    @Autowired
    AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }
    @Override
    public String read(String addressId) {
        return String.valueOf(addressRepository.findById(addressId));
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }
    @Override
    public void delete(String addressId) {
        addressRepository.deleteById(addressId);
    }
    @Override
    public List<Address> getAll( ){
        return addressRepository.findAll();
    }
}
