package com.airline.controller.schedule;

import com.airline.domain.airport.Airport;
import com.airline.domain.airport.Airports;
import com.airline.service.airport.AirportService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JJ on 9/11/16.
 */
@WebServlet("/addSchedule")
public class AddSchedule extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HashMap<String, String> airports = Airports.getAirportsInstance().getAirports();
            String json = new Gson().toJson(airports);
            request.getSession().setAttribute("airportList", json);
            request.getRequestDispatcher("/jsp/addSchedule/addSchedule.jsp").forward(request, response);
        }catch (Exception e){
            request.getRequestDispatcher("/jsp/addSchedule/addSchedule.jsp").forward(request, response);
        }
    }
}
