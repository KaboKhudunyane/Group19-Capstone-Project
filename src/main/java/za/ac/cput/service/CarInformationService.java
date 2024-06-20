package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.repository.CarInformationRepository;

import java.util.List;

@Service
public class CarInformationService implements IService<CarInformation, String> {

    private CarInformationRepository carInformationRepository;

    @Autowired
    public CarInformationService(CarInformationRepository carInformationRepository) {
        this.carInformationRepository = carInformationRepository;
    }

    @Override
    public CarInformation read(String carInformationId) {
        return carInformationRepository.findCarInformationByCarInformationId(carInformationId);
    }

    public void delete(String carInformationId) {
        CarInformation carInformation = carInformationRepository.findCarInformationByCarInformationId(carInformationId);
        if (carInformation != null) {
            carInformationRepository.delete(carInformation);
        }
    }

    @Override
    public CarInformation create(CarInformation carInformation) {
        return carInformationRepository.save(carInformation);
    }

    @Override
    public CarInformation update(CarInformation carInformation) {
        return carInformationRepository.save(carInformation);
    }

    public List<CarInformation> getAllCarInformation() {
        return carInformationRepository.findAll();
    }

}
