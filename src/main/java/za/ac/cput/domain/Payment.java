package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;
@Entity
public class Payment {
    @Id
    private String paymentId;
    @ManyToOne
    private Booking bookingInfo;
    private String paymentMethod;
    private String paymentDate;
    private double amountCharged;
    private String paymentStatus;


   protected Payment(){

   }

   private Payment(Builder builder){
       this.paymentId = builder.paymentId;
       this.bookingInfo = builder.bookingInfo;
       this.paymentMethod = builder.paymentMethod;
       this.paymentDate = builder.paymentDate;
       this.amountCharged = builder.amountCharged;
       this.paymentStatus = builder.paymentStatus;
   }

    public String getPaymentId() {
        return paymentId;
    }

    public Booking getBookingInfo() {
        return bookingInfo;
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
        return Double.compare(amountCharged, payment.amountCharged) == 0 && Objects.equals(paymentId, payment.paymentId) && Objects.equals(bookingInfo, payment.bookingInfo) && Objects.equals(paymentMethod, payment.paymentMethod) && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(paymentStatus, payment.paymentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, bookingInfo, paymentMethod, paymentDate, amountCharged, paymentStatus);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", bookingInfo=" + bookingInfo +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", amountCharged=" + amountCharged +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public static class Builder{
        private String paymentId;
        private String paymentMethod;
        private Booking bookingInfo;
        private String paymentDate;
        private double amountCharged;
        private String paymentStatus;

        public Builder setPaymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setBookingInfo(Booking bookingInfo) {
            this.bookingInfo = bookingInfo;
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
        public Builder copy(Payment payment){
            this.paymentId = payment.paymentId;
            this.bookingInfo = payment.bookingInfo;
            this.paymentMethod = payment.paymentMethod;
            this.paymentDate = payment.paymentDate;
            this.amountCharged = payment.amountCharged;
            this.paymentStatus = payment.paymentStatus;
            return this;
        }

        public Payment build(){
            return new Payment(this);
        }
    }
}
