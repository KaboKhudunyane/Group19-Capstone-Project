package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Account;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;

public class AdminFactory {

    public static Admin buildAdmin(Name name, Contact contact, Account account) {
        if (Helper.isNullOrEmpty(name.getFirstName()) ||
                Helper.isNullOrEmpty(name.getLastName()) ||
                Helper.isNullOrEmpty(contact.getEmail()) ||
                Helper.isNullOrEmpty(contact.getMobileNumber()) ||
                Helper.isNullOrEmpty(account.getUsername()) ||
                Helper.isNullOrEmpty(account.getPassword())) {
            return null;
        }

        return new Admin.Builder()
                .setName(name)
                .setContact(contact)
                .setAccount(account)  // Set the account object with username and password
                .buildAdmin();
    }
}
