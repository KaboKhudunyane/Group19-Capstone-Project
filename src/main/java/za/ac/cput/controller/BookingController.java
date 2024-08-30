package za.ac.cput.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.CarInformationService;

import java.util.List;


@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController {

    private BookingService bookingService;
    private CarInformationService carInformationService;

    @Autowired
    public BookingController(BookingService bookingService, CarInformationService carInformationService) {
        this.carInformationService = carInformationService;
        this.bookingService = bookingService;
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
}