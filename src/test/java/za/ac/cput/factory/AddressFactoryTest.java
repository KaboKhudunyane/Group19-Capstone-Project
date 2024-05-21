package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {


    @Test
    void createAddress_nullAddressId() {
        Address address = AddressFactory.createAddress(null, "U456", "Main Street", "Downtown", 12345);
        assertNull(address, "Address creation with null addressId should return null");
    }

    @Test
    void createAddress_emptyAddressId() {
        Address address = AddressFactory.createAddress("", "U456", "Main Street", "Downtown", 12345);
        assertNull(address, "Address creation with empty addressId should return null");
    }

    @Test
    void createAddress_nullUserId() {
        Address address = AddressFactory.createAddress("A123", null, "Main Street", "Downtown", 12345);
        assertNull(address, "Address creation with null userId should return null");
    }

    @Test
    void createAddress_emptyUserId() {
        Address address = AddressFactory.createAddress("A123", "", "Main Street", "Downtown", 12345);
        assertNull(address, "Address creation with empty userId should return null");
    }

    @Test
    void createAddress_emptyStreetName() {
        Address address = AddressFactory.createAddress("A123", "U456", "", "Downtown", 12345);
        assertNull(address, "Address creation with empty streetName should return null");
    }




}