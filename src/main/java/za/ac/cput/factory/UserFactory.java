package za.ac.cput.factory;
import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
public class UserFactory {
    public static User createUser(Account account, Name name,
                                  Contact contact, Address address,
                                  byte[] license, byte[] identityDocument) {
        return new User.Builder().setAccount(account)
                .setName(name)
                .setContact(contact)
                .setAddress(address)
                .setLicense(license)
                .setIdentityDocument(identityDocument)
                .buildUser();
    }
}