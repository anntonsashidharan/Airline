package com.airline.domain.aircraft;

import com.airline.domain.employee.Employee;

/**
 * Created by JJ on 4/13/16.
 */
public class Aircraft {
    private String registrationNumber;
    private String manufacturer;
    private int numberOfEconomySeats;
    private int numberOfBusinessSeats;
    private int numberOfFirstClassSeats;
    private Employee employee;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getNumberOfEconomySeats() {
        return numberOfEconomySeats;
    }

    public void setNumberOfEconomySeats(int numberOfEconomySeats) {
        this.numberOfEconomySeats = numberOfEconomySeats;
    }

    public int getNumberOfBusinessSeats() {
        return numberOfBusinessSeats;
    }

    public void setNumberOfBusinessSeats(int numberOfBusinessSeats) {
        this.numberOfBusinessSeats = numberOfBusinessSeats;
    }

    public int getNumberOfFirstClassSeats() {
        return numberOfFirstClassSeats;
    }

    public void setNumberOfFirstClassSeats(int numberOfFirstClassSeats) {
        this.numberOfFirstClassSeats = numberOfFirstClassSeats;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
