package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarInsuranceFactory;

import static org.junit.jupiter.api.Assertions.*;

public class CarInsuranceFactoryTest {
    @Test
    void buildCarInsurance() {
        CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance("Mv332", "D55", "Outsurance", "Pmv6588");
        assertNotNull(carInsurance);
        System.out.println(carInsurance);
    }

    @Test
    void testBuildCarInsuranceWithFail() {
        CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance("Mv332", "", "Outsurance","Pmv6588");
        assertNotNull(carInsurance);
        System.out.println(carInsurance);
    }
}
