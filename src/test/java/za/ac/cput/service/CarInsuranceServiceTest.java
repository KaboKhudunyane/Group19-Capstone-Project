package za.ac.cput.repository.factory;

;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CarInsurance;
import java.util.List;
import org.junit.jupiter.api.*;
import za.ac.cput.factory.CarInsuranceFactory;
import za.ac.cput.service.CarInsuranceService;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarInsuranceServiceTest {


    @Autowired
    private CarInsuranceService carInsuranceService;

    private static CarInsurance carInsurance1;
    private static CarInsurance carInsurance2;

    @BeforeEach
    void setUp() {
        carInsurance1 = CarInsuranceFactory.buildCarInsurance("Mv332", "D55", "Pmv6588", "Outsurance");
        assertNotNull(carInsurance1);
        carInsurance2 = CarInsuranceFactory.buildCarInsurance("zv332", "g55", "MMv6588", "Outsurance");
        assertNotNull(carInsurance2);
    }

    @Test
    @Order(1)
    void save() {
        CarInsurance created1 = carInsuranceService.save(carInsurance1);
        assertNotNull(created1);
        System.out.println("Saved CarInsurance 1: " + created1);
        CarInsurance created2 = carInsuranceService.save(carInsurance2);
        assertNotNull(created2);
        System.out.println("Saved CarInsurance 2: " + created2);
    }

    @Test
    @Order(2)
    void read() {
        CarInsurance read = carInsuranceService.read(CarInsurance2.getCarInsuranceId());
        assertNotNull(read);
        System.out.println("Read CarInsurance: " + read);
    }

    @Test
    @Order(3)
    void delete() {
        boolean deleted = CarInsuranceService.delete(carInformation1.getCarInsuranceId());
        assertTrue(deleted);
        System.out.println("Deleted CarCarInsurance 1");
    }

    @Test
    @Order(4)
    void getAll() {
        List<CarInsurance> allCarInsurance = CarInsuranceService.getAll();
        assertNotNull(allCarInsurance);
        assertTrue(allCarInsurance.size() > 0);
        System.out.println("All CarInsurance: " + allCarInsurance);
    }

}
