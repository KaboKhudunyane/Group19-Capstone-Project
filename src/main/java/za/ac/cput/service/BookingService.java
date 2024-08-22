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

    public BookingService(BookingRepository bookingRepository, CarInformationRepository carInformationRepository) {
        this.carInformationRepository = carInformationRepository;
        this.bookingRepository = bookingRepository;
    }


    @Override
    public Booking create(Booking booking) {
        carInformationRepository.save(booking.getCar());
        return bookingRepository.save(booking);

    }


    @Override
    public Booking read(String bookingID) {
        return bookingRepository.findById(bookingID).orElse(null);

    }
    @Override
    public Booking update(Booking booking) {
        carInformationRepository.save(booking.getCar());
        return bookingRepository.save(booking);

    }

    @Override
    public void delete(String bookingID) {
        bookingRepository.deleteById(bookingID);

    }

   @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }
}
