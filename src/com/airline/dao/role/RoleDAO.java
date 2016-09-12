package com.airline.dao.role;

import com.airline.domain.role.Role;
import com.airline.domain.user.User;
import com.airline.system.APPStatics;
import com.airline.util.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JJ on 4/17/16.
 */
public class RoleDAO {
    public List<Role> getUserRoles(User user){
        List<Role> roles = null;
        Role role;
        String sql = "SELECT * FROM " + APPStatics.schemaName + ".\"USER_ROLE\" WHERE user_name= ?";
        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,user.getUserName());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                role = new Role();
                role.setRoleName(resultSet.getString("role_name"));
                role.setDescription(resultSet.getString("description"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
