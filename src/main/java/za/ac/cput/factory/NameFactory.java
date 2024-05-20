package za.ac.cput.factory;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;
public class NameFactory {
    public static Name createName(String firstName, String middleName, String lastName) {

        if(Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(middleName) ||
                Helper.isNullOrEmpty(lastName))
            return null;

        return new Name.Builder()
                .setFirstName(firstName)
                .setMiddleName(middleName)
                .setLastName(lastName)
                .buildName();
    }
}
