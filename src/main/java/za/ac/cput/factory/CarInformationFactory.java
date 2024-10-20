package za.ac.cput.factory;

import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.User;

public class CarInformationFactory {

    public static CarInformation buildCarInformation(
            String make, String model, String year, String type,
            String licensePlate, String description, String features, User user,
            double rentalRate, String availabilityStatus, String picture1Base64, String picture2Base64, String picture3Base64) {

        return new CarInformation.Builder()
                .setMake(make)
                .setModel(model)
                .setYear(year)
                .setType(type)
                .setLicensePlate(licensePlate)
                .setDescription(description)
                .setFeatures(features)
                .setUser(user)
                .setRentalRate(rentalRate)
                .setAvailabilityStatus(availabilityStatus)
                .setPicture1Base64(picture1Base64)
                .setPicture2Base64(picture2Base64)
                .setPicture3Base64(picture3Base64)
                .buildCar();
    }
}
