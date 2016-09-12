package com.airline.dao.fair;

import com.airline.domain.fair.Fair;
import com.airline.domain.schedule.Schedule;
import com.airline.system.APPStatics;
import com.airline.webservice.Enums;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by JJ on 9/11/16.
 */
public class FairDAO {
    private Connection connection;

    public FairDAO(Connection connection) {
        this.connection = connection;
    }

    public void addFair(Schedule schedule,Enums.TravelClass travelClass,float adultRate,float childRate,float infantRate) throws Exception{
        String sqlInsertIntoSchedule = "INSERT INTO " + APPStatics.schemaName + ".\"FAIR\" " +
                "(schedule_id,class,adult_seat_price,child_seat_price,infant_cost) VALUES(?,?,?,?,?)";


        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoSchedule);
        statement2.setString(1, schedule.getScheduleId());
        statement2.setString(2, travelClass.toString());
        statement2.setFloat(3, adultRate);
        statement2.setFloat(4, childRate);
        statement2.setFloat(5, infantRate);

        statement2.execute();


    }
}
