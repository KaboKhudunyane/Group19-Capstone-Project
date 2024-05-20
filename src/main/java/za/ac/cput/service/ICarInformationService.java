package za.ac.cput.service;

import za.ac.cput.domain.CarInformation;

import java.util.List;

public interface ICarInformationService extends IService<CarInformation, String> {
    List<CarInformation>getAll();
}
