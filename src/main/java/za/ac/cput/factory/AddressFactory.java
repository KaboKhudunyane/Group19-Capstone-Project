package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class AddressFactory {

    // Method to create a new Address object with validation checks
    public static Address createAddress(String streetName, String suburb,
                                        String city, String province, String zipCode) {

        // Validate mandatory fields
        if(Helper.isNullOrEmpty(streetName) ||
                Helper.isNullOrEmpty(suburb) ||
                Helper.isNullOrEmpty(city) ||
                Helper.isNullOrEmpty(province) ||
                Helper.isNullOrEmpty(zipCode)) {
            return null;  // Return null if any mandatory field is missing
        }

        // Create and return a new Address object using the Builder pattern
        return new Address.Builder()
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setCity(city)
                .setProvince(province)
                .setZipCode(zipCode)
                .build();
    }
}
