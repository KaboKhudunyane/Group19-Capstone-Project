package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID; // Changed to Long for auto-generated ID
    @ManyToOne
    private Booking booking;
    private String paymentMethod;
    private String paymentDate;
    private double amountCharged;
    private String paymentStatus;

    protected Payment() {}

    private Payment(Builder builder) {
        this.booking = builder.booking;
        this.paymentMethod = builder.paymentMethod;
        this.paymentDate = builder.paymentDate;
        this.amountCharged = builder.amountCharged;
        this.paymentStatus = builder.paymentStatus;
    }

    public Long getPaymentID() {
        return paymentID;
    }

    public Booking getBooking() {
        return booking;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public double getAmountCharged() {
        return amountCharged;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.amountCharged, amountCharged) == 0 &&
                Objects.equals(paymentID, payment.paymentID) &&
                Objects.equals(booking, payment.booking) &&
                Objects.equals(paymentMethod, payment.paymentMethod) &&
                Objects.equals(paymentDate, payment.paymentDate) &&
                Objects.equals(paymentStatus, payment.paymentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentID, booking, paymentMethod, paymentDate, amountCharged, paymentStatus);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", booking=" + booking +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", amountCharged=" + amountCharged +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public static class Builder {
        private Booking booking;
        private String paymentMethod;
        private String paymentDate;
        private double amountCharged;
        private String paymentStatus;

        public Builder setBooking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setAmountCharged(double amountCharged) {
            this.amountCharged = amountCharged;
            return this;
        }

        public Builder setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder copyPayment(Payment payment) {
            this.booking = payment.booking;
            this.paymentMethod = payment.paymentMethod;
            this.paymentDate = payment.paymentDate;
            this.amountCharged = payment.amountCharged;
            this.paymentStatus = payment.paymentStatus;
            return this;
        }

        public Payment buildPayment() {
            return new Payment(this);
        }
    }
}
