package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.repository.SupportTicketRepository;
import java.util.List;
@Service
public class SupportTicketService implements IService<SupportTicket, Long> {
    private final SupportTicketRepository repository;
    @Autowired
    public SupportTicketService(SupportTicketRepository repository) {
        this.repository = repository;
    }
    @Override
    public SupportTicket create(SupportTicket supportTicket) {
        return repository.save(supportTicket);
    }
    @Override
    public SupportTicket read(Long supportTicketID) {
        return repository.findByTicketID(supportTicketID);
    }
    @Override
    public SupportTicket update(SupportTicket supportTicket) {
        return repository.save(supportTicket);
    }
    @Override
    public void delete(Long supportTicketID) {
        repository.deleteByTicketID(supportTicketID);
    }
    @Override
    public List<SupportTicket> getAll() {
        return repository.findAll();
    }

    public long countSupportTickets() {
        return repository.countSupportTickets();
    }
}
