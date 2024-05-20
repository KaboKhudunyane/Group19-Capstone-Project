package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Payment;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void buildPayment(){
        Booking booking = BookingFactory.buildBooking2( "15-June-2024","20-June-2024","10 Hanover street, Cape Town, 8001",
                "10 Hanover street, Cape Town, 8001",  24000);
        assertNotNull(booking);
        Payment payment = PaymentFactory.buildPayment("p101",booking, "Capitec","20-May-2024",12000, "Approved" );
        assertNotNull(payment);
        System.out.println(payment);
    }

}