package za.ac.cput.factory;
import za.ac.cput.domain.Account;
import za.ac.cput.util.Helper;
public class AccountFactory {
    public static Account createAccount(String username, String password ) {

        if(Helper.isNullOrEmpty(username) ||
                Helper.isNullOrEmpty(password))
            return null;

        return new Account.Builder()
                .setUsername(username)
                .setPassword(password)
                .buildAccount();
    }
}
