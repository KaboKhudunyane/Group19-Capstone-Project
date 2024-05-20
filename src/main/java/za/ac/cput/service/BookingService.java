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
    private BookingRepository bookingRepository;
    private CarInformationRepository carInformationRepository;

    @Autowired
    BookingService(BookingRepository bookingRepository, CarInformationRepository carInformationRepository) {
        this.bookingRepository = bookingRepository;
        this.carInformationRepository = carInformationRepository;
    }


    @Override
    public Booking create(Booking booking){
        CarInformationRepository.save(booking.getCarInformation());
        return bookingRepository.save(booking);

    }



    @Override
    public Booking read(String bookingId){
        return this.bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public void delete(String bookingId){bookingRepository.deleteById(bookingId);}


    @Override
    public Set<Booking> getall(){
        return bookingRepository.findAll().stream().collect(Collectors.toSet());

    }

}
