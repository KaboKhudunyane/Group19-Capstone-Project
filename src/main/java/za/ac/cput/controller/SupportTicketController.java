package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInsurance;
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
    public SupportTicket read(@PathVariable String id){return supportTicketService.read(id);}

    @PostMapping
    public SupportTicket update(@RequestBody CarInsurance carInsurance){return supportTicketService.update(carInsurance);}

    @DeleteMapping
    public void delete(@PathVariable String id){supportTicketService.delete(id);}

    @GetMapping
    public List<SupportTicket> getAll(){return supportTicketService.getAll();}
}
