package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CarInformationFactoryTest {

    Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("Cape Town").setProvince("Western Cape").setZipCode("12345").buildAddress();
    User user = UserFactory.createUser(User.Role.ROLE_USER,"username","password",  name, contact, address, loadPicture("lisence.jpg"), loadPicture("identity.jpg"));
        CarInformation carInformation = CarInformationFactory.buildCarInformation(
                "Toyota", "Scarlet", "2020", "Manual", "Plate-123",
                "Red 5 door car with 50 000km mileage", "Leather seats, Navigation system, Bluetooth", user,
                2000, "Available",
                loadPicture("scarlet1.jpg"),
                loadPicture("scarlet2.jpg"),
                loadPicture("scarlet3.jpg")
        );


    private byte[] loadPicture(String fileName) {
        try {
            Path path = Paths.get("src/images/img-prototype/" + fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }


    @Test
    public void testBuildCar() {
        assertNotNull(carInformation);
        System.out.println("Created car: " + carInformation);
    }

    @Test
    public void testBuildCarWithFail() {
        assertNull(carInformation); // Adjust this as needed if you want to test for failure
        System.out.println("Created car: " + carInformation);
    }
}


