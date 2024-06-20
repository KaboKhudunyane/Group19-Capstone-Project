package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.Payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaymentFactoryTest {
    CarInformation carInformation = CarInformationFactory.buildCarInformation("11", "BMW", "M4",
            "2018", "CA123-456", "M performance", "800hps");
    @Test
    void testBuildPayment(){
        Booking booking = BookingFactory.buildBooking("b101", "15-June-2024","20-June-2024","10 Hanover street, Cape Town, 8001",
                "10 Hanover street, Cape Town, 8001",carInformation,"Blue BMW M4(Manual)",  24000);
        assertNotNull(booking);
        System.out.println(booking);
        Payment payment = PaymentFactory.buildPayment("p101",booking, "Capitec","20-May-2024",12000, "Approved" );
        assertNotNull(payment);
        System.out.println(payment);
    }



    @Test
    void testBuildPaymentWithFail(){
        Booking booking = BookingFactory.buildBooking("b101", "15-June-2024","20-June-2024","10 Hanover street, Cape Town, 8001",
                "10 Hanover street, Cape Town, 8001",carInformation,"Blue BMW M4(Manual)",  24000);
        assertNotNull(booking);
        Payment payment = PaymentFactory.buildPayment("p101",booking, "Capitec","20-May-2024",12000, "" );
        assertNotNull(payment);
        System.out.println(payment);
    }


}