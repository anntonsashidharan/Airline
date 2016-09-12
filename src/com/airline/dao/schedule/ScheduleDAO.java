package com.airline.dao.schedule;

import com.airline.domain.airport.Airport;
import com.airline.domain.fair.Fair;
import com.airline.domain.flight.Flight;
import com.airline.domain.schedule.Schedule;
import com.airline.system.APPStatics;
import com.airline.util.db.DBConnection;
import com.airline.webservice.Enums;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JJ on 4/24/16.
 */
public class ScheduleDAO {
    private Connection connection;

    public ScheduleDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Schedule> getSchedulesOn(String source, String destination, Date departure, Date minTime, Date maxTime, int numberOfSeats, Enums.TravelClass travelClass) {
        List<Schedule> schedules = new ArrayList<Schedule>();
        //
        String columnToTestSeatNumber = null;
        if (Enums.TravelClass.FIRST_CLASS.equals(travelClass)) {
            columnToTestSeatNumber = "number_of_available_firstclass_seats";
        } else if (Enums.TravelClass.BUSINESS_CLASS.toString().equals(travelClass)) {
            columnToTestSeatNumber = "number_of_available_business_seats";
        } else if (Enums.TravelClass.ECONOMY_CLASS.equals(travelClass)) {
            columnToTestSeatNumber = "number_of_available_economy_seats";
        }


        Schedule schedule;
        String sql = "SELECT s.*,f.*," +
                "src.airport_code AS srccode,src.airport_name AS srcname, src.city AS srccity, src.country AS srccountry," +
                "dest.airport_code AS destcode,dest.airport_name AS destname, dest.city AS destcity, dest.country AS destcountry " +
                "FROM " + APPStatics.schemaName + ".\"SCHEDULE\" s, " + APPStatics.schemaName + ".\"FAIR\" f, " + APPStatics.schemaName + ".\"AIRPORT\" src, " + APPStatics.schemaName + ".\"AIRPORT\" dest " +
                "WHERE flight_number IN (SELECT flight_number FROM " + APPStatics.schemaName + ".\"FLIGHT\" " +
                "WHERE from_airpot = ? AND to_aiport = ?) " +
                "AND s.departure_time BETWEEN ? AND ? " +
                "AND src.airport_code = ? " +
                "AND dest.airport_code= ? " +
                "AND f.schedule_id = s.schedule_id " +
                "AND departure_date = ?";
        if (columnToTestSeatNumber != null) {
            sql = sql + " AND " + columnToTestSeatNumber + " > ?";
        }
        sql = sql + " ORDER BY s.schedule_id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, source);
            statement.setString(2, destination);
            statement.setTime(3, new Time(minTime.getTime()));
            statement.setTime(4, new Time(maxTime.getTime()));
            statement.setString(5, source);
            statement.setString(6, destination);
            statement.setDate(7, new java.sql.Date(departure.getTime()));
            if (columnToTestSeatNumber != null) {
                statement.setInt(8, numberOfSeats);
            }
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                schedule = new Schedule();
                schedules.add(schedule);

                String currentScheduleId = resultSet.getString("schedule_id");
                schedule.setScheduleId(currentScheduleId);
                schedule.setDepartDate(resultSet.getDate("departure_date"));
                schedule.setArrivalDate(resultSet.getDate("arrival_date"));
                schedule.setDepartTime(resultSet.getTime("departure_time"));
                schedule.setArrivalTime(resultSet.getTime("arrival_time"));
                schedule.setAircraftNumber(resultSet.getString("aircraft_number"));
                schedule.setNumOfAvailableEconomySeats(resultSet.getInt("number_of_available_economy_seats"));
                schedule.setNumOfAvailableBusinessSeats(resultSet.getInt("number_of_available_business_seats"));
                schedule.setNumOfAvailableFirstClassSeats(resultSet.getInt("number_of_available_firstclass_seats"));
                Flight flight = new Flight();
                flight.setFlightNumber(resultSet.getString("flight_number"));
                Airport from = new Airport();
                from.setCode(source);
                from.setName(resultSet.getString("srcname"));
                from.setCity(resultSet.getString("srccity"));
                from.setCountry(resultSet.getString("srccountry"));
                Airport to = new Airport();
                to.setCode(destination);
                to.setName(resultSet.getString("destname"));
                to.setCity(resultSet.getString("destcity"));
                to.setCountry(resultSet.getString("destcountry"));
                flight.setFromAirport(from);
                flight.setToAirport(to);
                schedule.setFlight(flight);

                List<Fair> fairs = new ArrayList<Fair>();
                schedule.setFairs(fairs);

                String travelClassTemp = resultSet.getString("class");
                if (travelClassTemp != null && !travelClassTemp.equals("")) {
                    Fair fair = new Fair();
                    fair.setTravelClass(travelClassTemp);
                    fair.setAdultSeatPrice(resultSet.getFloat("adult_seat_price"));
                    fair.setChildSeatPrice(resultSet.getFloat("child_seat_price"));
                    fair.setInfantCost(resultSet.getFloat("infant_cost"));
                    fairs.add(fair);
                }

                while (resultSet.next()) {
                    String scheduleId = resultSet.getString("schedule_id");
                    if (currentScheduleId.equalsIgnoreCase(scheduleId)) {
                        travelClassTemp = resultSet.getString("class");
                        if (travelClassTemp != null && !travelClassTemp.equals("")) {
                            Fair fair = new Fair();
                            fair.setTravelClass(travelClassTemp);
                            fair.setAdultSeatPrice(resultSet.getFloat("adult_seat_price"));
                            fair.setChildSeatPrice(resultSet.getFloat("child_seat_price"));
                            fairs.add(fair);
                        }
                    } else {
                        schedule = new Schedule();
                        schedules.add(schedule);

                        currentScheduleId = resultSet.getString("schedule_id");
                        schedule.setScheduleId(currentScheduleId);
                        schedule.setDepartDate(resultSet.getDate("departure_date"));
                        schedule.setArrivalDate(resultSet.getDate("arrival_date"));
                        schedule.setDepartTime(resultSet.getTime("departure_time"));
                        schedule.setArrivalTime(resultSet.getTime("arrival_time"));
                        schedule.setAircraftNumber(resultSet.getString("aircraft_number"));
                        schedule.setNumOfAvailableEconomySeats(resultSet.getInt("number_of_available_economy_seats"));
                        schedule.setNumOfAvailableBusinessSeats(resultSet.getInt("number_of_available_business_seats"));
                        schedule.setNumOfAvailableFirstClassSeats(resultSet.getInt("number_of_available_firstclass_seats"));
                        flight = new Flight();
                        flight.setFlightNumber(resultSet.getString("flight_number"));
                        from = new Airport();
                        from.setCode(source);
                        from.setName(resultSet.getString("srcname"));
                        from.setCity(resultSet.getString("srccity"));
                        from.setCountry(resultSet.getString("srccountry"));
                        to = new Airport();
                        to.setCode(destination);
                        to.setName(resultSet.getString("destname"));
                        to.setCity(resultSet.getString("destcity"));
                        to.setCountry(resultSet.getString("destcountry"));
                        flight.setFromAirport(from);
                        flight.setToAirport(to);
                        schedule.setFlight(flight);

                        fairs = new ArrayList<Fair>();
                        schedule.setFairs(fairs);

                        travelClassTemp = resultSet.getString("class");
                        if (travelClassTemp != null && !travelClassTemp.equals("")) {
                            Fair fair = new Fair();
                            fair.setTravelClass(travelClassTemp);
                            fair.setAdultSeatPrice(resultSet.getFloat("adult_seat_price"));
                            fair.setChildSeatPrice(resultSet.getFloat("child_seat_price"));
                            fairs.add(fair);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }


    /**
     * Deduct seat count according to passenger number while returning first seat number to be allocated
     * Eg. returns -120 so passenger 1-120, 2-119, 3-118....
     *
     * @param scheduleID
     * @param numberOfPassengers
     * @param travelClass
     * @return
     * @throws Exception
     */
    public int deductSeatCount(String scheduleID, int numberOfPassengers, Enums.TravelClass travelClass) throws Exception {

        StringBuilder sqlGetLastSeatNumber = new StringBuilder();
        StringBuilder sqlDeductSeatCount = new StringBuilder("UPDATE " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ? SET ");
        if (Enums.TravelClass.BUSINESS_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_business_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
            sqlDeductSeatCount.append("number_of_available_business_seats = number_of_available_business_seats - ?");
        } else if (Enums.TravelClass.ECONOMY_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_economy_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
            sqlDeductSeatCount.append("number_of_available_economy_seats = number_of_available_economy_seats - ?");
        } else if (Enums.TravelClass.FIRST_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_firstclass_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
            sqlDeductSeatCount.append("number_of_available_firstclass_seats = number_of_available_firstclass_seats - ?");
        }

        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetLastSeatNumber.toString());
            preparedStatement.setString(1, scheduleID);
            ResultSet resultSet = preparedStatement.executeQuery();
            int lastSeatNumber = resultSet.getInt("lastSeatNumber");

            if (lastSeatNumber < numberOfPassengers) {
                throw new Exception("No sufficient seats available");
            }

            PreparedStatement statement = connection.prepareStatement(sqlDeductSeatCount.toString());
            statement.setString(1, scheduleID);
            statement.setInt(2, numberOfPassengers);
            statement.executeQuery();


            connection.commit();
            return lastSeatNumber;


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


    public int getNumberOfAvailableSeats(String scheduleID, Enums.TravelClass travelClass) throws Exception {

        StringBuilder sqlGetLastSeatNumber = new StringBuilder();
        if (Enums.TravelClass.BUSINESS_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_business_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
        } else if (Enums.TravelClass.ECONOMY_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_economy_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
        } else if (Enums.TravelClass.FIRST_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_firstclass_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(sqlGetLastSeatNumber.toString());
        preparedStatement.setString(1, scheduleID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int availableSeats = resultSet.getInt("lastSeatNumber");

        return availableSeats;

    }


    public int reserveAndGetSeat(String scheduleID, Enums.TravelClass travelClass) throws Exception {

        StringBuilder sqlGetLastSeatNumber = new StringBuilder();
        StringBuilder sqlDeductSeatCount = new StringBuilder("UPDATE " + APPStatics.schemaName + ".\"SCHEDULE\" SET ");
        if (Enums.TravelClass.BUSINESS_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_business_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
            sqlDeductSeatCount.append("number_of_available_business_seats = number_of_available_business_seats - 1 WHERE schedule_id = ?");
        } else if (Enums.TravelClass.ECONOMY_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_economy_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
            sqlDeductSeatCount.append("number_of_available_economy_seats = number_of_available_economy_seats - 1 WHERE schedule_id = ?");
        } else if (Enums.TravelClass.FIRST_CLASS.equals(travelClass)) {
            sqlGetLastSeatNumber.append("SELECT number_of_available_firstclass_seats AS lastSeatNumber FROM " + APPStatics.schemaName + ".\"SCHEDULE\" WHERE schedule_id = ?");
            sqlDeductSeatCount.append("number_of_available_firstclass_seats = number_of_available_firstclass_seats - 1 WHERE schedule_id = ?");
        }


        PreparedStatement preparedStatement = connection.prepareStatement(sqlGetLastSeatNumber.toString());
        preparedStatement.setString(1, scheduleID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int lastSeatNumber = resultSet.getInt("lastSeatNumber");

        if (lastSeatNumber <= 0) {
            throw new Exception("No sufficient seats available");
        }

        PreparedStatement statement = connection.prepareStatement(sqlDeductSeatCount.toString());
        statement.setString(1, scheduleID);
        statement.execute();


        return lastSeatNumber;

    }


    public Schedule addSchedule(Schedule schedule) throws Exception{
        String sqlNextScheduleNumber = "SELECT nextval('" + APPStatics.schemaName + ".\"seqPaymentNumberGenerator\"')";
        String sqlInsertIntoSchedule = "INSERT INTO " + APPStatics.schemaName + ".\"SCHEDULE\" " +
                "(schedule_id,departure_date,departure_time," +
                "arrival_date,arrival_time,aircraft_number,flight_number," +
                "employee_id,number_of_available_economy_seats" +
                ",number_of_available_business_seats,number_of_available_firstclass_seats) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement statement1 = connection.prepareStatement(sqlNextScheduleNumber);
        ResultSet resultSet1 = statement1.executeQuery();
        resultSet1.next();
        int nextScheduleNumber = resultSet1.getInt("nextval");


        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoSchedule);
        String nextScheduleNumber1 = "SCH" + String.format("%06d", nextScheduleNumber);
        schedule.setScheduleId(nextScheduleNumber1);

        statement2.setString(1, schedule.getScheduleId());
        statement2.setDate(2, new java.sql.Date(schedule.getDepartDate().getTime()));
        statement2.setTime(3, new java.sql.Time(schedule.getDepartTime().getTime()));
        statement2.setDate(4, new java.sql.Date(schedule.getArrivalDate().getTime()));
        statement2.setTime(5, new java.sql.Time(schedule.getArrivalTime().getTime()));
        statement2.setString(6, schedule.getAircraftNumber());
        statement2.setString(7, schedule.getFlight().getFlightNumber());
        statement2.setString(8, "");
        statement2.setInt(9, schedule.getNumOfAvailableEconomySeats());
        statement2.setInt(10, schedule.getNumOfAvailableBusinessSeats());
        statement2.setInt(11, schedule.getNumOfAvailableFirstClassSeats());




        statement2.execute();


        return schedule;
    }

    public Schedule addScheduleSeatsToBusiness(Schedule schedule,int seatNumber) throws Exception{
        String sqlInsertIntoSchedule = "INSERT INTO " + APPStatics.schemaName + ".\"AVAILABLE_BUSINESS_SEATS\" " +
                "(schedule_id,seat_number) VALUES(?,?)";


        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoSchedule);
        statement2.setString(1, schedule.getScheduleId());
        statement2.setInt(2, seatNumber);

        statement2.execute();


        return schedule;
    }

    public Schedule addScheduleSeatsToEcon(Schedule schedule,int seatNumber) throws Exception{
        String sqlInsertIntoSchedule = "INSERT INTO " + APPStatics.schemaName + ".\"AVAILABLE_ECONOMY_SEATS\" " +
                "(schedule_id,seat_number) VALUES(?,?)";


        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoSchedule);
        statement2.setString(1, schedule.getScheduleId());
        statement2.setInt(2, seatNumber);

        statement2.execute();


        return schedule;
    }
    public Schedule addScheduleSeatsToFirstClass(Schedule schedule,int seatNumber) throws Exception{
        String sqlInsertIntoSchedule = "INSERT INTO " + APPStatics.schemaName + ".\"AVAILABLE_FIRSTCLASS_SEATS\" " +
                "(schedule_id,seat_number) VALUES(?,?)";


        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoSchedule);
        statement2.setString(1, schedule.getScheduleId());
        statement2.setInt(2, seatNumber);

        statement2.execute();


        return schedule;
    }


}
