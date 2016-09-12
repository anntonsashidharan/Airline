package com.airline.domain.ticket;

import com.airline.domain.booking.Booking;
import com.airline.domain.passenger.Passenger;
import com.airline.webservice.Enums;

import java.util.List;

/**
 * Created by JJ on 4/13/16.
 */
public class Ticket {
    private String ticketNumber;
    private Enums.TicketStatus status;
    private int seatNumber;
    private Passenger passenger;
    private Booking booking;
    private Ticket parentTicket;

    public Ticket getParentTicket() {
        return parentTicket;
    }

    public void setParentTicket(Ticket parentTicket) {
        this.parentTicket = parentTicket;
    }

    public Booking getBooking() {

        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Enums.TicketStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.TicketStatus status) {
        this.status = status;
    }
}
