package com.airline.controller.main;

import com.airline.domain.portal.TreeMenuEntity;
import com.airline.domain.user.User;
import com.airline.system.APPStatics;
import com.airline.util.data.ListsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JJ on 4/14/16.
 */
@WebServlet(urlPatterns = "/main")
public class MainController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute(APPStatics.SessionStatics.USER);
        if(user == null){
            request.getSession().setAttribute(APPStatics.SessionStatics.ERROR_MESSAGE,"Your session has expired, please login again.");
            response.sendRedirect("/login");
        }else {

            List<TreeMenuEntity> treeMenuEntities = ListsUtil.populateTreeMenu(user);

            request.getSession().setAttribute("treeMenu", treeMenuEntities);

            RequestDispatcher requestDispatcher = null;
            requestDispatcher = request.getRequestDispatcher("/jsp/main/main.jsp");
            requestDispatcher.forward(request,response);
        }
    }
}
