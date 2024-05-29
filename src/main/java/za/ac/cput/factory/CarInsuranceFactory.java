package za.ac.cput.factory;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.util.Helper;

public class CarInsuranceFactory {

    public static CarInsurance buildCarInsurance(String carInsuranceID, String userID, String carInsuranceName, String policyNumber)
    {
        if (Helper.isNullOrEmpty( carInsuranceID) || Helper.isNullOrEmpty(carInsuranceName) || Helper.isNullOrEmpty(userID) || Helper.isNullOrEmpty(policyNumber))
            return null;

        return new CarInsurance.Builder().setInsuranceID(carInsuranceID).setUserID(userID).setInsuranceName(carInsuranceName).setPolicyNumber(policyNumber).build();
    }
}
