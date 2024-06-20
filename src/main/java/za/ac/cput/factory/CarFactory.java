package za.ac.cput.factory;

import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class CarFactory {
    public static Car createCar(String carID,CarInformation carInformation,
                                String rate, String availability, String status) {

        if (Helper.isNullOrEmpty(carID) ||
                Helper.isNullOrEmpty(rate) ||
                Helper.isNullOrEmpty(availability) ||
                Helper.isNullOrEmpty(status)) {
            return null;
        }

        return new Car.Builder()
                .setCarID(carID)
                .setCarInformation(carInformation)
                .setRate(rate)
                .setAvailability(availability)
                .setStatus(status)
                .buildCar();
    }
}
