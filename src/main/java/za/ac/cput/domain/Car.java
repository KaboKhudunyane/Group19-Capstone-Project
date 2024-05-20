package za.ac.cput.domain;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {
    private String carID;
    private String userID;
    private String carInformation;
    private String rate;
    private String availability;
    private String status;

    protected Car() {}

    private Car(Builder builder) {
        this.carID = builder.carID;
        this.userID = builder.userID;
        this.carInformation = builder.carInformation;
        this.rate = builder.rate;
        this.availability = builder.availability;
        this.status = builder.status;
    }

    public String getCarID() {
        return carID;
    }

    public String getUserID() {
        return userID;
    }

    public String getCarInformation() {
        return carInformation;
    }

    public String getRate() {
        return rate;
    }

    public String getAvailability() {
        return availability;
    }

    public String getStatus() {
        return status;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCarInformation(String carInformation) {
        this.carInformation = carInformation;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object carObject) {
        if (this == carObject) return true;
        if (carObject == null || getClass() != carObject.getClass()) return false;
        Car car = (Car) carObject;
        return Objects.equals(carID, car.carID) &&
                Objects.equals(userID, car.userID) &&
                Objects.equals(carInformation, car.carInformation) &&
                Objects.equals(rate, car.rate) &&
                Objects.equals(availability, car.availability) &&
                Objects.equals(status, car.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carID, userID, carInformation, rate, availability, status);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carID='" + carID + '\'' +
                ", userID='" + userID + '\'' +
                ", carInformation='" + carInformation + '\'' +
                ", rate='" + rate + '\'' +
                ", availability='" + availability + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder {
        private String carID;
        private String userID;
        private String carInformation;
        private String rate;
        private String availability;
        private String status;

        public Builder setCarID(String carID) {
            this.carID = carID;
            return this;
        }

        public Builder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public Builder setCarInformation(String carInformation) {
            this.carInformation = carInformation;
            return this;
        }

        public Builder setRate(String rate) {
            this.rate = rate;
            return this;
        }

        public Builder setAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copyCar(Car car) {
            this.carID = car.carID;
            this.userID = car.userID;
            this.carInformation = car.carInformation;
            this.rate = car.rate;
            this.availability = car.availability;
            this.status = car.status;
            return this;
        }

        public Car buildCar() {
            return new Car(this);
        }
    }
}
