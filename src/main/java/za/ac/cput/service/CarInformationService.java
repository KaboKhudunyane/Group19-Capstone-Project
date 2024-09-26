package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.repository.CarInformationRepository;

import java.util.List;

@Service
public class CarInformationService implements IService<CarInformation, Long> {

    private final CarInformationRepository carInformationRepository;


    @Autowired
    public CarInformationService(CarInformationRepository carInformationRepository) {

        this.carInformationRepository = carInformationRepository;
    }

    @Override
    public CarInformation create(CarInformation carInformation) {
        return carInformationRepository.save(carInformation);
    }

    @Override
    public CarInformation read(Long carInformationID){

        return carInformationRepository.findById(carInformationID).orElse(null);
    }

    @Override
    public CarInformation update(CarInformation carInformation) {
        return carInformationRepository.save(carInformation);
    }

    @Override
    public void delete(Long carInformationID) {
        carInformationRepository.deleteById(carInformationID);

    }

    @Override
     public List<CarInformation> getAll(){

    return carInformationRepository.findAll();
    }
    public long countCars() {
        return carInformationRepository.countCars();
    }

}
