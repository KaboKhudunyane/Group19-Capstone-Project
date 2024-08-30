package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.service.SupportTicketService;
import java.util.List;
@RestController
@RequestMapping("api/supportTicket")
@CrossOrigin
public class SupportTicketController {
    @Autowired
    private SupportTicketService supportTicketService;
    @PostMapping("/create")
    public SupportTicket create(@RequestBody SupportTicket supportTicket) {
        return supportTicketService.create(supportTicket);
    }
    @GetMapping("/read/{ticketID}")
    public SupportTicket read(@PathVariable Long ticketID) {
        return supportTicketService.read(ticketID);
    }
    @PutMapping("/update")
    public SupportTicket update(@RequestBody SupportTicket supportTicket) {
        return supportTicketService.update(supportTicket);
    }
    @DeleteMapping("/delete/{ticketID}")
    public void delete(@PathVariable Long ticketID) {
        supportTicketService.delete(ticketID);
    }
    @GetMapping("/getAll")
    public List<SupportTicket> getAll() {
        return supportTicketService.getAll();
    }


}
