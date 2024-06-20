package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AddressServiceTest  {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;
    private Address address1, address2;

    @BeforeEach
    void setUp() {
        address1 = new Address.Builder()
                .setAddressId("A123")
                .setUserId("U456")
                .setStreetNumber(123)
                .setStreetName("Main Street")
                .setSuburb("Downtown")
                .setPostalCode(12345)
                .buildAddress();

        address2 = new Address.Builder()
                .setAddressId("A124")
                .setUserId("U457")
                .setStreetNumber(124)
                .setStreetName("Second Street")
                .setSuburb("Uptown")
                .setPostalCode(54321)
                .buildAddress();
    }

    @Test
    void create() {
        Address created = addressService.create(address1);
        assertNotNull(created);
        assertEquals(address1, created);
    }

    @Test
    void read() {
        Address found = addressService.read(address1.getAddressId());
        assertNotNull(found);
        assertTrue(found.getAddressId().equals(address1.getAddressId()));
    }

    @Test
    void update() {
        Address updated = addressService.update(address1);
        assertNotNull(updated);
        assertEquals(address1, updated);
    }

    @Test
    void delete() {
        addressService.delete("A123");
    }

    @Test
    void getAll() {
        addressService.create(address1);
        addressService.create(address2);
        List<Address> addresses = addressService.getAllAddress();
        assertNotNull(addresses);
        assertEquals(2, addresses.size());
    }
}
