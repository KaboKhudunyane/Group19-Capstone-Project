package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class Car {
    @Id
    private String carID;  // Unique identifier for the car
    //@OneToOne
    //private User userID;  // User ID associated with the car
    @OneToOne
    private CarInformation carInformation;  // Information about the car
    private String rate;  // Rental rate of the car
    private String availability;  // Availability status of the car
    private String status;  // Current status of the car

    // Protected no-arg constructor for JPA
    protected Car() {}

    // Private constructor for Builder
    private Car(Builder builder) {
        this.carID = builder.carID;
        //this.userID = builder.userID;
        this.carInformation = builder.carInformation;
        this.rate = builder.rate;
        this.availability = builder.availability;
        this.status = builder.status;
    }

    // Getters for all fields
    public String getCarID() {
        return carID;
    }

    //public User getUserID() {
     //   return userID;
    //}

    public CarInformation getCarInformation() {
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

    // Setters for all fields
    public void setCarID(String carID) {
        this.carID = carID;
    }

    //public void setUserID(User userID) {
       // this.userID = userID;
   // }

    public void setCarInformation(CarInformation carInformation) {
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

    // Override equals method for object comparison
    @Override
    public boolean equals(Object carObject) {
        if (this == carObject) return true;
        if (carObject == null || getClass() != carObject.getClass()) return false;
        Car car = (Car) carObject;
        return Objects.equals(carID, car.carID) &&
                Objects.equals(carInformation, car.carInformation) &&
                Objects.equals(rate, car.rate) &&
                Objects.equals(availability, car.availability) &&
                Objects.equals(status, car.status);
    }

    // Override hashCode method for object hashing
    @Override
    public int hashCode() {
        return Objects.hash(carID, carInformation, rate, availability, status);
    }

    // Override toString method for object string representation


    @Override
    public String toString() {
        return "Car{" +
                "carID='" + carID + '\'' +
                ", carInformation=" + carInformation +
                ", rate='" + rate + '\'' +
                ", availability='" + availability + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // Static Builder class for Car
    public static class Builder {
        private String carID;  // Unique identifier for the car
        //private User userID;  // User ID associated with the car
        private CarInformation carInformation;  // Information about the car
        private String rate;  // Rental rate of the car
        private String availability;  // Availability status of the car
        private String status;  // Current status of the car

        // Setters for all fields with Builder pattern
        public Builder setCarID(String carID) {
            this.carID = carID;
            return this;
        }

       /* public Builder setUserID(User userID) {
            this.userID = userID;
            return this;
        }*/

        public Builder setCarInformation(CarInformation carInformation) {
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

        // Copy method to copy fields from another Car object
        public Builder copyCar(Car car) {
            this.carID = car.carID;
            //this.userID = car.userID;
            this.carInformation = car.carInformation;
            this.rate = car.rate;
            this.availability = car.availability;
            this.status = car.status;
            return this;
        }

        // Build method to create Car object
        public Car buildCar() {
            return new Car(this);
        }
    }
}
