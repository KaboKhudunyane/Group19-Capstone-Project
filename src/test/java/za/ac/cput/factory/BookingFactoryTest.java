package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookingFactoryTest {
    CarInformation carInformation = CarInformationFactory.buildCarInformation("011", "BMW", "M4", "2017", "CA 123-456",
            "It is an M-performance", "800hps");

    Booking booking = BookingFactory.buildBooking("b101", "15-June-2024","20-June-2024","10 Hanover street, Cape Town, 8001",
            "10 Hanover street, Cape Town, 8001",carInformation,"Blue BMW M4(Manual)", 24000);

    @Test
    void testBuildbooking(){

        assertNotNull(carInformation);
        System.out.println(carInformation);

        assertNotNull(booking);
        System.out.println(booking);
    }

    @Test
    void testBuildBookingForPayment(){


        assertNotNull(booking);
        System.out.println(booking);
    }

    @Test
    void testBuildBookingWithFail(){
        assertNotNull(carInformation);
        System.out.println(carInformation);
        assertNotNull(booking);
        System.out.println(booking);
    }

}