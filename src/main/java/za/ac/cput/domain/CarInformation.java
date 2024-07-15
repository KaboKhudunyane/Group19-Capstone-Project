package za.ac.cput.domain;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class CarInformation implements Serializable {
    // The make of the car (e.g., Volkswagen, Audi)
    private String make;
    // The model of the car (e.g., Golf, A4)
    private String model;
    // The manufacturing year of the car (e.g., 2023)
    private String year;
    // The license plate number of the car
    private String licensePlate;
    // A brief description of the car
    private String description;
    // Additional features of the car
    private String features;
    protected CarInformation() {}
    private CarInformation(Builder builder) {
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.description = builder.description;
        this.features = builder.features;
    }
    // Getters for all fields
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
    // Equals and HashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInformation that = (CarInformation) o;
        return Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                Objects.equals(year, that.year) &&
                Objects.equals(licensePlate, that.licensePlate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(features, that.features);
    }
    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, licensePlate, description, features);
    }
    // ToString method
    @Override
    public String toString() {
        return "CarInformation{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", description='" + description + '\'' +
                ", features='" + features + '\'' +
                '}';
    }
    // Builder class for constructing CarInformation objects
    public static class Builder {
        private String make;
        private String model;
        private String year;
        private String licensePlate;
        private String description;
        private String features;
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
        public Builder copyCarInformation(CarInformation carInformation) {
            this.make = carInformation.make;
            this.model = carInformation.model;
            this.year = carInformation.year;
            this.licensePlate = carInformation.licensePlate;
            this.description = carInformation.description;
            this.features = carInformation.features;
            return this;
        }
        public CarInformation buildCarInformation() {
            return new CarInformation(this);
        }
    }
}