package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import za.ac.cput.enums.UserRole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CarInformationFactoryTest {

    Address address = AddressFactory.createAddress("123 Main St", "Springfield",
            "CityName", "Western Cape", "12345");

    User user = UserFactory.createUser("John", "Doe", "johndoe", "password123", UserRole.USER,
            "123456789", "john@example.com", address,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));
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


