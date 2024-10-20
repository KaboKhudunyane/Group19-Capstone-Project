package za.ac.cput.factory;

import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class CarInsuranceFactory {

    public static CarInsurance buildCarInsurance(String insuranceCompany, int policyNumber,
                                                 String coverageType, double coverageAmount, User user, CarInformation carInformation) {



        return new CarInsurance.Builder()
                .setInsuranceCompany(insuranceCompany)
                .setPolicyNumber(policyNumber)
                .setCoverageType(coverageType)
                .setCoverageAmount(coverageAmount)
                .setUser(user)
                .setCarInformation(carInformation)
                .buildCarInsurance();
    }
}
