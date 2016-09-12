package com.airline.domain.webservice.tickets;

import com.airline.domain.ticket.Ticket;

import java.util.List;

/**
 * Created by JJ on 9/7/16.
 */
public class Tickets {
    private List<Ticket> adultTickets;
    private List<Ticket> childTickets;
    private List<Ticket> infantTickets;

    public List<Ticket> getAdultTickets() {
        return adultTickets;
    }

    public void setAdultTickets(List<Ticket> adultTickets) {
        this.adultTickets = adultTickets;
    }

    public List<Ticket> getChildTickets() {
        return childTickets;
    }

    public void setChildTickets(List<Ticket> childTickets) {
        this.childTickets = childTickets;
    }

    public List<Ticket> getInfantTickets() {
        return infantTickets;
    }

    public void setInfantTickets(List<Ticket> infantTickets) {
        this.infantTickets = infantTickets;
    }
}
