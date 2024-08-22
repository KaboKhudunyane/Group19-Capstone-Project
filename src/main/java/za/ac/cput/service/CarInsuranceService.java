package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.repository.CarInsuranceRepository;

import java.util.List;

@Service
public class CarInsuranceService implements IService<CarInsurance, Long> {

        CarInsuranceRepository carInsuranceRepository;

        @Autowired
        public CarInsuranceService( CarInsuranceRepository carInsuranceRepository) {

            this.carInsuranceRepository = carInsuranceRepository;
        }

        @Override
        public CarInsurance create(CarInsurance carInsurance) {

            return carInsuranceRepository.save(carInsurance);
        }

        @Override
        public CarInsurance read(Long carInsuranceID){

            return carInsuranceRepository.findById(carInsuranceID).orElse(null);
        }

    @Override
    public CarInsurance update(CarInsurance carInsurance) {

        return carInsuranceRepository.save(carInsurance);
    }

    @Override
    public void delete(Long carInsuranceID) {
        carInsuranceRepository.deleteById(carInsuranceID);

    }

  @Override
  public List<CarInsurance> getAll(){

            return carInsuranceRepository.findAll();
        }


    }


