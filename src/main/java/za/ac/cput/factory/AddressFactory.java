package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class AddressFactory {

    public static Address createAddress(String addressId, String userId, String streetName, String suburb, int postalCode) {
        if (Helper.isNullOrEmpty(addressId)||
                Helper.isNullOrEmpty(userId)||
                Helper.isNullOrEmpty(streetName)||
                Helper.isNullOrEmpty(suburb)||
                postalCode < 0) {
           // throw new IllegalArgumentException("Invalid address ID");
            return null;
        }
        return new Address.Builder()
                .setAddressId(addressId)
                .setUserId(userId)
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setPostalCode(postalCode)
                .build();
    }
}

