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
    private LocalDate createdDate = LocalDate.now();

    @ManyToOne
    private CarInformation car;

    @ManyToOne
    private User user;

    private LocalDate startDate;
    private LocalTime pickUpTime;
    private LocalDate returnDate;
    private LocalTime dropOffTime;
    private double totalPrice;

    protected Booking() {}

    private Booking(Builder builder) {
        this.bookingID = builder.bookingID;
        this.car = builder.car;
        this.user = builder.user;
        this.startDate = builder.startDate;
        this.returnDate = builder.returnDate;
        this.pickUpTime = builder.pickUpTime;
        this.dropOffTime = builder.dropOffTime;
        this.totalPrice = builder.totalPrice;
        this.createdDate = builder.createdDate;
    }

    public String getBookingID() {
        return bookingID;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public CarInformation getCar() {
        return car;
    }

    public User getUser() {
        return user;
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
        return Double.compare(getTotalPrice(), booking.getTotalPrice()) == 0 && Objects.equals(getBookingID(), booking.getBookingID()) && Objects.equals(getCreatedDate(), booking.getCreatedDate()) && Objects.equals(getCar(), booking.getCar()) && Objects.equals(getUser(), booking.getUser()) && Objects.equals(getStartDate(), booking.getStartDate()) && Objects.equals(getPickUpTime(), booking.getPickUpTime()) && Objects.equals(getReturnDate(), booking.getReturnDate()) && Objects.equals(getDropOffTime(), booking.getDropOffTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookingID(), getCreatedDate(), getCar(), getUser(), getStartDate(), getPickUpTime(), getReturnDate(), getDropOffTime(), getTotalPrice());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", createdDate=" + createdDate +
                ", car=" + car +
                ", user=" + user +
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
        private User user;
        private LocalDate startDate;
        private LocalTime pickUpTime;
        private LocalDate returnDate;
        private LocalTime dropOffTime;
        private double totalPrice;
        private LocalDate createdDate;

        public Builder setBookingID(String bookingID) {
            this.bookingID = bookingID;
            return this;
        }

        public Builder setCar(CarInformation car) {
            this.car = car;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
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

        public Builder setCreatedDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder copy(Booking booking) {
            this.bookingID = booking.bookingID;
            this.car = booking.car;
            this.user = booking.user;
            this.startDate = booking.startDate;
            this.returnDate = booking.returnDate;
            this.pickUpTime = booking.pickUpTime;
            this.dropOffTime = booking.dropOffTime;
            this.totalPrice = booking.totalPrice;
            this.createdDate = booking.createdDate;
            return this;
        }

        public Booking buildBooking() {
            return new Booking(this);
        }
    }
}
