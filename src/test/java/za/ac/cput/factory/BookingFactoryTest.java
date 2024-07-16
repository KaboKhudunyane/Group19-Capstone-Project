package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookingFactoryTest {
    CarInformation carInformation = CarInformationFactory.buildCarInformation("11", "BMW", "M4",
            "2018", "CA123-456", "M performance");
    CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance("Mv332", "D55", "Outsurance", "Pmv6588");
    Car car = CarFactory.buildCar(carInformation, carInsurance, "150", "Available", "New".getBytes());

    Booking booking = BookingFactory.buildBooking("b101", car,"20-June-2024","10 Hanover street, Cape Town, 8001",
            "10 Hanover street, Cape Town, 8001","Blue BMW M4(Manual)", "2400",2500.0);

    @Test
    void testBuildbooking(){

        assertNotNull(carInformation);
        System.out.println(carInformation);

        assertNotNull(booking);
        System.out.println(booking);
    }

    @Test
    void testBuildBookingForPayment(){


        assertNotNull(booking);
        System.out.println(booking);
    }

    @Test
    void testBuildBookingWithFail(){
        assertNotNull(carInformation);
        System.out.println(carInformation);
        assertNotNull(booking);
        System.out.println(booking);
    }

}
