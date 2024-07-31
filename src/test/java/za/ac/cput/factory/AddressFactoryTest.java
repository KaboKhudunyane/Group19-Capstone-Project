package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
public class AddressFactoryTest {
    @Test
    public void testBuildAddress() {
        Address address = AddressFactory.createAddress("123 Main St", "Springfield",
                "CityName", "Western Cape", "12345");
        assertNotNull(address);
        System.out.println(address);
    }
    @Test
    public void testBuildAddressWithFail() {
        Address address = AddressFactory.createAddress("", "Springfield",
                "CityName", "Western Cape", "12345");
        assertNull(address);
        System.out.println(address);
    }
}