package com.samilemir.service.impl;

import com.samilemir.exception.BaseException;
import com.samilemir.exception.ErrorMessage;
import com.samilemir.exception.MessageType;
import com.samilemir.model.Ticket;
import com.samilemir.repos.TicketRepos;
import com.samilemir.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private TicketRepos ticketRepos;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepos.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepos.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage("Ticket not found with ID: " + id, MessageType.BILET_BULUNAMADI)));
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepos.save(ticket);
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setSeatNumber(ticketDetails.getSeatNumber());
        existingTicket.setAmount(ticketDetails.getAmount());
        existingTicket.setPnrCode(ticketDetails.getPnrCode());
        existingTicket.setCurrencyType(ticketDetails.getCurrencyType());
        existingTicket.setTicketStatus(ticketDetails.getTicketStatus());
        existingTicket.setPassenger(ticketDetails.getPassenger());
        existingTicket.setFlight(ticketDetails.getFlight());
        return ticketRepos.save(existingTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticketRepos.delete(ticket);
    }

    @Override
    public List<Ticket> getTicketsByFlightId(Long flightId) {
        return ticketRepos.findByFlightId(flightId);
    }
}
