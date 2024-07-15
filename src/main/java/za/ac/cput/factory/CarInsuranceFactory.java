package za.ac.cput.factory;

import za.ac.cput.domain.CarInsurance;
import za.ac.cput.util.Helper;

public class CarInsuranceFactory {

    public static CarInsurance buildCarInsurance(String insuranceCompany, String policyNumber,
                                                 String coverageType, String coverageAmount) {
        // Validate inputs using Helper utility class
        if (Helper.isNullOrEmpty(insuranceCompany) || Helper.isNullOrEmpty(policyNumber) ||
                Helper.isNullOrEmpty(coverageType) || Helper.isNullOrEmpty(coverageAmount)) {
            return null; // If any required field is null or empty, return null
        }

        // If inputs are valid, use the Builder pattern to create a CarInsurance object
        return new CarInsurance.Builder()
                .setInsuranceCompany(insuranceCompany)
                .setPolicyNumber(policyNumber)
                .setCoverageType(coverageType)
                .setCoverageAmount(coverageAmount)
                .buildCarInsurance(); // Build and return the CarInsurance object
    }
}
