
package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.User;
import za.ac.cput.enums.UserRole;

public class UserFactory {
    public static User createUser(String firstName, String lastName, String username, String password, UserRole userRole,
                                  String cellNumber, String email, Address address,
                                  byte[] license, byte[] identityDocument) {


        return new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setPassword(password)
                .setUserRole(userRole)
                .setCellNum(cellNumber)
                .setEmail(email)
                .setAddress(address)
                .setLicense(license)
                .setIdentityDocument(identityDocument)
                .build();
    }
}
