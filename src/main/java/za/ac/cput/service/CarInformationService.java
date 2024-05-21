package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.repository.CarInformationRepository;

import java.util.List;

@Service
public class CarInformationService implements ICarInformationService {

    private final CarInformationRepository repository;

    @Autowired
    public CarInformationService(CarInformationRepository repository) {
        this.repository = repository;
    }

    @Override
    public CarInformation save(CarInformation carInformation) {
        return repository.save(carInformation);
    }

    @Override
    public CarInformation read(String carInformationId) {
        return repository.findCarInformationByCarInformationId(carInformationId);
    }

    @Override
    public boolean delete(String carInformationId) {
        CarInformation carInformation = repository.findCarInformationByCarInformationId(carInformationId);
        if (carInformation != null) {
            repository.delete(carInformation);
            return true;
        }
           return false;
    }
    @Override
    public List<CarInformation> getAll () {
        return repository.findAll();
    }
}

