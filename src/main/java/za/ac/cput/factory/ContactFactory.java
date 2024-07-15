package za.ac.cput.factory;

import za.ac.cput.domain.Contact;
import za.ac.cput.util.Helper;

public class ContactFactory {

    // Method to create a new Contact object with validation checks
    public static Contact createContact(String email, String mobileNumber) {

        // Validate mandatory fields
        if(Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(mobileNumber)) {
            return null;  // Return null if any mandatory field is missing
        }

        // Create and return a new Contact object using the Builder pattern
        return new Contact.Builder()
                .setEmail(email)
                .setMobileNumber(mobileNumber)
                .buildContact();
    }
}
