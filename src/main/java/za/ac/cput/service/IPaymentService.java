package za.ac.cput.service;

import za.ac.cput.domain.Payment;

import java.util.List;


public interface IPaymentService extends IService<Payment, String>{


    List<Payment> getall();
}
