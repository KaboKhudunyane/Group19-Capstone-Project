package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.service.SupportTicketService;
import java.util.List;
@RestController
@RequestMapping
public class SupportTicketController {
    @Autowired
    private SupportTicketService supportTicketService;
    @PostMapping
    public SupportTicket create(@RequestBody SupportTicket supportTicket){return supportTicketService.create(supportTicket);}
    @GetMapping
    public SupportTicket read(@PathVariable String supportTicketID){return supportTicketService.read(supportTicketID);}
    @PostMapping
    public SupportTicket update(@RequestBody SupportTicket supportTicket){return supportTicketService.update(supportTicket);}
    @DeleteMapping
    public void delete(@PathVariable String id){supportTicketService.delete(id);}
    @GetMapping
    public List<SupportTicket> getAll(){return supportTicketService.getAllSupportTickets();}
}