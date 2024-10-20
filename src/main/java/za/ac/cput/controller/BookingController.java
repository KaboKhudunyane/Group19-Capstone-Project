package za.ac.cput.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.User;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.CarInformationService;
import za.ac.cput.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController {

    private BookingService bookingService;
    private CarInformationService carInformationService;
    private UserService userService;

    @Autowired
    public BookingController(BookingService bookingService, CarInformationService carInformationService, UserService userService) {
        this.carInformationService = carInformationService;
        this.bookingService = bookingService;
        this.userService = userService;
    }



    @PostMapping("/create")
    public Booking create(@RequestBody Booking booking){
        carInformationService.read(booking.getCar().getCarInformationID());
        return bookingService.create(booking);
    }


    @GetMapping("/csrfToken")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/getall")
    public List<Booking> getAll(){
        return bookingService.getAll();
    }

    @GetMapping("/user/{userID}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userID) {
     return bookingService.getBookingsByUserId(userID);
    }
}