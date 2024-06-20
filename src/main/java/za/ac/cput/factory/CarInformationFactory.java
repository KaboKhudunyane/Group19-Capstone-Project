package za.ac.cput.factory;

import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.util.Helper;

public class CarInformationFactory {

    public static CarInformation buildCarInformation(String carInformationId, String make, String model,
                                                     String year, String licensePlate, String description, String features,
                                                     Car carId) {
        if (Helper.isNullOrEmpty(carInformationId) ||
                Helper.isNullOrEmpty(make) || Helper.isNullOrEmpty(model) || Helper.isNullOrEmpty(year) ||
                Helper.isNullOrEmpty(licensePlate) || Helper.isNullOrEmpty(description) || Helper.isNullOrEmpty(features))
                 {
            return null;
        }

        return new CarInformation.Builder()
                .setCarInformationId(carInformationId)
                .setMake(make)
                .setModel(model)
                .setYear(year)
                .setLicensePlate(licensePlate)
                .setDescription(description)
                .setFeatures(features)
                .setCarId(carId)
                .buildCarInformation();
    }

    public static CarInformation buildCarInformationWithoutCar(String make, String model, String year,
                                                               String licensePlate, String description, String features) {
        if (Helper.isNullOrEmpty(make) || Helper.isNullOrEmpty(model) || Helper.isNullOrEmpty(year)
                || Helper.isNullOrEmpty(licensePlate) || Helper.isNullOrEmpty(description) || Helper.isNullOrEmpty(features)) {
            return null;
        }

        return new CarInformation.Builder()
                .setMake(make)
                .setModel(model)
                .setYear(year)
                .setLicensePlate(licensePlate)
                .setDescription(description)
                .setFeatures(features)
                .buildCarInformation();
    }
}






