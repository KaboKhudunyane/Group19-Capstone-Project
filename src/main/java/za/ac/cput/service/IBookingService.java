package za.ac.cput.service;

import za.ac.cput.domain.Booking;

import java.util.Set;

public interface IBookingService extends IService<Booking, String> {


    Set<Booking> getall();
}

