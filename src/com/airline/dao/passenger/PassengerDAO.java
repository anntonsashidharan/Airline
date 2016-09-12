package com.airline.dao.passenger;

import com.airline.domain.passenger.Passenger;
import com.airline.system.APPStatics;
import com.airline.util.db.DBConnection;
import com.airline.util.enums.Gender;
import com.airline.util.enums.PassengerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by JJ on 8/28/16.
 */
public class PassengerDAO {

    private Connection connection;

    public PassengerDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * return passenger with passengerID
     *
     * @param passenger passenger will be assigned passengerID
     * @return
     * @throws Exception
     */
    public Passenger createPassenger(Passenger passenger) throws Exception {


        String fName = passenger.getFirstName();
        String lName = passenger.getFirstName();
        String oName = passenger.getFirstName();
        String passport = passenger.getPassportNumber();
        Gender gender = passenger.getGender();
        PassengerType passengerType = passenger.getPassengerType();
        Date dob = passenger.getDateOfBirth();


        String sqlNextPassengerNumber = "SELECT nextval('" + APPStatics.schemaName + ".\"seqPassengerNumberGenerator\"')";
        String sqlInsertIntoPassenger = "INSERT INTO " + APPStatics.schemaName + ".\"PASSENGER\" " +
                "(passenger_id,first_name,last_name,other_name,passport,date_of_birth,type,gender) VALUES(?,?,?,?,?,?,?,?)";


        PreparedStatement statement1 = connection.prepareStatement(sqlNextPassengerNumber);
        ResultSet resultSet1 = statement1.executeQuery();
        resultSet1.next();
        int nextPassengerNumber = resultSet1.getInt("nextval");


        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoPassenger);
        String passengerID = APPStatics.PASSENGER_NUMBER_PREFIX + String.format("%06d", nextPassengerNumber);
        passenger.setPassengerID(passengerID);


        statement2.setString(1, passengerID);
        statement2.setString(2, fName);
        statement2.setString(3, lName);
        statement2.setString(4, oName);
        statement2.setString(5, passport);
        statement2.setDate(6, new java.sql.Date(dob.getTime()));
        statement2.setString(7, passengerType.toString());
        statement2.setString(8, gender.toString());


        statement2.execute();


        return passenger;


    }


    public void updatePassenger(String passengerID, Passenger passenger) throws Exception {
        String fName = passenger.getFirstName();
        String lName = passenger.getFirstName();
        String oName = passenger.getFirstName();
        String passport = passenger.getPassportNumber();
        Gender gender = passenger.getGender();
        PassengerType passengerType = passenger.getPassengerType();
        Date dob = passenger.getDateOfBirth();

        String sqlUpdatePassenger = "UPDATE " + APPStatics.schemaName + ".\"PASSENGER\" SET first_name=?,last_name=?,other_name=?,passport=?,date_of_birth=?,type=?,gender=? WHERE passenger_id=?";

        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        try {
            connection.setAutoCommit(false);    //to process as transaction
            PreparedStatement statement1 = connection.prepareStatement(sqlUpdatePassenger);
            ResultSet resultSet1 = statement1.executeQuery();
            statement1.setString(1, fName);
            statement1.setString(2, lName);
            statement1.setString(3, oName);
            statement1.setString(4, passport);
            statement1.setDate(5, new java.sql.Date(dob.getTime()));
            statement1.setString(6, passengerType.toString());
            statement1.setString(7, gender.toString());
            statement1.setString(8, passengerID);
            int result = statement1.executeUpdate();
            if (result == 0) {
                throw new Exception("Passenger not found");
            }
            connection.commit();


        } catch (SQLException e) {
            connection.rollback();
            throw new Exception(e.getSQLState());
        } finally {
            try {
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void deletePassenger(String passengerID) throws Exception {
        String sqlDeletePassenger = "DELETE FROM " + APPStatics.schemaName + ".\"PASSENGER\" WHERE passenger_id = ?";

        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        try {
            connection.setAutoCommit(false);    //to process as transaction
            PreparedStatement statement1 = connection.prepareStatement(sqlDeletePassenger);
            ResultSet resultSet1 = statement1.executeQuery();
            statement1.setString(1, passengerID);
            int result = statement1.executeUpdate();
            if (result == 0) {
                throw new Exception("Passenger not found");
            }
            connection.commit();


        } catch (SQLException e) {
            connection.rollback();
            throw new Exception(e.getSQLState());
        } finally {
            try {
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}

