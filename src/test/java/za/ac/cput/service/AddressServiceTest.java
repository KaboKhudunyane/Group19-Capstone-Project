package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddressServiceTest  {

    private AddressRepository addressRepository;
    private AddressService addressService;
    private Address address1, address2;

    @BeforeEach
    void setUp() {
        addressRepository = Mockito.mock(AddressRepository.class);
        addressService = new AddressService(addressRepository);

        address1 = new Address.Builder()
                .setAddressId("A123")
                .setUserId("U456")
                .setStreetNumber(123)
                .setStreetName("Main Street")
                .setSuburb("Downtown")
                .setPostalCode(12345)
                .build();

        address2 = new Address.Builder()
                .setAddressId("A124")
                .setUserId("U457")
                .setStreetNumber(124)
                .setStreetName("Second Street")
                .setSuburb("Uptown")
                .setPostalCode(54321)
                .build();
    }

    @Test
    void create() {
        when(addressRepository.save(any(Address.class))).thenReturn(address1);
        Address created = addressService.create(address1);
        assertNotNull(created);
        assertEquals(address1, created);
        verify(addressRepository, times(1)).save(address1);
    }

    @Test
    void read() {
        when(addressRepository.findById("A123")).thenReturn(Optional.of(address1));
        String found = addressService.read("A123");
        assertNotNull(found);
        assertTrue(found.contains("A123"));
        verify(addressRepository, times(1)).findById("A123");
    }

    @Test
    void update() {
        when(addressRepository.save(any(Address.class))).thenReturn(address1);
        Address updated = addressService.update(address1);
        assertNotNull(updated);
        assertEquals(address1, updated);
        verify(addressRepository, times(1)).save(address1);
    }

    @Test
    void delete() {
        doNothing().when(addressRepository).deleteById("A123");
        addressService.delete("A123");
        verify(addressRepository, times(1)).deleteById("A123");
    }

    @Test
    void getAll() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));
        List<Address> addresses = addressService.getAll();
        assertNotNull(addresses);
        assertEquals(2, addresses.size());
        verify(addressRepository, times(1)).findAll();
    }
}