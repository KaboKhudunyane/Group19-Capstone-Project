package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Promotion;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Booking findByBookingID(String bookingID);

    void deleteByBookingID(String bookingID);

    List<Booking> getAllBookings();
}

