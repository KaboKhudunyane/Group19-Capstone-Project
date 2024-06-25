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
    AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }
    @Override
    public Address read(String addressID) {
        return addressRepository.findByAddressId(addressID);
    }
    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }
    public void delete(String addressID) {
        addressRepository.deleteByAddressId(addressID);
    }
   /* public List<Address> getAllAddress() {
        return addressRepository.getAllAddress();
    }*/
}