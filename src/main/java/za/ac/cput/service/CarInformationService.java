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
    private final CarInformationRepository carInformationRepository;
    @Autowired
    public CarInformationService(CarInformationRepository carInformationRepository) {
        this.carInformationRepository = carInformationRepository;
    }
     public CarInformation save(CarInformation carInformation) {
        return carInformationRepository.save(carInformation);
    }
    @Override
    public CarInformation create(CarInformation carInformation) {
        return carInformationRepository.save(carInformation);
    }
    @Override
    public CarInformation read(String carInformationId) {
        return carInformationRepository.findByCarInformationId(carInformationId);
    }
    @Override
    public CarInformation update(CarInformation carInformation) {
        return carInformationRepository.save(carInformation);
    }
    public void delete(String carInformationId) {
        carInformationRepository.deleteByCarInformationId(carInformationId);
        return false;
    }
    public List<CarInformation> getAllCarInformation () {
        return carInformationRepository.getAllCarInformation();
    }
}
