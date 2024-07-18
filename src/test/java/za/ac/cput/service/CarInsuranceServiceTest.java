package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CarInsurance;
import org.junit.jupiter.api.*;
import za.ac.cput.factory.CarInsuranceFactory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarInsuranceServiceTest {

    @Autowired
    private CarInsuranceService carInsuranceService;

    private static CarInsurance carInsurance1;
    private static CarInsurance carInsurance2;

    @BeforeEach
    void setUp() {
        carInsurance1 = CarInsuranceFactory.buildCarInsurance("Outsurance", "POL12345", "Comprehensive", "100000");
        assertNotNull(carInsurance1);
        carInsurance2 = CarInsuranceFactory.buildCarInsurance("Outsurance", "POL67890", "Liability", "50000");
        assertNotNull(carInsurance2);
    }

    @Test
    @Order(1)
    void save() {
        CarInsurance created1 = carInsuranceService.create(carInsurance1);
        assertNotNull(created1);
        System.out.println("Saved CarInsurance 1: " + created1);

        CarInsurance created2 = carInsuranceService.create(carInsurance2);
        assertNotNull(created2);
        System.out.println("Saved CarInsurance 2: " + created2);
    }

    @Test
    @Order(2)
    void read() {
        CarInsurance found = carInsuranceService.read(carInsurance1.getPolicyNumber());
        assertNotNull(found);
        System.out.println("Read CarInsurance: " + found);
    }

    @Test
    @Order(3)
    void delete() {
        carInsuranceService.delete(carInsurance2.getPolicyNumber());
        System.out.println("Deleted CarInsurance with PolicyNumber: " + carInsurance2.getPolicyNumber());
    }

    @Test
    @Order(4)
    void update() {
        CarInsurance newCarInsurance = new CarInsurance.Builder()
                .copyCarInsurance(carInsurance1)
                .setCoverageAmount("150000")
                .buildCarInsurance();

        CarInsurance updatedCarInsurance = carInsuranceService.update(newCarInsurance);
        assertNotNull(updatedCarInsurance);
        System.out.println("Updated CarInsurance: " + updatedCarInsurance);
    }
}
