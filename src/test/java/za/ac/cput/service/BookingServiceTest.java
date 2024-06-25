package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingServiceTest {
    @Autowired
    private BookingService bookingService;
    private CarInformation carInformation = CarInformationFactory.buildCarInformation("011", "BMW", "M4", "2017", "CA 123-456", "It is an M-performance", "800hps, twin turbo Injector");
    private Booking booking = BookingFactory.buildBooking("b111", "10-June-2024","15-June-2024",
            "11 Lowry Street, Cape Town, 8001", "10 Dorset Street, Cape Town, 8001",carInformation,"Available"

            , 25000);

    @Test
    @Order(1)
    void saveBooking() {
        Booking saved = bookingService.create(booking);
        assertNotNull(saved);
        System.out.println(saved);

    }

    @Test
    @Order(2)
    void readBooking() {
        Booking read = bookingService.read(booking.getBookingId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void updateStatus(){
        Booking editedBooking = new Booking.Builder().copy(booking).setStatus("Unavailable during specified start date").
                build();
        assertNotNull(editedBooking);
        Booking updated = bookingService.update(editedBooking);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Disabled
    void deleteBooking(){
        bookingService.delete(booking.getBookingId());
        System.out.println("Success: deleted booking");

    }


    /*@Test
    @Order(4)
    void getAllBookings() {
        System.out.println(bookingService.getAllBookings());
    }*/

}

