package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.User;
import za.ac.cput.enums.UserRole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CarInsuranceFactoryTest {
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
    void buildCarInsurance() {
        CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance(
                "MiWay", 15447841, "Insurance", 1200, user, carInformation
        );
        assertNotNull(carInsurance);
        System.out.println(carInsurance);
    }
    @Test
    void testBuildCarInsuranceWithFail() {
        CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance(
                "MiWay", 15447841, "", 1200, user, carInformation
        );
        assertNull(carInsurance);
        System.out.println(carInsurance);
    }
}
