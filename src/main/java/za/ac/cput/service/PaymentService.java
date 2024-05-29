package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.PaymentRepository;

import java.util.List;
@Service
public class PaymentService  implements IService<Payment, String> {
    private PaymentRepository paymentRepository;

    @Autowired
    PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }
    @Override
    public Payment read(String paymentId) {
        return paymentRepository.findByPaymentID(paymentId);
    }
    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }
    public void delete(String paymentID){
        this.paymentRepository.deleteByPaymentID(paymentID);
    }
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}