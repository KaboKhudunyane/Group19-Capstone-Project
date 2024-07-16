package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Car;
import za.ac.cput.util.Helper;

public class BookingFactory {

    public static Booking buildBooking(String bookingId, Car car, String startDate, String returnDate, String pickUpLocation, String dropOffLocation, double totalPrice) {
        if (Helper.isNullOrEmpty(bookingId) || car == null || Helper.isNullOrEmpty(startDate) || Helper.isNullOrEmpty(returnDate)
                || Helper.isNullOrEmpty(pickUpLocation) || Helper.isNullOrEmpty(dropOffLocation) || totalPrice < 0) {
            return null;
        }

        return new Booking.Builder()
                .setBookingId(bookingId)
                .setCar(car) // Set the entire Car object directly
                .setStartDate(startDate)
                .setReturnDate(returnDate)
                .setPickUpLocation(pickUpLocation)
                .setDropOffLocation(dropOffLocation)
                .setTotalPrice(totalPrice)
                .buildBooking();
    }
}
