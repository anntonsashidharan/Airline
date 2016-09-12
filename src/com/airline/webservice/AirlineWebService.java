package com.airline.webservice;

import com.airline.domain.agency.Agency;
import com.airline.domain.airport.Airport;
import com.airline.domain.booking.Booking;
import com.airline.domain.passenger.Passenger;
import com.airline.domain.schedule.Schedule;
import com.airline.domain.ticket.Ticket;
import com.airline.domain.webservice.tickets.Tickets;
import com.airline.service.reservation.ReservationService;
import com.airline.service.schedule.ScheduleService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JJ on 4/24/16.
 */
@WebService(name = "Airline4", serviceName = "Airline4")
public class AirlineWebService {
    /**
     * @param source
     * @param destination
     * @param departure
     * @param numberOfSeats
     * @param travelClass
     * @return
     */
    @WebMethod
    public List<Schedule> getScheduleList(String source, String destination, Date departure, Date minTime, Date maxTime, int numberOfSeats, Enums.TravelClass travelClass) throws Exception {
        List<Schedule> schedules = ScheduleService.getSchedules(source, destination, departure, minTime, maxTime, numberOfSeats, travelClass);
        return schedules;
    }

    @WebMethod
    public Tickets reserveTickets(Schedule schedule, List<Passenger> adultPassengers,List<Passenger> childPassengers,List<Passenger> infantPassengers, Enums.TravelClass travelClass,Agency agency) throws Exception {
        Tickets tickets = ReservationService.reserveTicket(schedule,adultPassengers,childPassengers,infantPassengers,travelClass,agency);
        return tickets;
    }

    @WebMethod
    public Ticket cancelTicket(Ticket ticket) throws Exception {
        Ticket ticket1 = ReservationService.cancelTicket(ticket);
        return ticket1;
    }
}
