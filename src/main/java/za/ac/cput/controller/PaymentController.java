package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Payment;
import za.ac.cput.service.PaymentService;
import java.util.List;
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/create")
    public Payment save(@RequestBody Payment payment) {

        return paymentService.create(payment);
    }
    @GetMapping("/read/{paymentID}")
    public Payment read(@PathVariable Long paymentID) {

        return paymentService.read(paymentID);
    }
    @PutMapping("/update")
    public Payment update(@RequestBody Payment payment) {

        return paymentService.update(payment);
    }
    @DeleteMapping("/delete/{paymentID}")
    public void delete(@PathVariable Long paymentID) {

        paymentService.delete(paymentID);
    }
    @GetMapping("/getAll")
    public List<Payment> getAllPayments() {

        return paymentService.getAll();
    }
}