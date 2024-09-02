package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;

public class AdminFactory {
    public static Admin buildAdmin(Name name, Contact contact, String username, String password) {
        // Validate that none of the essential fields are null or empty
        if (Helper.isNullOrEmpty(name.getFirstName()) ||
                Helper.isNullOrEmpty(name.getLastName()) ||
                Helper.isNullOrEmpty(name.getMiddleName()) ||
                Helper.isNullOrEmpty(contact.getEmail()) ||
                Helper.isNullOrEmpty(contact.getMobileNumber()) ||
                Helper.isNullOrEmpty(username) ||  // Validate username
                Helper.isNullOrEmpty(password)) {
            return null;
        }

        // Use the builder to create and return an Admin object
        return new Admin.Builder()
                .setName(name)
                .setContact(contact)
                .setUsername(username)  // Set the username in the builder
                .setPassword(password)
                .buildAdmin();
    }
}
