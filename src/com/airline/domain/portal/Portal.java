package com.airline.domain.portal;

import com.airline.domain.role.Role;

import java.util.List;

/**
 * Created by JJ on 4/13/16.
 */
public class Portal {
    private String portalName;
    private String description;
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPortalName() {
        return portalName;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
    }
}
