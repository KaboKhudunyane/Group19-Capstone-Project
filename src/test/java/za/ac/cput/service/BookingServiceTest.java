package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.util.Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CarInformationService carInformationService;

    private static String bookingID;

    private Booking booking;

    @BeforeEach
    void setUp() {
        CarInformation carInformation = carInformationService.read(1L);
        booking = BookingFactory.buildBookingTesting(
                carInformation,
                LocalDate.of(2024, 6, 15),
                LocalDate.of(2024, 6, 20),
                LocalTime.of(10, 0),
                LocalTime.of(10, 0),
                12000
        );
    }





    @Test
    @Order(1)
    void create() {
        Booking created = bookingService.create(booking);
        assertNotNull(created);
        bookingID = created.getBookingID();
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        Booking read = bookingService.read(bookingID);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(bookingService.getAll());
    }
}
