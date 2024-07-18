package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.repository.CarInsuranceRepository;


import java.util.List;

@Service
public class CarInsuranceService implements IService<CarInsurance, String>{

    private CarInsuranceRepository carInsuranceRepository;

    @Autowired
    CarInsuranceService(CarInsuranceRepository carInsuranceRepository){
              this.carInsuranceRepository = carInsuranceRepository;
    }
    @Override
    public CarInsurance create(CarInsurance carInsurance) {
        return carInsuranceRepository.save(carInsurance);
    }
    @Override
    public CarInsurance read(String carInsuranceId) {
        return carInsuranceRepository.findByCarInsuranceID(carInsuranceId);
    }
    @Override
    public CarInsurance update(CarInsurance carInsurance){
        return carInsuranceRepository.save(carInsurance);
    }
    public void delete(String carInsuranceId) {
        carInsuranceRepository.deleteByCarInsuranceID(carInsuranceId);
    }
    public List<CarInsurance> getAll () {
        return carInsuranceRepository.findAll();
    }
}