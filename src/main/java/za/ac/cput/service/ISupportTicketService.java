package za.ac.cput.service;

import za.ac.cput.domain.SupportTicket;

import java.util.List;

public interface ISupportTicketService extends IService<SupportTicket, String> {
    List<SupportTicket> getAll();

}
