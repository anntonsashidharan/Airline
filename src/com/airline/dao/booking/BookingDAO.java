package com.airline.dao.booking;

import com.airline.dao.ticket.TicketDAO;
import com.airline.dao.passenger.PassengerDAO;
import com.airline.dao.payment.PaymentDAO;
import com.airline.dao.schedule.ScheduleDAO;
import com.airline.domain.booking.Booking;
import com.airline.domain.passenger.Passenger;
import com.airline.domain.payment.Payment;
import com.airline.domain.schedule.Schedule;
import com.airline.domain.ticket.Ticket;
import com.airline.system.APPStatics;
import com.airline.util.db.DBConnection;
import com.airline.webservice.Enums;

import java.sql.*;
import java.util.Date;

/**
 * Created by JJ on 8/30/16.
 */
public class BookingDAO {
    private Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public Booking createBooking(Booking booking) throws Exception {
        String bookingNumber;
        Enums.TravelClass travelClass = booking.getTravelClass();
        Schedule schedule = booking.getSchedule();
        Ticket ticket;

        String sqlNextBookingNumber = "SELECT nextval('" + APPStatics.schemaName + ".\"seqBookingNumberGenerator\"')";
        String sqlInsertIntoBooking = "INSERT INTO " + APPStatics.schemaName + ".\"BOOKING\" " +
                "(booking_number,payment_id,travel_class,date,time,schedule_id,agency_id) VALUES(?,?,?,?,?,?,?)";

        PreparedStatement statement1 = connection.prepareStatement(sqlNextBookingNumber);
        ResultSet resultSet1 = statement1.executeQuery();
        resultSet1.next();
        int nextBookingNumber = resultSet1.getInt("nextval");
        bookingNumber = APPStatics.BOOKING_NUMBER_PREFIX + String.format("%06d", nextBookingNumber);
        booking.setBookingNumber(bookingNumber);

        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoBooking);
        statement2.setString(1, bookingNumber);
        statement2.setString(2, booking.getPayment().getPaymentID());
        statement2.setString(3, travelClass.toString());
        Date date = new Date();
        statement2.setDate(4, new java.sql.Date(date.getTime()));
        statement2.setTime(5, new Time(date.getTime()));
        booking.setBookingDate(date);
        booking.setBookingTime(date);
        statement2.setString(6, booking.getSchedule().getScheduleId());
        statement2.setString(7, booking.getAgency().getId());
        statement2.execute();




        return booking;
    }
}
