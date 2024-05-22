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
    public SupportTicket save(SupportTicket supportTicket) {
        return repository.save(supportTicket);
    }

    @Override
    public SupportTicket read(String SupportTicketId) {
        return repository.findSupportTicketBySupportTicketId(SupportTicketId);
    }

    @Override
    public boolean delete(String supportTicketId) {
        SupportTicket supportTicket = repository.findSupportTicketBySupportTicketId(supportTicketId);
        if (supportTicket != null) {
            repository.delete(supportTicket);
            return true;
        }
        return false;
    }
    @Override
    public List<SupportTicket> getAll () {
        return repository.findAll();
    }



}
