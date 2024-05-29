package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.User;
import za.ac.cput.repository.CarInformationRepository;

import java.util.List;

@Service
public class CarInformationService implements IService<CarInformation, String> {

    private final CarInformationRepository repository;

    @Autowired
    public CarInformationService(CarInformationRepository repository) {
        this.repository = repository;
    }
    @Override
    public CarInformation create(CarInformation carInformation) {
        return repository.save(carInformation);
    }
    @Override
    public CarInformation read(String carInformationId) {
        return repository.findByCarInformationId(carInformationId);
    }
    @Override
    public CarInformation update(CarInformation carInformation) {
        return repository.save(carInformation);
    }
    void delete(String carInformationId) {
        repository.deleteByCarInformationId(carInformationId);
    }
    public List<CarInformation> getAll () {
        return repository.getAllCarInformation();
    }
}