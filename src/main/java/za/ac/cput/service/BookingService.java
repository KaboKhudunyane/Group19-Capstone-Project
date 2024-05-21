package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.repository.CarInformationRepository;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class BookingService implements IBookingService{
    private CarInformationRepository carInformationRepository;

    private BookingRepository bookingRepository;

    @Autowired
    BookingService(CarInformationRepository carInformationRepository, BookingRepository bookingRepository ) {
        this.carInformationRepository = carInformationRepository;
        this.bookingRepository = bookingRepository;

    }


    @Override
    public Booking save(Booking booking){
        carInformationRepository.save(booking.getCarInformation());
        return bookingRepository.save(booking);

    }



    @Override
    public Booking read(String bookingId){
        return this.bookingRepository.findBookingByBookingId(bookingId);
    }

    @Override
    public boolean delete(String bookingId) {
        Booking booking = bookingRepository.findBookingByBookingId(bookingId);
        if (booking != null) {
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }

    @Override
    public Set<Booking> getall(){
        return bookingRepository.findAll().stream().collect(Collectors.toSet());

    }

}
