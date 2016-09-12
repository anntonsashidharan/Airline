package com.airline.service.schedule;

import com.airline.dao.fair.FairDAO;
import com.airline.dao.flight.FlightDAO;
import com.airline.dao.schedule.ScheduleDAO;
import com.airline.domain.flight.Flight;
import com.airline.domain.schedule.Schedule;
import com.airline.system.APPStatics;
import com.airline.util.db.Transaction;
import com.airline.webservice.Enums;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by JJ on 4/24/16.
 */
public class ScheduleService {
    public static List<Schedule> getSchedules(String source, String destination, Date departure, Date minTime, Date maxTime, int numberOfSeats, Enums.TravelClass travelClass) throws Exception {
        Connection connection = Transaction.beginTransaction();
        ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

        List<Schedule> schedules = scheduleDAO.getSchedulesOn(source, destination, departure, minTime, maxTime, numberOfSeats, travelClass);

        Transaction.endTransaction(connection);
        return schedules;
    }


    public static void addSchedules(String from,String to,Date departDate,Date departTime,Date arrivalDate,Date arrivalTime,String aircraftNo,
                                    int numOfEconSeat,int numOfBusSeat,int numOfFCSeat,
                                    float econFairAdult,float econfairChild,float econFairInfant,
                                    float busFairAdult,float busfairChild,float busFairInfant,
                                    float firstClassFairAdult,float firstClassfairChild,float firstClassFairInfant) throws Exception {
        Connection connection = Transaction.beginTransaction();


        try{
            Schedule schedule = new Schedule();

            ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
            FlightDAO flightDAO = new FlightDAO(connection);
            Flight flight = flightDAO.getFlightBySourceAndDestination(from,to);

            schedule.setFlight(flight);
            schedule.setDepartTime(departTime);
            schedule.setDepartDate(departDate);
            schedule.setArrivalDate(arrivalDate);
            schedule.setArrivalTime(arrivalTime);
            schedule.setNumOfAvailableFirstClassSeats(numOfFCSeat);
            schedule.setNumOfAvailableBusinessSeats(numOfBusSeat);
            schedule.setNumOfAvailableEconomySeats(numOfEconSeat);
            schedule.setAircraftNumber(aircraftNo);

            scheduleDAO.addSchedule(schedule);



            for(int i = 1;i<numOfEconSeat;i++){
                scheduleDAO.addScheduleSeatsToEcon(schedule,i);
            }
            for(int i = 1;i<numOfBusSeat;i++){
                scheduleDAO.addScheduleSeatsToBusiness(schedule, i);
            }
            for(int i = 1;i<numOfFCSeat;i++){
                scheduleDAO.addScheduleSeatsToFirstClass(schedule, i);
            }

            FairDAO fairDAO = new FairDAO(connection);
            fairDAO.addFair(schedule, Enums.TravelClass.BUSINESS_CLASS,busFairAdult,busfairChild,busFairInfant);

        //List<Schedule> schedules = scheduleDAO.getSchedulesOn(source, destination, departure, minTime, maxTime, numberOfSeats, travelClass);
        }finally {
            Transaction.endTransaction(connection);
        }
    }
}
