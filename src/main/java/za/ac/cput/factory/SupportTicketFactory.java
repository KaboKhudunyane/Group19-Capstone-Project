package za.ac.cput.factory;

import za.ac.cput.domain.SupportTicket;
import za.ac.cput.util.Helper;

public class SupportTicketFactory {



    public static SupportTicket buildSupportTicket(String ticketID,
                                                   String userID,
                                                   String subject,
                                                   String description,
                                                   String dateCreated,
                                                   String Status){
        if (Helper.isNullorEmpty( ticketID)
                || Helper.isNullorEmpty(userID)
                || Helper.isNullorEmpty(description)
                || Helper.isNullorEmpty(dateCreated)
                || Helper.isNullorEmpty(Status))
            return null;


        return new SupportTicket().Builder()
                .setTicketID(ticketID)
                .setUserID(userID)
                .setSubject(subject)
                .setDescription(description)
                .setDateCreated(dateCreated)
                .setStatus(Status)
                .build();

    }

}
