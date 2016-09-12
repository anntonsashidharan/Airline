package com.airline.dao.ticket;

import com.airline.dao.passenger.PassengerDAO;
import com.airline.dao.schedule.ScheduleDAO;
import com.airline.domain.passenger.Passenger;
import com.airline.domain.schedule.Schedule;
import com.airline.domain.ticket.Ticket;
import com.airline.system.APPStatics;
import com.airline.util.db.DBConnection;
import com.airline.webservice.Enums;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by JJ on 8/30/16.
 */
public class TicketDAO {

    private Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    public Ticket createTicket(Ticket ticket) throws Exception {


        String sqlNextTicketNumber = "SELECT nextval('" + APPStatics.schemaName + ".\"seqTicketNumberGenerator\"')";
        String sqlInsertIntoTicket = "INSERT INTO " + APPStatics.schemaName + ".\"TICKET\" " +
                "(ticket_number,status,seat_number,booking_number,parent_ticket_number,passenger_number) VALUES(?,?,?,?,?,?)";


        PreparedStatement statement1 = connection.prepareStatement(sqlNextTicketNumber);
        ResultSet resultSet1 = statement1.executeQuery();
        resultSet1.next();
        int nextTicketNumber = resultSet1.getInt("nextval");


        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoTicket);
        String ticketNumber = APPStatics.TICKET_NUMBER_PREFIX + String.format("%06d", nextTicketNumber);
        ticket.setTicketNumber(ticketNumber);
        ticket.setStatus(Enums.TicketStatus.SCHEDULED);


        statement2.setString(1, ticketNumber);
        statement2.setString(2, Enums.TicketStatus.SCHEDULED.toString());
        statement2.setInt(3, ticket.getSeatNumber());
        statement2.setString(4, ticket.getBooking().getBookingNumber());
        if(ticket.getParentTicket()!=null){
            statement2.setString(5, ticket.getParentTicket().getTicketNumber());
        }else{
            statement2.setString(5, null);
        }
        statement2.setString(6, ticket.getPassenger().getPassengerID());
        statement2.execute();


        return ticket;
    }
}
