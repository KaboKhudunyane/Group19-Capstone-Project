package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.repository.BookingRepository;
import java.util.List;

@Service
public class BookingService implements IService<Booking, String>{

    private BookingRepository bookingRepository;

    @Autowired
    BookingService(BookingRepository bookingRepository ) {
        this.bookingRepository = bookingRepository;
    }
    @Override
    public Booking create(Booking booking){
        return bookingRepository.save(booking);
    }
    @Override
    public Booking read(String bookingId){
        return bookingRepository.findByBookingID(bookingId);
    }
    @Override
    public Booking update(Booking booking){
        return this.bookingRepository.save(booking);
    }
    public void delete(String bookingId){
        this.bookingRepository.deleteById(bookingId);
    }
    public List<Booking> getAllBookings(){
        return bookingRepository.getAllBookings();

    }
}
