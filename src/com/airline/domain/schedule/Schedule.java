package com.airline.domain.schedule;

import com.airline.domain.fair.Fair;
import com.airline.domain.flight.Flight;

import java.util.Date;
import java.util.List;

/**
 * Created by JJ on 4/13/16.
 */
public class Schedule {
    private String scheduleId;
    private Date departDate;
    private Date departTime;
    private Date arrivalTime;
    private Date arrivalDate;
    private Flight flight;
    private int numOfAvailableEconomySeats;
    private int numOfAvailableBusinessSeats;
    private int numOfAvailableFirstClassSeats;
    private String aircraftNumber;
    private List<Fair> fairs;

    public List<Fair> getFairs() {
        return fairs;
    }

    public void setFairs(List<Fair> fairs) {
        this.fairs = fairs;
    }

    public String getAircraftNumber() {
        return aircraftNumber;
    }

    public void setAircraftNumber(String aircraftNumber) {
        this.aircraftNumber = aircraftNumber;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getNumOfAvailableEconomySeats() {
        return numOfAvailableEconomySeats;
    }

    public void setNumOfAvailableEconomySeats(int numOfAvailableEconomySeats) {
        this.numOfAvailableEconomySeats = numOfAvailableEconomySeats;
    }

    public int getNumOfAvailableBusinessSeats() {
        return numOfAvailableBusinessSeats;
    }

    public void setNumOfAvailableBusinessSeats(int numOfAvailableBusinessSeats) {
        this.numOfAvailableBusinessSeats = numOfAvailableBusinessSeats;
    }

    public int getNumOfAvailableFirstClassSeats() {
        return numOfAvailableFirstClassSeats;
    }

    public void setNumOfAvailableFirstClassSeats(int numOfAvailableFirstClassSeats) {
        this.numOfAvailableFirstClassSeats = numOfAvailableFirstClassSeats;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
