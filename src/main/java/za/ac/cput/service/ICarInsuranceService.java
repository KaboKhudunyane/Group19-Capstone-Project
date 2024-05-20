package za.ac.cput.service;

import za.ac.cput.domain.CarInsurance;

import java.util.List;

public interface ICarInsuranceService extends IService<CarInsurance, String>{
    List<CarInsurance> getAll();
}
