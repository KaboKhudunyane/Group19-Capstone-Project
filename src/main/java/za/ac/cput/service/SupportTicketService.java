package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.repository.SupportTicketRepository;

import java.util.List;
@Service
public class SupportTicketService implements IService<SupportTicket, String>{

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
    public SupportTicket read(String SupportTicketId) {
        return repository.findByTicketID(SupportTicketId);
    }
    @Override
    public SupportTicket update(SupportTicket supportTicket) {
        return repository.save(supportTicket);
    }
    public void delete(String supportTicketId) {
        repository.deleteByTicketID(supportTicketId);
    }
    /*public List<SupportTicket> getAllSupportTickets() {
        return repository.getAllSupportTickets();
    }*/



}
