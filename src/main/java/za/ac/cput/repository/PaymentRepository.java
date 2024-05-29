package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    Payment findByPaymentID(String paymentID);

    void deleteByPaymentID(String paymentID);

    List<Payment> getAllPayments();
}
