package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.repository.CarInformationRepository;
import za.ac.cput.repository.PaymentRepository;

import java.util.List;
@Service
public class BookingService implements IService<Booking, Long>{
    private final BookingRepository repository;
    @Autowired
    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }
    @Override
    public Booking create(Booking booking) {
        return repository.save(booking);
    }
    @Override
    public Booking read(Long bookingID) {
        return repository.findByBookingID(bookingID);
    }
    @Override
    public Booking update(Booking booking) {
        return repository.save(booking);
    }
    @Override
    public void delete(Long bookingID) {
        repository.deleteByBookingID(bookingID);
    }
    @Override
    public List<Booking> getAll() {
        return repository.findAll();
    }
}