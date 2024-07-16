package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Payment;

public class PaymentFactory {

    public static Payment buildPayment(Booking booking, String paymentMethod,
                                       String paymentDate, double amountCharged, String paymentStatus) {
        return new Payment.Builder()
                .setBooking(booking)
                .setPaymentMethod(paymentMethod)
                .setPaymentDate(paymentDate)
                .setAmountCharged(amountCharged)
                .setPaymentStatus(paymentStatus)
                .buildPayment();
    }
}
