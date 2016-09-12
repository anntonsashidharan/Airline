package com.airline.util.data;

import com.airline.dao.portal.PortalDAO;
import com.airline.dao.role.RoleDAO;
import com.airline.dao.user.UserDAO;
import com.airline.domain.portal.Portal;
import com.airline.domain.portal.TreeMenuEntity;
import com.airline.domain.role.Role;
import com.airline.domain.user.User;
import com.airline.system.APPStatics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JJ on 4/17/16.
 */
public class ListsUtil {

    public static List<TreeMenuEntity> populateTreeMenu(User user){
        List<TreeMenuEntity> treeMenuEntities = new ArrayList<TreeMenuEntity>();
        TreeMenuEntity level1;
        TreeMenuEntity level2;
        List<String> portals = ListsUtil.getStringArrayList(user.getPortals());
        if(portals.contains(APPStatics.PortalStatics.ADD_AIRCRAFT) || portals.contains(APPStatics.PortalStatics.VIEW_AIRCRAFT) || portals.contains(APPStatics.PortalStatics.DELETE_AIRCRAFT) || portals.contains(APPStatics.PortalStatics.UPDATE_AIRCRAFT)){
            level1 = new TreeMenuEntity();
            level1.setDisplayName("Aircraft");
            treeMenuEntities.add(level1);
            level1.setSubMenu(new ArrayList<TreeMenuEntity>());
            if(portals.contains(APPStatics.PortalStatics.ADD_AIRCRAFT)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Add Aircraft");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
            if(portals.contains(APPStatics.PortalStatics.DELETE_AIRCRAFT) || portals.contains(APPStatics.PortalStatics.UPDATE_AIRCRAFT) || portals.contains(APPStatics.PortalStatics.VIEW_AIRCRAFT)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Manage Aircraft");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
        }

        if(portals.contains(APPStatics.PortalStatics.ADD_SCHEDULE) || portals.contains(APPStatics.PortalStatics.VIEW_SCHEDULE) || portals.contains(APPStatics.PortalStatics.DELETE_SCHEDULE) || portals.contains(APPStatics.PortalStatics.UPDATE_SCHEDULE)){
            level1 = new TreeMenuEntity();
            level1.setDisplayName("Schedule");
            treeMenuEntities.add(level1);
            level1.setSubMenu(new ArrayList<TreeMenuEntity>());
            if(portals.contains(APPStatics.PortalStatics.ADD_SCHEDULE)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Add Schedule");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
            if(portals.contains(APPStatics.PortalStatics.DELETE_SCHEDULE) || portals.contains(APPStatics.PortalStatics.UPDATE_SCHEDULE) || portals.contains(APPStatics.PortalStatics.VIEW_SCHEDULE)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Manage Schedule");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
        }

        if(portals.contains(APPStatics.PortalStatics.ADD_EMPLOYEE) || portals.contains(APPStatics.PortalStatics.VIEW_EMPLOYEE) || portals.contains(APPStatics.PortalStatics.DELETE_EMPLOYEE) || portals.contains(APPStatics.PortalStatics.UPDATE_EMPLOYEE)){
            level1 = new TreeMenuEntity();
            level1.setDisplayName("Employee");
            treeMenuEntities.add(level1);
            level1.setSubMenu(new ArrayList<TreeMenuEntity>());
            if(portals.contains(APPStatics.PortalStatics.ADD_EMPLOYEE)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Add Employee");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
            if(portals.contains(APPStatics.PortalStatics.DELETE_EMPLOYEE) || portals.contains(APPStatics.PortalStatics.UPDATE_EMPLOYEE) || portals.contains(APPStatics.PortalStatics.VIEW_EMPLOYEE)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Manage Employee");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
        }

        if(portals.contains(APPStatics.PortalStatics.ADD_FLIGHT) || portals.contains(APPStatics.PortalStatics.VIEW_FLIGHT) || portals.contains(APPStatics.PortalStatics.DELETE_FLIGHT) || portals.contains(APPStatics.PortalStatics.UPDATE_FLIGHT)){
            level1 = new TreeMenuEntity();
            level1.setDisplayName("Schedule");
            treeMenuEntities.add(level1);
            level1.setSubMenu(new ArrayList<TreeMenuEntity>());
            if(portals.contains(APPStatics.PortalStatics.ADD_FLIGHT)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Add Schedule");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
            if(portals.contains(APPStatics.PortalStatics.DELETE_FLIGHT) || portals.contains(APPStatics.PortalStatics.UPDATE_FLIGHT) || portals.contains(APPStatics.PortalStatics.VIEW_FLIGHT)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Manage Schedule");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
        }


        if(portals.contains(APPStatics.PortalStatics.ADD_AIRPORT) || portals.contains(APPStatics.PortalStatics.VIEW_AIRPORT) || portals.contains(APPStatics.PortalStatics.DELETE_AIRPORT) || portals.contains(APPStatics.PortalStatics.UPDATE_AIRPORT)){
            level1 = new TreeMenuEntity();
            level1.setDisplayName("Airport");
            treeMenuEntities.add(level1);
            level1.setSubMenu(new ArrayList<TreeMenuEntity>());
            if(portals.contains(APPStatics.PortalStatics.ADD_AIRPORT)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Add Airport");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
            if(portals.contains(APPStatics.PortalStatics.DELETE_AIRPORT) || portals.contains(APPStatics.PortalStatics.UPDATE_AIRPORT) || portals.contains(APPStatics.PortalStatics.VIEW_AIRPORT)){
                level2 = new TreeMenuEntity();
                level2.setDisplayName("Manage Airport");
                level2.setHref("/main#");
                level1.getSubMenu().add(level2);
            }
        }

        level1 = new TreeMenuEntity();
        level1.setDisplayName("Manage Password");
        level1.setHref("/main#");
        treeMenuEntities.add(level1);
        level1.setSubMenu(new ArrayList<TreeMenuEntity>());


        return treeMenuEntities;
    }

    public static List<String> getStringArrayList(List<Portal> portals){
        List<String> strings = new ArrayList<String>();
        for (Portal portal:portals){
            strings.add(portal.getPortalName());
        }
        return strings;
    }

}



