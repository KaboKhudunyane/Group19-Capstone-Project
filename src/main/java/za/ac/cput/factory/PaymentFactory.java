package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Payment;
import za.ac.cput.util.Helper;

public class PaymentFactory {
    public static Payment buildPayment(String paymentId, Booking bookingInfo, String paymentMethod, String paymentDate, double amountCharged, String paymentStatus){
      if(Helper.isNullOrEmpty(paymentId) || bookingInfo == null|| Helper.isNullOrEmpty(paymentMethod) || Helper.isNullOrEmpty(paymentDate) ||
              Helper.isNullOrEmpty(paymentStatus) || amountCharged < 0)
          return null;

      return new Payment.Builder().setPaymentId(paymentId).setBookingInfo(bookingInfo).setPaymentMethod(paymentMethod).setPaymentDate(paymentDate).setAmountCharged(amountCharged).
              setPaymentStatus(paymentStatus).build();

    }

}
