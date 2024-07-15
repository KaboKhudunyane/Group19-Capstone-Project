package za.ac.cput.factory;

import za.ac.cput.domain.CarInformation;
import za.ac.cput.util.Helper;

public class CarInformationFactory {

    public static CarInformation buildCarInformation(String make, String model,
                                                     String year, String licensePlate,
                                                     String description, String features) {
        // Validate inputs using Helper utility class
        if (Helper.isNullOrEmpty(make) || Helper.isNullOrEmpty(model) ||
                Helper.isNullOrEmpty(year) || Helper.isNullOrEmpty(licensePlate)) {
            return null; // If any required field is null or empty, return null
        }

        // If inputs are valid, use the Builder pattern to create a CarInformation object
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
