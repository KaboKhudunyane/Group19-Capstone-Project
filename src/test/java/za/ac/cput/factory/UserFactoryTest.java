package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
public class UserFactoryTest {
    Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("CityName").setState("StateName").setZipCode("12345").buildAddress();
    @Test
    public void testBuildUser(){
        User user = UserFactory.createUser(name, contact, address, true, "avatar.jpg");
        assertNotNull(user);
        System.out.println(user);
    }
    @Test
    public void testBuildUserWithFail(){
        User user = UserFactory.createUser(name, contact, address, true, "");
        assertNull(user);
        System.out.println(user);
    }
}