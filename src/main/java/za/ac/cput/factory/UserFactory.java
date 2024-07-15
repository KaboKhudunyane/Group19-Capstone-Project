package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class UserFactory {

    // Method to create a new User object with validation checks
    public static User createUser(String userID, Name name,
                                  Contact contact, Address address,
                                  Boolean license, String picture) {

        // Validate mandatory fields
        if(Helper.isNullOrEmpty(userID) ||
                Helper.isNullOrEmpty(picture)) {
            return null;  // Return null if any mandatory field is missing
        }

        // Create and return a new User object using the Builder pattern
        return new User.Builder()
                .setUserID(userID)
                .setName(name)
                .setContact(contact)
                .setAddress(address)
                .setLicense(license)
                .setPicture(picture)
                .buildUser();
    }
}
