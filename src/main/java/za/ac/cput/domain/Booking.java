package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String bookingId;
    @OneToOne
    private Car car;
    private String startDate;
    private String returnDate;
    private String pickUpLocation;
    private String dropOffLocation;

    private String status;

    private double totalPrice;

    protected Booking(){

    }

    private Booking(Builder builder){
        this.bookingId = builder.bookingId;
        this.car = builder.car;
        this.startDate = builder.startDate;
        this.returnDate = builder.returnDate;
        this.pickUpLocation= builder.pickUpLocation;
        this.dropOffLocation = builder.dropOffLocation;
        this.status = builder.status;
        this.totalPrice = builder.totalPrice;

    }

    public String getBookingId() {
        return bookingId;
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

    public Car getCar() {
        return car;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Double.compare(totalPrice, booking.totalPrice) == 0 && Objects.equals(bookingId, booking.bookingId) && Objects.equals(car, booking.car) && Objects.equals(startDate, booking.startDate) && Objects.equals(returnDate, booking.returnDate) && Objects.equals(pickUpLocation, booking.pickUpLocation) && Objects.equals(dropOffLocation, booking.dropOffLocation) && Objects.equals(status, booking.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, car, startDate, returnDate, pickUpLocation, dropOffLocation, status, totalPrice);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", carInformation=" + car +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private String bookingId;
        private Car car;
        private String startDate;
        private String returnDate;

        private String pickUpLocation;

        private String dropOffLocation;

        private String status;
        private double totalPrice;

        public Builder setBookingId(String bookingId) {
            this.bookingId = bookingId;
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

        public Builder setCar(Car car) {
            this.car = car;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
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
            this.pickUpLocation= booking.pickUpLocation;
            this.dropOffLocation = booking.dropOffLocation;
            this.status = booking.status;
            this.totalPrice = booking.totalPrice;

            return this;
        }

        public Booking build(){
            return new Booking(this);
        }
    }

}
