package za.ac.cput.domain;
import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String bookingId;
    @ManyToOne
    private Car car; // Reference to Car
    private String startDate;
    private String returnDate;
    private String pickUpLocation;
    private String dropOffLocation;
    private double totalPrice;
    protected Booking() {
    }
    private Booking(Builder builder) {
        this.bookingId = builder.bookingId;
        this.car = builder.car; // Set Car instance
        this.startDate = builder.startDate;
        this.returnDate = builder.returnDate;
        this.pickUpLocation = builder.pickUpLocation;
        this.dropOffLocation = builder.dropOffLocation;
        this.totalPrice = builder.totalPrice;
    }
    public String getBookingId() {
        return bookingId;
    }
    public Car getCar() {
        return car;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getReturnDate() {
        return returnDate;
    }
    public String getPickUpLocation() {
        return pickUpLocation;
    }
    public String getDropOffLocation() {
        return dropOffLocation;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return Double.compare(booking.totalPrice, totalPrice) == 0 &&
                Objects.equals(bookingId, booking.bookingId) &&
                Objects.equals(startDate, booking.startDate) &&
                Objects.equals(returnDate, booking.returnDate) &&
                Objects.equals(pickUpLocation, booking.pickUpLocation) &&
                Objects.equals(dropOffLocation, booking.dropOffLocation) &&
                Objects.equals(car, booking.car);
    }
    @Override
    public int hashCode() {
        return Objects.hash(bookingId, car, startDate, returnDate, pickUpLocation, dropOffLocation, totalPrice);
    }
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", car=" + car +
                ", startDate='" + startDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
    public static class Builder {
        private String bookingId;
        private Car car; // Reference to Car
        private String startDate;
        private String returnDate;
        private String pickUpLocation;
        private String dropOffLocation;
        private double totalPrice;
        public Builder setBookingId(String bookingId) {
            this.bookingId = bookingId;
            return this;
        }
        public Builder setCar(Car car) {
            this.car = car; // Set Car instance
            return this;
        }
        public Builder setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }
        public Builder setReturnDate(String returnDate) {
            this.returnDate = returnDate;
            return this;
        }
        public Builder setPickUpLocation(String pickUpLocation) {
            this.pickUpLocation = pickUpLocation;
            return this;
        }
        public Builder setDropOffLocation(String dropOffLocation) {
            this.dropOffLocation = dropOffLocation;
            return this;
        }
        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }
        public Builder copy(Booking booking){
            this.bookingId = booking.bookingId;
            this.car = booking.car;
            this.startDate = booking.startDate;
            this.returnDate = booking.returnDate;
            this.pickUpLocation = booking.pickUpLocation;
            this.dropOffLocation = booking.dropOffLocation;
            this.totalPrice = booking.totalPrice;
            return this;
        }
        public Booking buildBooking() {
            return new Booking(this);
        }
    }
}
