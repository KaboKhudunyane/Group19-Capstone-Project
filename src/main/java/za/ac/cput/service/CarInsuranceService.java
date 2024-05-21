package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.repository.CarInsuranceRepository;


import java.util.List;

  @Service
public class CarInsuranceService implements IService<CarInsurance, String>{

    private final CarInsuranceRepository repository;

    @Autowired
          public CarInsuranceService(CarInsuranceRepository repository) {
              this.repository = repository;
          }
          @Override
          public CarInsurance save(CarInsurance carInsurance) {
              return repository.save(carInsurance);
          }

          @Override
          public CarInsurance read(String carInsuranceId) {
              return repository.findCarInsuranceByCarInsuranceId(carInsuranceId);
          }

          @Override
          public boolean delete(String carInsuranceId) {
              CarInsurance carInsurance = repository.findCarInsuranceByCarInsuranceId(carInsuranceId);
              if (carInsurance != null) {
                  repository.delete(carInsurance);
                  return true;
              }
              return false;
          }
          @Override
          public List<CarInsurance> getAll () {
              return repository.findAll();
          }

}
