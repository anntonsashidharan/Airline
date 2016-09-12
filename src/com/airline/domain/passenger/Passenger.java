package com.airline.domain.passenger;

import com.airline.domain.booking.Booking;
import com.airline.domain.ticket.Ticket;
import com.airline.util.enums.Gender;
import com.airline.util.enums.PassengerType;

import java.util.Date;
import java.util.EnumMap;

/**
 * Created by JJ on 4/13/16.
 */
public class Passenger {
    private String passengerID;
    private String firstName;
    private String lastName;
    private String otherName;
    private Date dateOfBirth;
    private Gender gender;
    private String passportNumber;
    private PassengerType passengerType;
    private Ticket ticket;


    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }
}


