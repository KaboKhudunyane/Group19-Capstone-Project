package za.ac.cput.factory;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.util.Helper;

public class SupportTicketFactory {

    public static SupportTicket createSupportTicket(String ticketID,
                                                   String userID,
                                                   String subject,
                                                   String description,
                                                   String dateCreated,
                                                   String Status){
        if (Helper.isNullOrEmpty( ticketID)
                || Helper.isNullOrEmpty(userID)
                || Helper.isNullOrEmpty(description)
                || Helper.isNullOrEmpty(dateCreated)
                || Helper.isNullOrEmpty(Status))
            return null;

        return new SupportTicket.Builder()
                .setTicketID(ticketID)
                .setUserID(userID)
                .setSubject(subject)
                .setDescription(description)
                .setDateCreated(dateCreated)
                .setStatus(Status)
                .buildSupportTicket();
    }
}