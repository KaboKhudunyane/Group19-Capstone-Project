package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.SupportTicket;
import java.util.List;
@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    SupportTicket findByTicketID(Long supportTicketID);
    void deleteByTicketID(Long supportTicketID);

    @Query("SELECT COUNT(st) FROM SupportTicket st")
    long countSupportTickets();
}