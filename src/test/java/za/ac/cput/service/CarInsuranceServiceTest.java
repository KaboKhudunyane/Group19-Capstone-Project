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
import za.ac.cput.factory.CarInsuranceFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarInsuranceServiceTest {

    @Autowired
    private CarInsuranceService carInsuranceService;

    @Autowired
    private CarInformationService carInformationService;

    @Autowired
    private UserService userService;

    private CarInformation carInformation1;
    private CarInformation carInformation2;
    private CarInformation carInformation3;
    private CarInformation carInformation4;
    private CarInformation carInformation5;
    private CarInformation carInformation6;
    private CarInformation carInformation7;
    private CarInformation carInformation8;
    private CarInformation carInformation9;
    private CarInformation carInformation10;


    private User user1;
    private User user2;
    private User user3;
    private User user4;



    private CarInsurance carInsurance1;
    private CarInsurance carInsurance2;
    private CarInsurance carInsurance3;
    private CarInsurance carInsurance4;
    private CarInsurance carInsurance5;
    private CarInsurance carInsurance6;
    private CarInsurance carInsurance7;
    private CarInsurance carInsurance8;
    private CarInsurance carInsurance9;
    private CarInsurance carInsurance10;






    private static Long carInsuranceID1;
    private static Long carInsuranceID2;
    private static Long carInsuranceID3;
    private static Long carInsuranceID4;
    private static Long carInsuranceID5;
    private static Long carInsuranceID6;
    private static Long carInsuranceID7;
    private static Long carInsuranceID8;
    private static Long carInsuranceID9;
    private static Long carInsuranceID10;




    @BeforeEach
    void setUp() {
        carInformation1 = carInformationService.read(1L);
        carInformation2 = carInformationService.read(2L);
        carInformation3 = carInformationService.read(3L);
        carInformation4 = carInformationService.read(4L);
        carInformation5 = carInformationService.read(5L);
        carInformation6 = carInformationService.read(6L);
        carInformation7 = carInformationService.read(7L);
        carInformation8 = carInformationService.read(8L);
        carInformation9 = carInformationService.read(9L);
        carInformation10 = carInformationService.read(10L);


        user1 = userService.read(3L);
        user2 = userService.read(4L);
        user3 = userService.read(5L);
        user4 = userService.read(6L);


        carInsurance1 = CarInsuranceFactory.buildCarInsurance(
                "MiWay", 15447841, "Insurance", 1200, user1, carInformation1
        );
        carInsurance2 = CarInsuranceFactory.buildCarInsurance(
                "MiWay", 15447841, "Insurance", 1200, user1, carInformation2
        );
        carInsurance3 = CarInsuranceFactory.buildCarInsurance(
                "MiWay", 15447841, "Insurance", 1200, user2, carInformation3
        );
        carInsurance4 = CarInsuranceFactory.buildCarInsurance(
                "MiWay", 15447841, "Insurance", 1200, user2, carInformation4
        );
        carInsurance5 = CarInsuranceFactory.buildCarInsurance(
                "Budget", 221456987, "Insurance", 1400, user3, carInformation5
        );
        carInsurance6 = CarInsuranceFactory.buildCarInsurance(
                "Budget", 221456987, "Insurance", 1400, user3, carInformation6
        );
        carInsurance7 = CarInsuranceFactory.buildCarInsurance(
                "Budget", 221456987, "Insurance", 1400, user4, carInformation7
        );
        carInsurance8 = CarInsuranceFactory.buildCarInsurance(
                "Budget", 221456987, "Insurance", 1400, user4, carInformation8
        );
        carInsurance9 = CarInsuranceFactory.buildCarInsurance(
                "Legit", 331458661, "Insurance", 1200, user3, carInformation9
        );
        carInsurance10 = CarInsuranceFactory.buildCarInsurance(
                "Legit", 331456999, "Insurance", 1200, user1, carInformation10
        );

    }

    @Test
    @Order(1)
    void create() {
        CarInsurance created1 = carInsuranceService.create(carInsurance1);
        CarInsurance created2 = carInsuranceService.create(carInsurance2);
        CarInsurance created3 = carInsuranceService.create(carInsurance3);
        CarInsurance created4 = carInsuranceService.create(carInsurance4);
        CarInsurance created5 = carInsuranceService.create(carInsurance5);
        CarInsurance created6 = carInsuranceService.create(carInsurance6);
        CarInsurance created7 = carInsuranceService.create(carInsurance7);
        CarInsurance created8 = carInsuranceService.create(carInsurance8);
        CarInsurance created9 = carInsuranceService.create(carInsurance9);
        CarInsurance created10 = carInsuranceService.create(carInsurance10);


        assertNotNull(created1);
        assertNotNull(created2);
        assertNotNull(created3);
        assertNotNull(created4);
        assertNotNull(created5);
        assertNotNull(created6);
        assertNotNull(created7);
        assertNotNull(created8);
        assertNotNull(created9);
        assertNotNull(created10);


        carInsuranceID1 = created1.getCarInsuranceID();
        carInsuranceID2 = created2.getCarInsuranceID();
        carInsuranceID3 = created3.getCarInsuranceID();
        carInsuranceID4 = created4.getCarInsuranceID();
        carInsuranceID5 = created5.getCarInsuranceID();
        carInsuranceID6 = created6.getCarInsuranceID();
        carInsuranceID7 = created7.getCarInsuranceID();
        carInsuranceID8 = created8.getCarInsuranceID();
        carInsuranceID9= created9.getCarInsuranceID();
        carInsuranceID10 = created10.getCarInsuranceID();


        System.out.println(created1);
        System.out.println(created2);
        System.out.println(created3);
        System.out.println(created4);
        System.out.println(created5);
        System.out.println(created6);
        System.out.println(created7);
        System.out.println(created8);
        System.out.println(created9);
        System.out.println(created10);


    }

    @Test
    @Order(2)
    void read() {
        CarInsurance read1= carInsuranceService.read(carInsuranceID1);
        CarInsurance read2 = carInsuranceService.read(carInsuranceID2);
        CarInsurance read3 = carInsuranceService.read(carInsuranceID3);
        CarInsurance read4 = carInsuranceService.read(carInsuranceID4);
        CarInsurance read5 = carInsuranceService.read(carInsuranceID5);
        CarInsurance read6 = carInsuranceService.read(carInsuranceID6);
        CarInsurance read7= carInsuranceService.read(carInsuranceID7);
        CarInsurance read8 = carInsuranceService.read(carInsuranceID8);
        CarInsurance read9 = carInsuranceService.read(carInsuranceID9);
        CarInsurance read10= carInsuranceService.read(carInsuranceID10);



        assertNotNull(read1);
        assertNotNull(read2);
        assertNotNull(read3);
        assertNotNull(read4);
        assertNotNull(read5);
        assertNotNull(read6);
        assertNotNull(read7);
        assertNotNull(read8);
        assertNotNull(read9);
        assertNotNull(read10);


        System.out.println(read1);
        System.out.println(read2);
        System.out.println(read3);
        System.out.println(read4);
        System.out.println(read5);
        System.out.println(read6);
        System.out.println(read7);
        System.out.println(read8);
        System.out.println(read9);
        System.out.println(read10);


    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(carInsuranceService.getAll());
    }
}
