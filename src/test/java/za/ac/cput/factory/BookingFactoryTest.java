package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookingFactoryTest {
    @Test
    void testBuildbooking(){
        CarInformation carInformation = CarInformationFactory.buildCarInformation("011", "BMW", "M4", "2017", "CA 123-456", "It is an M-performance", "800hps, twin turbo Injector","1234");
        assertNotNull(carInformation);
        System.out.println(carInformation);
        Booking booking1 = BookingFactory.buildBooking("b101", "15-June-2024","20-June-2024","10 Hanover street, Cape Town, 8001",
                "10 Hanover street, Cape Town, 8001",carInformation, "Pending", 24000);
        assertNotNull(booking1);
        System.out.println(booking1);
    }

    @Test
    void testBuildBookingForPayment(){
        Booking booking = BookingFactory.buildBooking("b101", "15-June-2024","20-June-2024","10 Hanover street, Cape Town, 8001",
                "10 Hanover street, Cape Town, 8001","010","Blue BMW M4(Manual)", 24000);
        assertNotNull(booking);
        System.out.println(booking);
    }

    @Test
    void testBuildBookingWithFail(){
        CarInformation carInformation = CarInformationFactory.buildCarInformation("011", "BMW", "M4", "2017", "CA 123-456", "It is an M-performance", "800hps, twin turbo Injector","1234");
        assertNotNull(carInformation);
        System.out.println(carInformation);
        Booking booking1 = BookingFactory.buildBooking("b101", "15-June-2024","20-June-2024","10 Hanover street, Cape Town, 8001",
                "10 Hanover street, Cape Town, 8001",carInformation, "Pending", 0);
        assertNotNull(booking1);
        System.out.println(booking1);
    }

}