package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.SupportTicket;

import java.util.List;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, String> {

    SupportTicket findByTicketID(String supportTicketID);

    void deleteByTicketID(String supportTicketID);

    //List<SupportTicket> getAllSupportTickets();
}
