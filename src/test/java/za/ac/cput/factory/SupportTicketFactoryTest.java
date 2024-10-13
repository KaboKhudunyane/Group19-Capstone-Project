package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SupportTicketFactoryTest {

    // Setup the necessary objects for testing
    private Account account = new Account.Builder().setUsername("Username").setPassword("password").buildAccount();
    private Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    private Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    private Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("CityName").setProvince("Western Cape").setZipCode("12345").buildAddress();
    private static final String LICENSE_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";
    private static final String ID_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG2.PNG";

    private byte[] compressImage(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
            return null;
        }
    }
    private byte[] licensePicture;
    private byte[] idPicture;
    // Creating a User instance for testing purposes
    User user = UserFactory.createUser(account, name, contact, address, licensePicture, idPicture, User.Role.USER); // Set the role here

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
