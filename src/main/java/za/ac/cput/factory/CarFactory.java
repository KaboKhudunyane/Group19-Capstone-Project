package za.ac.cput.factory;

import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.util.Helper;

public class CarFactory {

    public static Car buildCar(CarInformation carInformation, CarInsurance carInsurance,
                               String rentalRate, String availabilityStatus, String carPicture) {
        // Validate inputs
        if (carInformation == null || carInsurance == null ||
                Helper.isNullOrEmpty(rentalRate) || Helper.isNullOrEmpty(availabilityStatus)) {
            return null; // If any required field is null or empty, return null
        }

        // If inputs are valid, use the Builder pattern to create a Car object
        return new Car.Builder()
                .setCarInformation(carInformation)
                .setCarInsurance(carInsurance)
                .setRentalRate(rentalRate)
                .setAvailabilityStatus(availabilityStatus)
                .setCarPicture(carPicture) // Set the byte array representing the picture
                .buildCar();
    }
}
