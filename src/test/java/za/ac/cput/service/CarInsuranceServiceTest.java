package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarInsuranceFactory;



import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarInsuranceServiceTest {

    @Autowired
    private CarInsuranceService carInsuranceService;
    private static Long carInsuranceID;


    CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance(
            "MiWay", 15447841, "Insurance", 1200
    );

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


