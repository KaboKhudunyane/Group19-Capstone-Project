package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    @Embedded
    private CarInformation carInformation;
    @Embedded
    private CarInsurance carInsurance;
    private String rentalRate;
    private String availabilityStatus;
    @Lob
    private byte[] carPicture;
    protected Car() {
    }
    private Car(Builder builder) {
        this.carId = builder.carId;
        this.carInformation = builder.carInformation;
        this.carInsurance = builder.carInsurance;
        this.rentalRate = builder.rentalRate;
        this.availabilityStatus = builder.availabilityStatus;
        this.carPicture = builder.carPicture;
    }
    // Getters
    public Long getCarId() {
        return carId;
    }

    public CarInformation getCarInformation() {
        return carInformation;
    }
    public CarInsurance getCarInsurance() {
        return carInsurance;
    }

    public byte[] getCarPicture() {
        return carPicture;
    }

    public String getRentalRate() {
        return rentalRate;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) &&
                Objects.equals(carInformation, car.carInformation) &&
                Objects.equals(carInsurance, car.carInsurance) &&
                Objects.equals(rentalRate, car.rentalRate) &&
                Objects.equals(availabilityStatus, car.availabilityStatus) &&
                Arrays.equals(carPicture, car.carPicture);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(carId, carInformation, rentalRate, availabilityStatus);
        result = 31 * result + Arrays.hashCode(carPicture);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carInformation=" + carInformation +
                ", carInsurance=" + carInsurance +
                ", rentalRate='" + rentalRate + '\'' +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                '}';
    }
    public static class Builder {
        private Long carId;
        private CarInformation carInformation;
        private CarInsurance carInsurance;
        private String rentalRate;
        private String availabilityStatus;
        private byte[] carPicture;
        public Builder setCarId(Long carId) {
            this.carId = carId;
            return this;
        }

        public Builder setCarInformation(CarInformation carInformation) {
            this.carInformation = carInformation;
            return this;
        }

        public Builder setCarInsurance(CarInsurance carInsurance) {
            this.carInsurance = carInsurance;
            return this;
        }

        public Builder setRentalRate(String rentalRate) {
            this.rentalRate = rentalRate;
            return this;
        }

        public Builder setAvailabilityStatus(String availabilityStatus) {
            this.availabilityStatus = availabilityStatus;
            return this;
        }

        public Builder setCarPicture(byte[] carPicture) {
            this.carPicture = carPicture;
            return this;
        }

        public Builder copyCar(Car car) {
            this.carId = car.carId;
            this.carInformation = car.carInformation;
            this.carInsurance = car.carInsurance;
            this.rentalRate = car.rentalRate;
            this.availabilityStatus = car.availabilityStatus;
            this.carPicture = car.carPicture;
            return this;
        }

        public Car buildCar() {
            return new Car(this);
        }
    }
}
