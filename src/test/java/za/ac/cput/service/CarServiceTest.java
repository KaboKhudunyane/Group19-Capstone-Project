package za.ac.cput.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.CarInsuranceFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CarServiceTest {
    @Autowired
    private CarService carService;
    private static CarInformation carInformation = new CarInformation.Builder()
            .setMake("Toyota")
            .setModel("Corolla")
            .setYear("2020")
            .setLicensePlate("ABC123")
            .setDescription("A reliable sedan")
            .setFeatures("Air conditioning, Power windows")
            .buildCarInformation();
    private static CarInsurance carInsurance = new CarInsurance.Builder()
            .setPolicyNumber("12345")
            .setInsuranceCompany("ABC Insurance")
            .buildCarInsurance();
    private static Car car = CarFactory.buildCar("123",
            carInformation, carInsurance, "100","carpicture.jpeg");

    @Test
    void create() {
        Car createdCar = carService.create(car);
        assertNotNull(createdCar);
        System.out.println("Created Car: " + createdCar);
    }

    @Test
    void read() {
        Car readCar = carService.read(car.getCarId().toString());
        assertNotNull(readCar);
        System.out.println("Read Car: " + readCar);
    }

    @Test
    void update() {
        Car carToUpdate = carService.read(car.getCarId().toString());
        assertNotNull(carToUpdate);

        // Update using the builder pattern
        Car updatedCar = new Car.Builder()
                .copyCar(carToUpdate)
                .setRentalRate("150") // Update rental rate
                .buildCar();

        Car savedUpdatedCar = carService.update(updatedCar);
        assertNotNull(savedUpdatedCar);
        assertEquals("150", savedUpdatedCar.getRentalRate());
        System.out.println("Updated Car: " + savedUpdatedCar);
    }

    @Test
    void delete() {
        carService.delete(car.getCarId().toString());
        Car deletedCar = carService.read(car.getCarId().toString());
        assertNull(deletedCar);
        System.out.println("Car deleted successfully");
    }
}
