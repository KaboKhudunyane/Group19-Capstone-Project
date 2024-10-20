package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Entity
public class CarInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carInformationID;
    private String make;
    private String model;
    private String year;
    private String type;
    private String licensePlate;
    private String description;
    private String features;

    @ManyToOne
    private User user ;

    private double rentalRate;
    private String availabilityStatus;

    @Lob
    @Column(columnDefinition = "longBlob")
    private byte[] picture1;

    @Lob
    @Column(columnDefinition = "longBlob")
    private byte[] picture2;

    @Lob
    @Column(columnDefinition = "longBlob")
    private byte[] picture3;

    protected CarInformation() {}

    private CarInformation(Builder builder) {
        this.carInformationID = builder.carInformationID;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.type = builder.type;
        this.licensePlate = builder.licensePlate;
        this.description = builder.description;
        this.features = builder.features;
        this.user = builder.user;
        this.rentalRate = builder.rentalRate;
        this.availabilityStatus = builder.availabilityStatus;
        this.picture1 = builder.picture1;
        this.picture2 = builder.picture2;
        this.picture3 = builder.picture3;
    }

    // Getters
    public Long getCarInformationID() {
        return carInformationID;
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

    public String getType() {
        return type;
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

    public User getUser() {
        return user;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public byte[] getPicture1() {
        return picture1;
    }

    public byte[] getPicture2() {
        return picture2;
    }

    public byte[] getPicture3() {
        return picture3;
    }

    public String getPicture1Base64() {
        return picture1 != null ? Base64.getEncoder().encodeToString(picture1) : null;
    }

    public String getPicture2Base64() {
        return picture2 != null ? Base64.getEncoder().encodeToString(picture2) : null;
    }

    public String getPicture3Base64() {
        return picture3 != null ? Base64.getEncoder().encodeToString(picture3) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInformation that = (CarInformation) o;
        return Double.compare(rentalRate, that.rentalRate) == 0 && Objects.equals(carInformationID, that.carInformationID) && Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(year, that.year) && Objects.equals(type, that.type) && Objects.equals(licensePlate, that.licensePlate) && Objects.equals(description, that.description) && Objects.equals(features, that.features) && Objects.equals(user, that.user) && Objects.equals(availabilityStatus, that.availabilityStatus) && Objects.deepEquals(picture1, that.picture1) && Objects.deepEquals(picture2, that.picture2) && Objects.deepEquals(picture3, that.picture3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carInformationID, make, model, year, type, licensePlate, description, features, user, rentalRate, availabilityStatus, Arrays.hashCode(picture1), Arrays.hashCode(picture2), Arrays.hashCode(picture3));
    }

    @Override
    public String toString() {
        return "CarInformation{" +
                "carInformationID=" + carInformationID +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", description='" + description + '\'' +
                ", features='" + features + '\'' +
                ", user=" + user +
                ", rentalRate=" + rentalRate +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", picture1=" + Arrays.toString(picture1) +
                ", picture2=" + Arrays.toString(picture2) +
                ", picture3=" + Arrays.toString(picture3) +
                '}';
    }

    public static class Builder {
        private Long carInformationID;
        private String make;
        private String model;
        private String year;
        private String type;
        private String licensePlate;
        private String description;
        private String features;
        private User user;
        private double rentalRate;
        private String availabilityStatus;
        private byte[] picture1;
        private byte[] picture2;
        private byte[] picture3;

        // Builder methods...
        public Builder setCarInformationID(Long carInformationID) {
            this.carInformationID = carInformationID;
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

        public Builder setType(String type) {
            this.type = type;
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

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setRentalRate(double rentalRate) {
            this.rentalRate = rentalRate;
            return this;
        }

        public Builder setAvailabilityStatus(String availabilityStatus) {
            this.availabilityStatus = availabilityStatus;
            return this;
        }

        public Builder setPicture1(byte[] picture1) {
            this.picture1 = picture1;
            return this;
        }

        public Builder setPicture2(byte[] picture2) {
            this.picture2 = picture2;
            return this;
        }

        public Builder setPicture3(byte[] picture3) {
            this.picture3 = picture3;
            return this;
        }

        public Builder setPicture1Base64(String picture1Base64) {
            if (picture1Base64 != null) {
                this.picture1 = Base64.getDecoder().decode(picture1Base64);
            }
            return this;
        }

        public Builder setPicture2Base64(String picture2Base64) {
            if (picture2Base64 != null) {
                this.picture2 = Base64.getDecoder().decode(picture2Base64);
            }
            return this;
        }

        public Builder setPicture3Base64(String picture3Base64) {
            if (picture3Base64 != null) {
                this.picture3 = Base64.getDecoder().decode(picture3Base64);
            }
            return this;
        }

        public Builder copy(CarInformation carInformation) {
            this.carInformationID = carInformation.carInformationID;
            this.make = carInformation.make;
            this.model = carInformation.model;
            this.year = carInformation.year;
            this.type = carInformation.type;
            this.licensePlate = carInformation.licensePlate;
            this.description = carInformation.description;
            this.features = carInformation.features;
            this.user = carInformation.user;
            this.rentalRate = carInformation.rentalRate;
            this.availabilityStatus = carInformation.availabilityStatus;
            this.picture1 = carInformation.picture1;
            this.picture2 = carInformation.picture2;
            this.picture3 = carInformation.picture3;
            return this;
        }

        public CarInformation buildCar() {
            return new CarInformation(this);
        }
    }
}
