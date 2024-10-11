package za.ac.cput.factory;

import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.User;

public class CarInformationFactory {

    public static CarInformation buildCarInformation(
            String make, String model, String year, String type,
            String licensePlate, String description, String features, User user,
            double rentalRate, String availabilityStatus, byte[] picture1, byte[] picture2, byte[] picture3) {

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
                .setPicture1(picture1)
                .setPicture2(picture2)
                .setPicture3(picture3)
                .buildCar();
    }
}
