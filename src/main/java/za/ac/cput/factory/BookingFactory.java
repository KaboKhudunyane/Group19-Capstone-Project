package za.ac.cput.factory;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingFactory {
    public static Booking buildBooking(CarInformation car, LocalDate startDate, LocalDate returnDate, LocalTime pickUpTime,LocalTime dropOffTime, double totalPrice) {
        if (car == null || totalPrice < 0)
            return null;

        return new Booking.Builder()
                .setCar(car)
                .setStartDate(startDate)
                .setPickUpTime(pickUpTime)
                .setReturnDate(returnDate)
                .setDropOffTime(dropOffTime)
                .setTotalPrice(totalPrice)
                .buildBooking();
    }

    public static Booking buildBookingTesting(CarInformation car, LocalDate startDate, LocalDate returnDate, LocalTime pickUpTime,LocalTime dropOffTime, double totalPrice) {
        if (car == null || totalPrice < 0)
            return null;

        return new Booking.Builder()
                .setBookingID(Helper.generateID()) // Ensure ID is set
                .setCar(car)
                .setStartDate(startDate)
                .setPickUpTime(pickUpTime)
                .setReturnDate(returnDate)
                .setDropOffTime(dropOffTime)
                .setTotalPrice(totalPrice)
                .buildBooking();
    }
}
