package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarFactoryTest {
    // Create a Name object for testing
    Name name = new Name.Builder()
            .setFirstName("John")
            .setMiddleName("Fred")
            .setLastName("Doe")
            .buildName();

    // Create a Contact object for testing
    Contact contact = new Contact.Builder()
            .setContactId("1")
            .setEmail("john@example.com")
            .setMobileNo(123456789)
            .buildContact();

    // Create an Address object for testing
    Address address = new Address.Builder()
            .setAddressId("1")
            .setUserId("123")
            .setStreetName("123 Main St")
            .setSuburb("Springfield")
            .setPostalCode(12345)
            .buildAddress();

    User user= new User.Builder().setUserID("fhf").setName(name).setContact(contact).setAddress(address)
            .setLicense(true).setRole("admin").setPicture("pict").setVerified(true).buildUser();

    // Create a CarInformation object for testing
    CarInformation carInformation = new CarInformation.Builder()
            .setCarInformationId("1")
            .setMake("Toyota")
            .setModel("Corolla")
            .setYear("2023")
            .setLicensePlate("ABC123")
            .setDescription("New Toyota Corolla")
            .setFeatures("Bluetooth, Backup Camera, Navigation System")
            .buildCarInformation();

    // Create a car with valid parameters
    Car car = CarFactory.createCar("123",
            carInformation, "150",
            "Available", "New");
    @Test
    public void testBuildCar(){

        assertNotNull(car);  // Assert that the created Car object is not null
        System.out.println(car);  // Print the created Car object
    }

    @Test
    public void testBuildCarWithFail(){
        // Create a car with all null parameters so the factory returns null
        Car car = CarFactory.createCar( null, null, null, null, null);
        assertNotNull(car);  // This will fail because the factory should return null
        System.out.println(car);  // Print the created Car object (should be null)
    }
}
