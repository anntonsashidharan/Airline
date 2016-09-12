package com.airline.domain.user;

import com.airline.domain.portal.Portal;
import com.airline.domain.role.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by JJ on 4/13/16.
 */
public class User {
    private String userName;
    //0 for first login 1 for previously logged
    private int firstLoginFlag;
    private List<Portal> portals;

    public List<Portal> getPortals() {
        return portals;
    }

    public void setPortals(List<Portal> portals) {
        this.portals = portals;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFirstLoginFlag() {
        return firstLoginFlag;
    }

    public void setFirstLoginFlag(int firstLoginFlag) {
        this.firstLoginFlag = firstLoginFlag;
    }

}