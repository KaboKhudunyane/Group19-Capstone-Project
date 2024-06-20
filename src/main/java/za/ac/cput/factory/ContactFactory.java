package za.ac.cput.factory;
import za.ac.cput.domain.Contact;
import za.ac.cput.util.Helper;
public class ContactFactory {

    public static Contact createContact(String contactId, String email, int mobileNo) {
        if (Helper.isNullOrEmpty(contactId)||
                Helper.isNullOrEmpty(email)||
                mobileNo < 0) {
          return null;
        }
        return new Contact.Builder()
                .setContactId(contactId)
                .setEmail(email)
                .setMobileNo(mobileNo)
                .buildContact();
    }
}