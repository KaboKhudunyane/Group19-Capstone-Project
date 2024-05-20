package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class CarInformation {
    @Id
    private String carInformationId;

    private String make;
    private String model;
    private String year;
    private String licensePlate;
    private String description;
    private String features;

    @Column(name = "car_id", nullable = false)
    private String carId;

    protected CarInformation() {}

    private CarInformation(Builder builder) {
        this.carInformationId = builder.carInformationId;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.description = builder.description;
        this.features = builder.features;
        this.carId = builder.carId;
    }

    public String getCarInformationId() {
        return carInformationId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getDescription() {
        return description;
    }

    public String getFeatures() {
        return features;
    }

    public String getCarId() {
        return carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInformation that = (CarInformation) o;
        return Objects.equals(carInformationId, that.carInformationId) &&
                Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                Objects.equals(year, that.year) &&
                Objects.equals(licensePlate, that.licensePlate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(features, that.features) &&
                Objects.equals(carId, that.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carInformationId, make, model, year, licensePlate, description, features, carId);
    }

    @Override
    public String toString() {
        return "CarInformation{" +
                "carInformationId='" + carInformationId + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", description='" + description + '\'' +
                ", features='" + features + '\'' +
                ", carId='" + carId + '\'' +
                '}';
    }

    public static class Builder {
        private String carInformationId;
        private String make;
        private String model;
        private String year;
        private String licensePlate;
        private String description;
        private String features;
        private String carId;

        public Builder setCarInformationId(String carInformationId) {
            this.carInformationId = carInformationId;
            return this;
        }

        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(String year) {
            this.year = year;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setFeatures(String features) {
            this.features = features;
            return this;
        }

        public Builder setCarId(String carId) {
            this.carId = carId;
            return this;
        }

        public Builder copy(CarInformation carInformation) {
            this.carInformationId = carInformation.carInformationId;
            this.make = carInformation.make;
            this.model = carInformation.model;
            this.year = carInformation.year;
            this.licensePlate = carInformation.licensePlate;
            this.description = carInformation.description;
            this.features = carInformation.features;
            this.carId = carInformation.carId;
            return this;
        }

        public CarInformation build() {
            return new CarInformation(this);
        }
    }
}


