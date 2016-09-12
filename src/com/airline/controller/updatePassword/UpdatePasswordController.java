package com.airline.controller.updatePassword;

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
@WebServlet(urlPatterns = "/updatePassword")
public class UpdatePasswordController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if(request.getParameterNames().hasMoreElements()){
                User sessionUser = (User)request.getSession().getAttribute(APPStatics.SessionStatics.USER);
                if(sessionUser==null){
                    response.sendRedirect("/login");
                }else{
                    if(sessionUser.getFirstLoginFlag()==0){
                        request.getSession().setAttribute(APPStatics.SessionStatics.ERROR_MESSAGE, null);
                        String oldPassword = request.getParameter("oldpassword");
                        String newPassword = request.getParameter("newpassword");
                        String confirmedPassword = request.getParameter("confirmpassword");

                        if(oldPassword.trim().equals("") || newPassword.trim().equals("")|| confirmedPassword.trim().equals("")){
                            throw new Exception("Required fields missing");
                        }

                        if(!newPassword.trim().equals(confirmedPassword.trim())){
                            throw new Exception("Wrong confirmation password");
                        }

                        User updateUser = UserService.updateUserPassword(sessionUser.getUserName(),oldPassword,newPassword);
                        if(updateUser==null){
                            throw new Exception("Incorrect old password");
                        }
                        response.sendRedirect("/main");
                    }else {
                        response.sendRedirect("/main");
                    }
                }
            } else {
                request.getRequestDispatcher("/jsp/updatePassword/updatePassword.jsp").forward(request, response);
            }
        }catch (Exception e){
            request.getSession().setAttribute(APPStatics.SessionStatics.ERROR_MESSAGE, e.getMessage());
            response.sendRedirect("/updatePassword");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute(APPStatics.SessionStatics.USER);
        if(user == null){
            response.sendRedirect("/login");
        }else {
            if(user.getFirstLoginFlag()==0){
                request.getRequestDispatcher("/jsp/updatePassword/updatePassword.jsp").forward(request, response);
            }else {
                response.sendRedirect("/main");
            }
        }
    }
}
