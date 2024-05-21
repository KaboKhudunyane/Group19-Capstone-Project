package za.ac.cput.service;

import za.ac.cput.domain.Booking;

public interface IService <T, ID>{

    T save(T t);
    ID read (ID id);
    boolean delete(ID id);

}