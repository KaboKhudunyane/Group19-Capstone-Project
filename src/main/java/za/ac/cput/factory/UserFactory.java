package za.ac.cput.factory;
import za.ac.cput.domain.*;
public class UserFactory {
    public static User createUser(User.Role role, String username, String password, Name name,
                                  Contact contact, Address address,
                                  byte[] license, byte[] identityDocument
                                  ) {
        return new User.Builder()
                .setRole(role)
                .setUsername(username)
                .setPassword(password)
                .setName(name)
                .setContact(contact)
                .setAddress(address)
                .setLicense(license)
                .setIdentityDocument(identityDocument)
                .buildUser();
    }
}
