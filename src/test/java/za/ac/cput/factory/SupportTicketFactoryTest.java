package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import za.ac.cput.enums.UserRole;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class SupportTicketFactoryTest {

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

    Booking booking = BookingFactory.buildBooking(
            carInformation,
            LocalDate.of(2024, 6, 15),
            LocalDate.of(2024, 6, 20),
            LocalTime.of(10, 0),
            LocalTime.of(10, 0),
            12000
    );

    // Test case for successful creation of a SupportTicket
    @Test
    void buildSupportTicket() {
        LocalDate dateCreated = LocalDate.of(2024, 4, 3); // Set the creation date for the support ticket

        // Attempt to build a SupportTicket using the factory
        SupportTicket supportTicket = SupportTicketFactory.buildSupportTicket(user, "Technical Support", "I am facing login issues.", dateCreated);

        // Assertions to verify that the support ticket was created successfully
        assertNotNull(supportTicket); // Check that the support ticket is not null
        assertEquals(user, supportTicket.getUser()); // Verify the user associated with the ticket
        assertEquals("Technical Support", supportTicket.getSubject()); // Verify the subject of the ticket
        assertEquals("I am facing login issues.", supportTicket.getMessage()); // Verify the message in the ticket
        assertEquals(dateCreated, supportTicket.getDateCreated()); // Verify the creation date of the ticket
    }

    // Test case to verify failure when subject is empty
    @Test
    void testBuildSupportTicketWithFail() {
        LocalDate dateCreated = LocalDate.of(2024, 4, 3); // Set the creation date for the support ticket

        // Attempt to build a SupportTicket with an empty subject
        SupportTicket supportTicket = SupportTicketFactory.buildSupportTicket(user, "", "I am facing login issues.", dateCreated);

        // Assertions to check that the support ticket is null when subject is empty
        assertNull(supportTicket); // The ticket should not be created and thus should be null
        System.out.println(supportTicket); // Print the support ticket, which should be null
    }
}
