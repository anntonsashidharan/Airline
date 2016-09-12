package com.airline.controller.login;

import com.airline.domain.user.User;
import com.airline.service.user.UserService;
import com.airline.system.APPStatics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by JJ on 4/14/16.
 */
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        if(request.getParameterNames().hasMoreElements()){
            User user = UserService.getUserLogin(request.getParameter("userName"), request.getParameter("password"));
            if(user==null){
                request.getSession().setAttribute(APPStatics.SessionStatics.ERROR_MESSAGE, "Invalid user name or password");
                response.sendRedirect("/login");
            }else{
                request.getSession().setAttribute(APPStatics.SessionStatics.ERROR_MESSAGE, null);
                request.getSession().setAttribute(APPStatics.SessionStatics.USER,user);
                if(user.getFirstLoginFlag()==0){
                    response.sendRedirect("/updatePassword");
                }else {
                    response.sendRedirect("/main");
                }
            }
        } else {
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        }
        }catch (Exception e){
            request.setAttribute("errorMessage",e.getMessage());
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*RequestDispatcher rd;
        UserLogin userLogin = (UserLogin)request.getSession().getAttribute(APPStatics.SessionStatics.USER_LOGIN);
        if(userLogin==null){
            //rd = request.getRequestDispatcher("/jsp/login/login.jsp");
            response.sendRedirect("/jsp/login/login.jsp");
        }else{
            if(userLogin.getFirstLoginFlag()==1){
                rd = request.getRequestDispatcher("/main");
            }else{
                rd = request.getRequestDispatcher("/jsp/updatePassword/updatePassword.jsp");
            }
        }
       // rd.forward(request, response);
       */
        User user = (User)request.getSession().getAttribute(APPStatics.SessionStatics.USER);
        if(user == null){
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        }else {
            if(user.getFirstLoginFlag()==1){
                response.sendRedirect("/main");
            }else{
                response.sendRedirect("/updatePassword");
            }
        }
    }

}
