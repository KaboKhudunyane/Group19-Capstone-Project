package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;

import static org.junit.jupiter.api.Assertions.*;

class BookingFactoryTest {

    @Test
    void buildbooking(){
        CarInformation carInformation = CarInformationFactory.buildCarInformation("011", "BMW", "M4", "2017", "CA 123-456", "It is an M-performance", "800hps, twin turbo Injector");
        assertNotNull(carInformation);
        System.out.println(carInformation);
        Booking booking1 = BookingFactory.buildBooking1("b101", "15-June-2024","20-June-2024","10 Hanover street, Cape Town, 8001",
                "10 Hanover street, Cape Town, 8001",carInformation, "Pending", 24000);
        assertNotNull(booking1);
        System.out.println(booking1);
    }

}