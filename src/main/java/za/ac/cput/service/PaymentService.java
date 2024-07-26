package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment read(Long paymentID) {
        return paymentRepository.findById(paymentID).orElse(null);
    }

    @Transactional
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Transactional
    public void delete(Long paymentID) {
        paymentRepository.deleteById(paymentID);
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }
}
