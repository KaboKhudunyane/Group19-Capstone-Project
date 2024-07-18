package za.ac.cput.factory;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;
import java.time.LocalDate;
public class SupportTicketFactory {
    public static SupportTicket buildSupportTicket(
            User user,
            String subject,
            String message,
            LocalDate dateCreated){
        if( user == null || Helper.isNullOrEmpty(subject) || Helper.isNullOrEmpty(message) || dateCreated == null){
            return null;
        }
        return new SupportTicket.Builder()
                .setUser(user)
                .setSubject(subject)
                .setMessage(message)
                .setDateCreated(dateCreated)
                .build();
    }

}