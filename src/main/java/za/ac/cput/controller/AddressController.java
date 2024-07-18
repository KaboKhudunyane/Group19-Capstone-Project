package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/save")
    public Address save(@RequestBody Address address) {
        return addressService.create(address);
    }

    @GetMapping("/read/{streetName}")
    public Address read(@PathVariable String streetName) {
        return addressService.read(streetName);
    }

    @PutMapping("/update")
    public Address update(@RequestBody Address address) {
        return addressService.update(address);
    }

    @DeleteMapping("/delete/{streetName}")
    public void delete(@PathVariable String streetName) {
        addressService.delete(streetName);
    }

    @GetMapping("/getAll")
    public List<Address> getAll() {
        return addressService.getAll();
    }
}
