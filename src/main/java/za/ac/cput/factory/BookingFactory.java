package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.util.Helper;

public class BookingFactory {
    public static Booking buildBooking(String bookingId, String startDate, String returnDate, String pickUpLocation, String dropOffLocation, CarInformation carInformation, String status, double totalPrice){
        if(Helper.isNullOrEmpty(bookingId) || Helper.isNullOrEmpty(startDate) || Helper.isNullOrEmpty(returnDate) || Helper.isNullOrEmpty(status) || carInformation == null
                || Helper.isNullOrEmpty(pickUpLocation) || Helper.isNullOrEmpty(dropOffLocation)|| totalPrice < 0 )
            return null;

        return new Booking.Builder().setBookingId(bookingId).setStartDate(startDate).setReturnDate(returnDate).setPickUpLocation(pickUpLocation).setDropOffLocation(dropOffLocation).setCarInformation(carInformation)
                .setStatus(status).setTotalPrice(totalPrice)
                .build();

    }
    /*public static Booking buildBooking( String bookingId, String startDate, String returnDate, String pickUpLocation,String dropOffLocation, String description, double totalPrice){
        if(Helper.isNullOrEmpty(bookingId) || Helper.isNullOrEmpty(startDate) || Helper.isNullOrEmpty(returnDate)
                || Helper.isNullOrEmpty(pickUpLocation) || Helper.isNullOrEmpty(dropOffLocation)|| Helper.isNullOrEmpty(description) ||
                totalPrice < 0 )
            return null;

        //CarInformation carInformation = CarInformationFactory.buildCarInformation(carInformationId,description);

        return new Booking.Builder().setBookingId(bookingId).setStartDate(startDate).setReturnDate(returnDate).setPickUpLocation(pickUpLocation).setDropOffLocation(dropOffLocation).setCarInformation(carInformation)
                .setTotalPrice(totalPrice)
                .build();




    }*/
}
