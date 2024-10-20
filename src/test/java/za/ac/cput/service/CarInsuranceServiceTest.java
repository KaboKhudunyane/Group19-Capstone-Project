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

    private CarInformation carInformation;
    private User user;
    private CarInsurance carInsurance;
    private static Long carInsuranceID;

    // This method will run before each test to set up shared objects
    @BeforeEach
    void setUp() {
        // Fetching carInformation and user using the injected service instances
        carInformation = carInformationService.read(1L);
        user = userService.read(1L);

        // Building the CarInsurance object using the factory
        carInsurance = CarInsuranceFactory.buildCarInsurance(
                "MiWay", 15447841, "Insurance", 1200, user, carInformation
        );
    }

    @Test
    @Order(1)
    void create() {
        CarInsurance created = carInsuranceService.create(carInsurance);
        assertNotNull(created);
        carInsuranceID = created.getCarInsuranceID();
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        CarInsurance read = carInsuranceService.read(carInsuranceID);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(carInsuranceService.getAll());
    }
}
