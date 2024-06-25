package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.repository.PaymentRepository;

import java.util.List;
@Service
public class PaymentService  implements IService<Payment, String> {
    private BookingRepository bookingRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    PaymentService(BookingRepository bookingRepository, PaymentRepository paymentRepository){
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Payment create(Payment payment) {
        bookingRepository.save(payment.getBookingInfo());
        return paymentRepository.save(payment);
    }
    @Override
    public Payment read(String paymentId) {
        return paymentRepository.findByPaymentId(paymentId);
    }
    @Override
    public Payment update(Payment payment) {

        return paymentRepository.save(payment);
    }

    public void delete(String paymentId){

        this.paymentRepository.deleteByPaymentId(paymentId);
    }

    /*public List<Payment> getAllPayments() {

        return paymentRepository.getAllPayments();
    }*/
}