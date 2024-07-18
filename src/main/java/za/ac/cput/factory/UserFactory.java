package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class UserFactory {

    public static User createUser(Name name,
                                  Contact contact, Address address,
                                  Boolean license, byte[] picture) {


        return new User.Builder()
                .setName(name)
                .setContact(contact)
                .setAddress(address)
                .setLicense(license)
                .setPicture(picture)
                .buildUser();
    }
}
