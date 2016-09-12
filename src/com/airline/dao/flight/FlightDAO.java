package com.airline.dao.flight;

import com.airline.domain.airport.Airport;
import com.airline.domain.flight.Flight;
import com.airline.domain.schedule.Schedule;
import com.airline.system.APPStatics;
import com.airline.util.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JJ on 4/24/16.
 */
public class FlightDAO {
    Connection connection;

    public FlightDAO(Connection connection) {
        this.connection = connection;
    }

    public Flight getFlightBySourceAndDestination (String source, String destination){
        Flight flight = null;
        String sql = "SELECT * FROM " + APPStatics.schemaName + ".\"FLIGHT\" WHERE from_airpot = ? " +
                "AND to_aiport = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,source);
            statement.setString(2, destination);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                flight = new Flight();
                flight.setFlightNumber(resultSet.getString("flight_number"));
                Airport from = new Airport();
                from.setCode(source);
                Airport to = new Airport();
                to.setCode(destination);
                flight.setFromAirport(from);
                flight.setToAirport(to);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }
}
