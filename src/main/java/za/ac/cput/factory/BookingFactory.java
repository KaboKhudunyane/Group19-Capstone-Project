package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.util.Helper;

public class BookingFactory {
    public static Booking buildBooking1(String bookingId, String startDate, String returnDate, String pickUpLocation, String dropOffLocation, CarInformation carInformation, String status, double totalPrice){
        if(Helper.isNullOrEmpty(bookingId) || Helper.isNullOrEmpty(startDate) || Helper.isNullOrEmpty(returnDate) || Helper.isNullOrEmpty(status) || carInformation == null
         || Helper.isNullOrEmpty(pickUpLocation) || Helper.isNullOrEmpty(dropOffLocation)|| totalPrice < 0 )
            return null;

        return new Booking.Builder().setBookingId(bookingId).setStartDate(startDate).setReturnDate(returnDate).setPickUpLocation(pickUpLocation).setDropOffLocation(dropOffLocation).setCarInformation(carInformation)
                .setStatus(status).setTotalPrice(totalPrice)
                .build();

    }
    public static Booking buildBooking2( String startDate, String returnDate, String pickUpLocation,String dropOffLocation, double totalPrice){
        if(Helper.isNullOrEmpty(startDate) || Helper.isNullOrEmpty(returnDate)
                || Helper.isNullOrEmpty(pickUpLocation) || Helper.isNullOrEmpty(dropOffLocation)|| totalPrice < 0 )
            return null;

        return new Booking.Builder().setStartDate(startDate).setReturnDate(returnDate).setPickUpLocation(pickUpLocation).setDropOffLocation(dropOffLocation).
        setTotalPrice(totalPrice)
                .build();

    }
}
