package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.repository.CarInformationRepository;

import java.util.List;
@Service
public class BookingService implements IService<Booking, String>{

    private CarInformationRepository carInformationRepository;
    private BookingRepository bookingRepository;

    @Autowired
    BookingService(CarInformationRepository carInformationRepository, BookingRepository bookingRepository ) {

        this.carInformationRepository = carInformationRepository;
        this.bookingRepository = bookingRepository;
    }
    @Override
    public Booking create(Booking booking){
        carInformationRepository.save(booking.getCarInformation());
        return bookingRepository.save(booking);
    }
    @Override
    public Booking read(String bookingId){

        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public Booking update(Booking booking){

        return bookingRepository.save(booking);
    }

    public void delete(String bookingId){

        this.bookingRepository.deleteById(bookingId);
    }

    /*public List<Booking> getAllBookings(){
        return bookingRepository.getAllBookings();

    }*/
}
