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
    public Address save(@RequestBody Address address){
        return addressService.create(address);
    }

    @GetMapping("/read/{addressId}")
    public Address read(@PathVariable String addressID){
        return addressService.read(addressID);
    }

    @PutMapping("/update")
    public Address update(@RequestBody Address address){
        return addressService.update(address);
    }

    @DeleteMapping("/delete/{addressId}")
    public void delete (@PathVariable String addressId){
        addressService.delete(addressId);
    }

    @GetMapping("/getAll")
    public List<Address> getAllAddress(){
        return addressService.getAllAddress();
    }
}
