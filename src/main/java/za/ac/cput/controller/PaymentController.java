package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Payment;
import za.ac.cput.service.PaymentService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired

    private PaymentService paymentService;

    @PostMapping("/save")
    public Payment save(@RequestBody Payment payment){
        return paymentService.save(payment);
    }

    @GetMapping("/read/{paymentId}")
    public Payment read(@PathVariable String paymentId){
        return paymentService.read(paymentId);
    }

    @DeleteMapping("/delete/{paymentId}")
    public void delete (@PathVariable String paymentId){
        paymentService.delete(paymentId);}


    @GetMapping("/getall")
    public List<Payment> getall(){
        return paymentService.getall();}
}

