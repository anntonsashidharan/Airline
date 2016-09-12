package com.airline.service.reservation;

import com.airline.dao.booking.BookingDAO;
import com.airline.dao.passenger.PassengerDAO;
import com.airline.dao.payment.PaymentDAO;
import com.airline.dao.schedule.ScheduleDAO;
import com.airline.dao.ticket.TicketDAO;
import com.airline.domain.agency.Agency;
import com.airline.domain.booking.Booking;
import com.airline.domain.passenger.Passenger;
import com.airline.domain.payment.Payment;
import com.airline.domain.schedule.Schedule;
import com.airline.domain.ticket.Ticket;
import com.airline.domain.webservice.tickets.Tickets;
import com.airline.util.db.Transaction;
import com.airline.webservice.Enums;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JJ on 8/30/16.
 */
public class ReservationService {

    public static Tickets reserveTicket(Schedule schedule, List<Passenger> adultPassengers, List<Passenger> childPassengers, List<Passenger> infantPassengers, Enums.TravelClass travelClass, Agency agency) throws Exception {
        Tickets tickets = new Tickets();
        List<Ticket> adultTickets = new ArrayList<Ticket>();
        List<Ticket> childTickets = new ArrayList<Ticket>();
        List<Ticket> infantTickets = new ArrayList<Ticket>();

        Connection connection = null;
        try {
            connection = Transaction.beginTransaction();

            PassengerDAO passengerDAO = new PassengerDAO(connection);
            TicketDAO ticketDAO = new TicketDAO(connection);
            ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            BookingDAO bookingDAO = new BookingDAO(connection);


            int availableSeats = scheduleDAO.getNumberOfAvailableSeats(schedule.getScheduleId(), travelClass);


            if (availableSeats < adultPassengers.size() + childPassengers.size()) {
                throw new Exception("No sufficient seats available for this booking");
            }

            //validate payment

            //if valid

            Payment payment = new Payment();
            paymentDAO.createPayment(payment);


            Booking booking = new Booking();
            booking.setPayment(payment);
            booking.setAgency(agency);
            booking.setSchedule(schedule);
            booking.setTravelClass(travelClass);
            bookingDAO.createBooking(booking);


            for (Passenger passenger : adultPassengers) {
                Ticket ticket = new Ticket();
                ticket.setBooking(booking);
                ticket.setPassenger(passenger);
                int seatNumber = scheduleDAO.reserveAndGetSeat(schedule.getScheduleId(), travelClass);
                ticket.setSeatNumber(seatNumber);
                passengerDAO.createPassenger(passenger);
                ticketDAO.createTicket(ticket);
                adultTickets.add(ticket);
            }

            for (Passenger passenger : childPassengers) {
                Ticket ticket = new Ticket();
                ticket.setBooking(booking);
                ticket.setPassenger(passenger);
                ticket.setParentTicket(adultTickets.get(0));
                int seatNumber = scheduleDAO.reserveAndGetSeat(schedule.getScheduleId(), travelClass);
                ticket.setSeatNumber(seatNumber);
                passengerDAO.createPassenger(passenger);
                ticketDAO.createTicket(ticket);
                childTickets.add(ticket);
            }
            for (Passenger passenger : infantPassengers) {
                Ticket ticket = new Ticket();
                ticket.setBooking(booking);
                ticket.setPassenger(passenger);
                ticket.setParentTicket(adultTickets.get(0));
                //int seatNumber = scheduleDAO.reserveAndGetSeat(schedule.getScheduleId(), travelClass);
                //ticket.setSeatNumber(seatNumber);
                passengerDAO.createPassenger(passenger);
                ticketDAO.createTicket(ticket);
                infantTickets.add(ticket);
            }

            tickets.setAdultTickets(adultTickets);
            tickets.setChildTickets(childTickets);
            tickets.setInfantTickets(infantTickets);
        } finally {
            Transaction.endTransaction(connection);
        }
        return tickets;
    }


    public static Ticket cancelTicket(Ticket ticket){
        Ticket ticket1 = new Ticket();
        return ticket1;
    }

}
