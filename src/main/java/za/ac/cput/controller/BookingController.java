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
        public Booking save(@RequestBody Booking booking){
            return bookingService.create(booking);
         }

        @GetMapping("/read/{bookingId}")
        public Booking read(@PathVariable String bookingId){
            return bookingService.read(bookingId);
        }

    @PostMapping("/update")
    public Booking update(@RequestBody Booking booking){
        return bookingService.update(booking);
    }

    @DeleteMapping("/delete/{bookingId}")
        public void delete (@PathVariable String bookingId){
            bookingService.delete(bookingId);}


       /* @GetMapping("/getAllBookings")
        public List<Booking> getAllBookings(){
            return bookingService.getAllBookings();}*/
    }


