package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.SupportTicket;

import java.util.List;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, String> {

    SupportTicket findBySupportTicketID(String supportTicketID);

    void deleteBySupportTicketID(String supportTicketID);

    List<SupportTicket> getAllSupportTickets();
}
