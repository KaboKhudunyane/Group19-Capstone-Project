package za.ac.cput.service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.PaymentFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;
    private static final String CAR_PICTURE_PATH = "path/to/your/car/picture.jpg";
    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    byte[] carPicture = readFileAsBytes(CAR_PICTURE_PATH);
    Car car = new Car.Builder()
            .setCarInformation(
                    new CarInformation.Builder()
                            .setMake("Toyota")
                            .setModel("Corolla")
                            .setYear("2023")
                            .setLicensePlate("ABC123")
                            .setDescription("New Toyota Corolla")
                            .setFeatures("Bluetooth, Backup Camera, Navigation System")
                            .buildCarInformation())
            .setCarInsurance(
                    new CarInsurance.Builder()
                            .setInsuranceCompany("Insurance Co.")
                            .setPolicyNumber("12345")
                            .setCoverageType("Comprehensive")
                            .setCoverageAmount("100000")
                            .buildCarInsurance())
            .setRentalRate("150")
            .setAvailabilityStatus("Available")
            .setCarPicture(carPicture) // Provide appropriate car picture data here
            .buildCar();
    Booking booking = BookingFactory.buildBooking("b101", car, "15-June-2024", "20-June-2024",
            "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
            24000);
    private Payment payment = PaymentFactory.buildPayment(booking,"Capitec", "20-May-2024", 18000,"Declined");
    @Test
    void savePayment(){
    }
    @Test
    void readPayment(){
    }
    @Test
    void updatePayment(){
    }

    @Test
    void deletePayment(){

    }
    @Test
    void getAllPayments(){
    }
}