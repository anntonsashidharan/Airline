package com.airline.domain.flight;

import com.airline.domain.airport.Airport;
import com.airline.domain.employee.Employee;

/**
 * Created by JJ on 4/13/16.
 */
public class Flight {
    private String flightNumber;
    private Airport fromAirport;
    private Airport toAirport;
    private Employee employeeRecorder;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    public Employee getEmployeeRecorder() {
        return employeeRecorder;
    }

    public void setEmployeeRecorder(Employee employeeRecorder) {
        this.employeeRecorder = employeeRecorder;
    }
}
