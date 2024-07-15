package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddressFactoryTest {

    // Test case to verify successful Address creation
    @Test
    public void testBuildAddress() {
        Address address = AddressFactory.createAddress("123 Main St", "Springfield",
                "CityName", "StateName", "12345");

        assertNotNull(address);  // Assert that the created Address object is not null
        System.out.println(address);  // Print the created Address object
    }

    // Test case to verify Address creation with a failing condition
    @Test
    public void testBuildAddressWithFail() {
        // Create an address with an empty street name so the factory returns null
        Address address = AddressFactory.createAddress("", "Springfield",
                "CityName", "StateName", "12345");

        assertNull(address);  // Assert that the factory returns null
        System.out.println(address);  // Print the created Address object (should be null)
    }
}
