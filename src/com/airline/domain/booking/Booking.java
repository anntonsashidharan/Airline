package com.airline.domain.booking;

import com.airline.domain.agency.Agency;
import com.airline.domain.passenger.Passenger;
import com.airline.domain.payment.Payment;
import com.airline.domain.schedule.Schedule;
import com.airline.domain.ticket.Ticket;
import com.airline.webservice.Enums;

import java.util.Date;
import java.util.List;

/**
 * Created by JJ on 4/13/16.
 */
public class Booking {
    private String bookingNumber;
    private Payment payment;
    private Enums.TravelClass travelClass;
    private Date bookingDate;
    private Date bookingTime;
    private Schedule schedule;
    private Agency agency;
    /*private List<Passenger> adultPassengers;
    private List<Passenger> childPassengers;
    private List<Passenger> infantPassengers;*/
    private List<Ticket> tickets;


    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Enums.TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(Enums.TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
