package za.ac.cput.controller;

import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public String getAddressById(@PathVariable String id) {
        String address = addressService.read(id);
        return address ;
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.create(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress( @RequestBody Address addressDetails) {
        Address updatedAddress = addressService.update(addressDetails);
        return updatedAddress != null ? ResponseEntity.ok(updatedAddress) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String id) {
        boolean isDeleted = addressService.delete(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
