package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CarInformationFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarInformationServiceTest {

    @Autowired
    private CarInformationService carInformationService;

    @Autowired
    private UserService userService;

    private static Long carInformationID_1;
    private static Long carInformationID_2;

    /*
    private static Long carInformationID_3;
    private static Long carInformationID_4;
    private static Long carInformationID_5;
    private static Long carInformationID_6;
    private static Long carInformationID_7;
    private static Long carInformationID_8;
    private static Long carInformationID_9;
    private static Long carInformationID_10;

     */



    private User user;

    private CarInformation carInformation1;
    private CarInformation carInformation2;

    /*
    private CarInformation carInformation3;
    private CarInformation carInformation4;
    private CarInformation carInformation5;
    private CarInformation carInformation6;
    private CarInformation carInformation7;
    private CarInformation carInformation8;
    private CarInformation carInformation9;
    private CarInformation carInformation10;

     */

    @BeforeEach
    void setUp() {
        user = userService.read(1L);


        carInformation1 = CarInformationFactory.buildCarInformation(
                "Toyota", "Scarlet", "2020", "Manual", "Plate-123",
                "Red 5 door car with 50 000km mileage", "Leather seats, Navigation system, Bluetooth", user,
                2000, "Available",
                loadPicture("scarlet1.jpg"),
                loadPicture("scarlet2.jpg"),
                loadPicture("scarlet3.jpg")
        );

        carInformation2 = CarInformationFactory.buildCarInformation(
                "Honda", "Civic", "2019", "Automatic", "Plate-456", "White coupe car with 40 000km mileage", "Gray leather seats, Sunroof, Bluetooth",
                user, 2200, "Availbale",
                loadPicture("civic1.jpg"),
                loadPicture("civic2.jpg"),
                loadPicture("civic3.jpg")
        );

        /*

        carInformation3 =CarInformationFactory.buildCarInformation(
                "Ford", "Focus", "2018", "Manual", "Plate-789", "Blue 5 door car with 30 000km mileage", "Cloth seats, Navigation system, Bluetooth", carInsurance, 1800, "Available",
                loadPicture("focus1.jpg"),
                loadPicture("focus2.jpg"),
                loadPicture("focus3.jpg")
        );

        carInformation4 = CarInformationFactory.buildCarInformation(
                "BMW", "3 Series", "2021", "Automatic", "Plate-321", "White 4 door car with 10 000km mileage", "Leather seats, Sunroof, Bluetooth", carInsurance, 3500, "Available",
                loadPicture("bmw2.jpg"), loadPicture("bmw2.jpg"),loadPicture("bmw3.jpg")
        );

        carInformation5 = CarInformationFactory.buildCarInformation(
                "Audi", "A4", "2022", "Manual", "Plate-654", "White 4 door car with 5 000km mileage", "Leather seats, Navigation system, Bluetooth", carInsurance, 3700,"Available",
                loadPicture("audi1.jpg"), loadPicture("audi2.jpg"), loadPicture("audi3.jpg")
        );

        carInformation6 = CarInformationFactory.buildCarInformation(
                "Mercedes", "C180", "2020", "Automatic", "Plate-987", "Silver 4 door car with 20 000km mileage", "Leather seats, Sunroof, Bluetooth", carInsurance, 4000, "Available",
                loadPicture("mercedes1.jpg"), loadPicture("mercedes2.jpg"), loadPicture("mercedes3.jpg")

                );

        carInformation7 = CarInformationFactory.buildCarInformation(
                "Volkswagen", "Golf", "2019", "Manual", "Plate-147", "White 5 door car with 25 000km mileage", "Cloth seats, Navigation system, Bluetooth", carInsurance, 2000,"Available",
                loadPicture("golf1.jpg"), loadPicture("golf2.jpg"), loadPicture("golf3.jpg" )
        );

        carInformation8 = CarInformationFactory.buildCarInformation(
                "Hyundai", "Elantra", "2018", "Automatic", "Plate-258", "White 4 door car with 35 000km mileage", "Cloth seats, Bluetooth", carInsurance, 1600, "Available",
                loadPicture("elantra1.jpg"), loadPicture("elantra2.jpg"), loadPicture("elantra3.jpg")
        );

        carInformation9 =CarInformationFactory.buildCarInformation(
                "Nissan", "X-trail", "2017", "Manual", "Plate-369", "Dark gray 4 door car with 45 000km mileage", "Leather seats, Navigation system, Bluetooth", carInsurance, 1400, "Available",
                loadPicture("al1.jpg"), loadPicture("al2.jpg"), loadPicture("al3.jpg")
        );

        carInformation10 = CarInformationFactory.buildCarInformation(
                "Mazda", "3", "2016", "Automatic", "Plate-9877", "Red 5 door car with 55 000km mileage", "Cloth seats, Bluetooth", carInsurance, 1200, "Available",
                loadPicture("mazda1.jpg"), loadPicture("mazda2.jpg"), loadPicture("mazda3.jpg")

        );

         */
    }

    private byte[] loadPicture(String fileName) {
        try {
            Path path = Paths.get("src/images/img-prototype/" + fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }



    @Test
    @Order(1)
    void create() {
        CarInformation created1 = carInformationService.create(carInformation1);
        CarInformation created2 = carInformationService.create(carInformation2);

        /*
        CarInformation created3 = carInformationService.create(carInformation3);
        CarInformation created4 = carInformationService.create(carInformation4);
        CarInformation created5 = carInformationService.create(carInformation5);
        CarInformation created6 = carInformationService.create(carInformation6);
        CarInformation created7 = carInformationService.create(carInformation7);
        CarInformation created8 = carInformationService.create(carInformation8);
        CarInformation created9 = carInformationService.create(carInformation9);
        CarInformation created10 = carInformationService.create(carInformation10);

         */

        assertNotNull(created1);
        assertNotNull(created2);

        /*
        assertNotNull(created3);
        assertNotNull(created4);
        assertNotNull(created5);
        assertNotNull(created6);
        assertNotNull(created7);
        assertNotNull(created8);
        assertNotNull(created9);
        assertNotNull(created10);

         */

        carInformationID_1 = created1.getCarInformationID();
        carInformationID_2 = created2.getCarInformationID();

        /*
        carInformationID_3 = created3.getCarInformationID();
        carInformationID_4 = created4.getCarInformationID();
        carInformationID_5 = created5.getCarInformationID();
        carInformationID_6 = created6.getCarInformationID();
        carInformationID_7 = created7.getCarInformationID();
        carInformationID_8 = created8.getCarInformationID();
        carInformationID_9 = created9.getCarInformationID();
        carInformationID_10 = created10.getCarInformationID();

         */

        System.out.println(created1);
        System.out.println(created2);

        /*
        System.out.println(created3);
        System.out.println(created4);
        System.out.println(created5);
        System.out.println(created6);
        System.out.println(created7);
        System.out.println(created8);
        System.out.println(created9);
        System.out.println(created10);

         */

    }






    @Test
    @Order(2)
    void read() {
        CarInformation read1 = carInformationService.read(carInformationID_1);
        CarInformation read2 = carInformationService.read(carInformationID_2);

        /*
        CarInformation read3 = carInformationService.read(carInformationID_3);
        CarInformation read4 = carInformationService.read(carInformationID_4);
        CarInformation read5 = carInformationService.read(carInformationID_5);
        CarInformation read6 = carInformationService.read(carInformationID_6);
        CarInformation read7 = carInformationService.read(carInformationID_7);
        CarInformation read8 = carInformationService.read(carInformationID_8);
        CarInformation read9 = carInformationService.read(carInformationID_9);
        CarInformation read10 = carInformationService.read(carInformationID_10);

         */

        assertNotNull(read1);
        assertNotNull(read2);

        /*
        assertNotNull(read3);
        assertNotNull(read4);
        assertNotNull(read5);
        assertNotNull(read6);
        assertNotNull(read7);
        assertNotNull(read8);
        assertNotNull(read9);
        assertNotNull(read10);

         */


        System.out.println(read1);
        System.out.println(read2);

        /*
        System.out.println(read3);
        System.out.println(read4);
        System.out.println(read5);
        System.out.println(read6);
        System.out.println(read7);
        System.out.println(read8);
        System.out.println(read9);
        System.out.println(read10);

         */

    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(carInformationService.getAll());
    }

    @Test
    void testCount(){
        System.out.println("Numbers of Cars: "+carInformationService.countCars());
    }
}
