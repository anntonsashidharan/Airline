package com.airline.service.user;

import com.airline.dao.user.UserDAO;
import com.airline.domain.user.User;
import com.airline.util.db.Transaction;

import java.sql.Connection;

/**
 * Created by JJ on 4/14/16.
 */
public class UserService {

    /**
     *
     * @param userName
     * @param password
     * @return user if valid login else returns null
     */
    public static User getUserLogin(String userName,String password) throws Exception {
        Connection connection = Transaction.beginTransaction();
        User userLogin = null;
        UserDAO userDAO = new UserDAO(connection);
        //userLogin = userDAO.validateAndGetUser(userName, password);
        userLogin = userDAO.getUser(userName, password);
        Transaction.endTransaction(connection);
        return userLogin;

    }

    /**
     *
     * @param userName
     * @param newPassword
     * @return return updated user
     */
    public static User updateUserPassword(String userName, String oldPassword,String newPassword) throws Exception {
        Connection connection = Transaction.beginTransaction();
        UserDAO userDAO = new UserDAO(connection);
        User user = userDAO.getUser(userName, oldPassword);
        if(user!=null){ //valid username password
            int rowCount = userDAO.updateUserPassword(user,newPassword);
            //test successfully updated or not
            if(rowCount<1){
                return null;
            }
        }
        Transaction.endTransaction(connection);
        return user;
    }
}
