package za.ac.cput.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.SupportTicket;


@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, String>{
    SupportTicket findSupportTicketBySupportTicketId(String SupportTicket);

}