package com.airline.domain.agency;

import com.airline.domain.employee.Employee;

/**
 * Created by JJ on 4/13/16.
 */
public class Agency {
    private String name;
    private String id;
    private Employee employee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
