package za.ac.cput.domain;
import jakarta.persistence.*;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    private String bookingID = Helper.generateID();
    @ManyToOne
    private CarInformation car;
    private LocalDate startDate;
    private LocalTime pickUpTime;
    private LocalDate returnDate;
    private LocalTime dropOffTime;
    private double totalPrice;

    protected Booking() {}

    private Booking(Builder builder) {
        this.bookingID = builder.bookingID;
        this.car = builder.car;
        this.startDate = builder.startDate;
        this.returnDate = builder.returnDate;
        this.pickUpTime = builder.pickUpTime;
        this.dropOffTime = builder.dropOffTime;
        this.totalPrice = builder.totalPrice;
    }

    public String getBookingID() {
        return bookingID;
    }

    public CarInformation getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public LocalTime getDropOffTime() {
        return dropOffTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Double.compare(totalPrice, booking.totalPrice) == 0 && Objects.equals(bookingID, booking.bookingID) && Objects.equals(car, booking.car) && Objects.equals(startDate, booking.startDate) && Objects.equals(pickUpTime, booking.pickUpTime) && Objects.equals(returnDate, booking.returnDate) && Objects.equals(dropOffTime, booking.dropOffTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingID, car, startDate, pickUpTime, returnDate, dropOffTime, totalPrice);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", car=" + car +
                ", startDate=" + startDate +
                ", pickUpTime=" + pickUpTime +
                ", returnDate=" + returnDate +
                ", dropOffTime=" + dropOffTime +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private String bookingID;
        private CarInformation car;
        private LocalDate startDate;
        private LocalTime pickUpTime;
        private LocalDate returnDate;
        private LocalTime dropOffTime;
        private double totalPrice;

        public Builder setBookingID(String bookingID) {
            this.bookingID = bookingID;
            return this;
        }

        public Builder setCar(CarInformation car) {
            this.car = car;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setReturnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder setPickUpTime(LocalTime pickUpTime) {
            this.pickUpTime = pickUpTime;
            return this;

        }

        public Builder setDropOffTime(LocalTime dropOffTime) {
            this.dropOffTime = dropOffTime;
            return this;

        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder copy(Booking booking) {
            this.bookingID = booking.bookingID;
            this.car = booking.car;
            this.startDate = booking.startDate;
            this.returnDate = booking.returnDate;
            this.pickUpTime = booking.pickUpTime;
            this.dropOffTime = booking.dropOffTime;
            this.totalPrice = booking.totalPrice;
            return this;
        }

        public Booking buildBooking() {
            return new Booking(this);
        }
    }
}
