package com.airline.system;

/**
 * Created by JJ on 4/12/16.
 */
public class APPStatics {
    //database statics
    public static String postgresDriver = "org.postgresql.Driver";
    public static String databaseURL = "jdbc:postgresql://localhost:5432/airline4";
    public static String schemaName = "airline4";
    public static String userName = "postgres";
    public static String password = "admin";


//    //Qatar airline1
//    public static final String PAYMENT_NUMBER_PREFIX = "PAYQTR";
//    public static final String PASSENGER_NUMBER_PREFIX = "PSGQTR";
//    public static final String BOOKING_NUMBER_PREFIX = "BKNQTR";
//    public static final String TICKET_NUMBER_PREFIX = "TKCQTR";

//    //Malasian airline2
//    public static final String PAYMENT_NUMBER_PREFIX = "PAYMAL";
//    public static final String PASSENGER_NUMBER_PREFIX = "PSGMAL";
//    public static final String BOOKING_NUMBER_PREFIX = "BKNMAL";
//    public static final String TICKET_NUMBER_PREFIX = "TKCMAL";

//


//    //British airline3
//    public static final String TICKET_NUMBER_PREFIX = "TKCBTS";
//    public static final String BOOKING_NUMBER_PREFIX = "BKNBTS";
//    public static final String PASSENGER_NUMBER_PREFIX = "PSGBTS";
//    public static final String PAYMENT_NUMBER_PREFIX = "PAYBTS";


    //Srilankan airline4
    public static final String TICKET_NUMBER_PREFIX = "TKCSLA";
    public static final String BOOKING_NUMBER_PREFIX = "BKNSLA";
    public static final String PASSENGER_NUMBER_PREFIX = "PSGSLA";
    public static final String PAYMENT_NUMBER_PREFIX = "PAYSLA";


    public class RequestStatics{
        //Request Statics
        public static final String ERROR_MESSAGE = "errorMessage";
    }

    public class SessionStatics{
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String USER = "user";
    }

    public class PortalStatics{
        public static final String ADD_AIRCRAFT = "ADD_AIRCRAFT";
        public static final String VIEW_AIRCRAFT = "VIEW_AIRCRAFT";
        public static final String UPDATE_AIRCRAFT = "UPDATE_AIRCRAFT";
        public static final String DELETE_AIRCRAFT = "DELETE_AIRCRAFT";
        public static final String MANAGE_AIRCRAFT = "MANAGE_AIRCRAFT";

        public static final String ADD_FLIGHT = "ADD_FLIGHT";
        public static final String MANAGE_FLIGHT = "MANAGE_FLIGHT";
        public static final String DELETE_FLIGHT = "DELETE_FLIGHT";
        public static final String UPDATE_FLIGHT = "UPDATE_FLIGHT";
        public static final String VIEW_FLIGHT = "VIEW_FLIGHT";

        public static final String ADD_SCHEDULE = "ADD_SCHEDULE";
        public static final String DELETE_SCHEDULE = "DELETE_SCHEDULE";
        public static final String UPDATE_SCHEDULE = "UPDATE_SCHEDULE";
        public static final String VIEW_SCHEDULE = "VIEW_SCHEDULE";
        public static final String MANAGE_SCHEDULE = "MANAGE_SCHEDULE";

        public static final String ADD_EMPLOYEE = "ADD_EMPLOYEE";
        public static final String DELETE_EMPLOYEE = "DELETE_EMPLOYEE";
        public static final String MANAGE_EMPLOYEE = "MANAGE_EMPLOYEE";
        public static final String UPDATE_EMPLOYEE = "UPDATE_EMPLOYEE";
        public static final String VIEW_EMPLOYEE = "VIEW_EMPLOYEE";

        public static final String DELETE_AIRPORT = "DELETE_AIRPORT";
        public static final String ADD_AIRPORT = "ADD_AIRPORT";
        public static final String MANAGE_AIRPORT = "MANAGE_AIRPORT";
        public static final String UPDATE_AIRPORT = "UPDATE_AIRPORT";
        public static final String VIEW_AIRPORT = "VIEW_AIRPORT";

        public static final String FIRST_LOGIN_UPDATE = "FIRST_LOGIN_UPDATE";
        public static final String LOGIN = "LOGIN";
        public static final String MANAGE_PASSWORD = "MANAGE_PASSWORD";

    }


}
