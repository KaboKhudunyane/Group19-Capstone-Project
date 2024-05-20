package za.ac.cput.factory;

import za.ac.cput.domain.CarInsurance;
import za.ac.cput.util.Helper;

public class CarInsuranceFactory {

    public CarInsuranceFactory(){}

    public static CarInsurance buildCarInsurance(String insuranceID,
                                                 String insuranceName,
                                                 String userID,
                                                 String policyNumber)
    {
        if (Helper.isNullorEmpty( insuranceID)
                || Helper.isNullorEmpty(insuranceName)
                || Helper.isNullorEmpty(userID)
                || Helper.isNullorEmpty(policyNumber))
            return null;

        return new CarInsurance().Builder()
                .setInsuranceID(insuranceID)
                .setInsuranceName(insuranceName)
                .setUserID(userID)
                .setPolicyNumber(policyNumber)
                .build();
    }

}
