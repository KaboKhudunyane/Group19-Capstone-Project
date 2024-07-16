package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.repository.PaymentRepository;
import za.ac.cput.repository.ReviewRepository;

import java.util.List;
@Service
public class PaymentService  implements IService<Payment, Long> {
    private final PaymentRepository repository;
    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }
    @Override
    public Payment create(Payment payment) {
        return repository.save(payment);
    }
    @Override
    public Payment read(Long paymentID) {
        return repository.findByPaymentID(paymentID);
    }
    @Override
    public Payment update(Payment payment) {
        return repository.save(payment);
    }
    @Override
    public void delete(Long paymentID) {
        repository.deleteByPaymentID(paymentID);
    }
    @Override
    public List<Payment> getAll() {
        return repository.findAll();
    }
}