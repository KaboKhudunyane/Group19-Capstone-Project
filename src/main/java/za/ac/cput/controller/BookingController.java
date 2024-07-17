package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.service.BookingService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/save")
    public Booking save(@RequestBody Booking booking) {
        return bookingService.create(booking);
    }

    @GetMapping("/read/{bookingID}")
    public Booking read(@PathVariable Long bookingID) {
        return bookingService.read(bookingID);
    }

    @PostMapping("/update")
    public Booking update(@RequestBody Booking booking) {
        return bookingService.update(booking);
    }

    @DeleteMapping("/delete/{bookingID}")
    public void delete(@PathVariable Long bookingID) {
        bookingService.delete(bookingID);
    }


    @GetMapping("/getAll")
    public List<Booking> getAll() {
        return bookingService.getAll();
    }
}