package com.airline.domain.role;

import com.airline.domain.employee.Employee;
import com.airline.domain.portal.Portal;

import java.util.List;

/**
 * Created by JJ on 4/16/16.
 */
public class Role {
    private String roleName;
    private String description;
    private List<Portal> portals;
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Portal> getPortals() {

        return portals;
    }

    public void setPortals(List<Portal> portals) {
        this.portals = portals;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
