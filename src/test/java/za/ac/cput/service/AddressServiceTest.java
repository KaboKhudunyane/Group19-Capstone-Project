package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    private static Address address = AddressFactory.createAddress("123 Main St", "Suburbia", "Cityville", "State", "12345");

    @Test
    void create() {
        Address createdAddress = addressService.create(address);
        assertNotNull(createdAddress);
        System.out.println("Created Address: " + createdAddress);
    }

    @Test
    void read() {
        Address readAddress = addressService.read(address.getStreetName());
        assertNotNull(readAddress);
        System.out.println("Read Address: " + readAddress);
    }

    @Test
    void update() {
        Address newAddress = new Address.Builder()
                .copyAddress(address)
                .setCity("UpdatedCity")
                .buildAddress();
        Address updatedAddress = addressService.update(newAddress);
        assertNotNull(updatedAddress);
        System.out.println("Updated Address: " + updatedAddress);
    }

    @Test
    void delete() {
        addressService.delete(address.getStreetName());
        Address deletedAddress = addressService.read(address.getStreetName());
        assertNull(deletedAddress);
        System.out.println("Address deleted successfully.");
    }
}
