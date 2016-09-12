package com.airline.dao.user;

import com.airline.domain.portal.Portal;
import com.airline.domain.role.Role;
import com.airline.domain.user.User;
import com.airline.system.APPStatics;
import com.airline.util.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JJ on 4/16/16.
 */
public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserByUserName(String userName) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM " + APPStatics.schemaName + ".\"EMPLOYEE\" WHERE user_name=\'" + userName + "\'";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            user = new User();
            user.setUserName(userName);
            user.setFirstLoginFlag(resultSet.getInt("first_login_flag"));
        }
        return user;
    }

//    public User validateAndGetUser(String userName,String password){
//        User user = null;
//        // String sql = "SELECT * FROM " + APPStatics.schemaName + ".\"EMPLOYEE\" " +
//        //           "WHERE user_name=\'" + userName + "\'" + " AND password=\'" + password + "\'" ;
//        String sql = "SELECT * FROM airline1.\"EMPLOYEE\" e, airline1.\"USER_ROLE\" ur " +
//                "WHERE e.user_name = ? AND e.password = ? AND e.user_name = ur.user_name";
//        DBConnection dbConnection = DBConnection.getInstance();
//        Connection connection = dbConnection.getConnection();
//        try {
//            Role role = new Role();
//            List<Role> roles = new ArrayList<Role>();
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1,userName);
//            statement.setString(2,password);
//            ResultSet resultSet = statement.executeQuery();
//            if(resultSet.next()){
//                user = new User();
//                user.setUserName(userName);
//                user.setFirstLoginFlag(resultSet.getInt("first_login_flag"));
//                role.setRoleName(resultSet.getString("role_name"));
//                roles.add(role);
//                user.setRoles(roles);
//            }
//            while(resultSet.next()){
//                role = new Role();
//                role.setRoleName(resultSet.getString("role_name"));
//                roles.add(role);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }

//    public User getUser(String userName, String password){
//        User user = null;
//        String sql = "SELECT * FROM " + APPStatics.schemaName + ".\"EMPLOYEE\" WHERE user_name= ? AND password = ?";
//        DBConnection dbConnection = DBConnection.getInstance();
//        Connection connection = dbConnection.getConnection();
//        try {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1,userName);
//            statement.setString(2,password);
//            ResultSet resultSet = statement.executeQuery();
//            if(resultSet.next()){
//                user = new User();
//                user.setUserName(userName);
//                user.setFirstLoginFlag(resultSet.getInt("first_login_flag"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }

    public User getUser(String userName, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM " + APPStatics.schemaName + ".\"EMPLOYEE\" emp, " + APPStatics.schemaName + ".\"USER_ROLE\" ur, " + APPStatics.schemaName + ".\"ROLE_PORTAL\" rp,  " + APPStatics.schemaName + ".\"PORTAL\" p  " +
                "WHERE emp.user_name= ? AND password = ? " +
                "AND emp.user_name = ur.user_name " +
                "AND ur.role_name = rp.role_name " +
                "AND rp.portal_name = p.portal_name";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userName);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        List<Portal> portals;
        Portal portal;

        if (resultSet.next()) {
            user = new User();
            user.setUserName(userName);
            user.setFirstLoginFlag(resultSet.getInt("first_login_flag"));
            portals = new ArrayList<Portal>();
            portal = new Portal();
            portal.setPortalName(resultSet.getString("portal_name"));
            portal.setDescription(resultSet.getString("description"));
            portals.add(portal);
            user.setPortals(portals);
            while (resultSet.next()) {
                portal = new Portal();
                portal.setPortalName(resultSet.getString("portal_name"));
                portal.setDescription(resultSet.getString("description"));
                portals.add(portal);
            }
        }

        return user;
    }

    public int updateUserPassword(User user, String password) throws SQLException {
        User existingUser = null;
        int rowCount = 0;
        String sql = "UPDATE " + APPStatics.schemaName + ".\"EMPLOYEE\" SET password = ?, first_login_flag= ? WHERE user_name= ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, password);
        statement.setInt(2, 1);
        statement.setString(3, user.getUserName());
        rowCount = statement.executeUpdate();

        return rowCount;
    }

}
