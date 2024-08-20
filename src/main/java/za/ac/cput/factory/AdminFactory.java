package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;

public class AdminFactory {
    public static Admin bluidAdmin(Name name, Contact contact){
        if(Helper.isNullOrEmpty(name.getFirstName())||
                Helper.isNullOrEmpty(name.getLastName())
                ||Helper.isNullOrEmpty(name.getMiddleName())
                ||Helper.isNullOrEmpty(contact.getEmail())
                ||Helper.isNullOrEmpty(contact.getMobileNumber())){
            return null;
        }
        return new Admin.Builder()
                .setName(name)
                .setContact(contact)
                .buildAdmin();
    }
}
