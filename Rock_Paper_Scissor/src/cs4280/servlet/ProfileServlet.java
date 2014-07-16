package cs4280.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
Responsible to display user info, provide function to change user preference like theme
 */
public class ProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /////////////////////////////////////////////
        /*
        Renee Workspace, check session here, kick the user back if needed
        */
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        if (session.getAttribute("isLoggedIn") != "1"){
            dispatcher=request.getRequestDispatcher("/pages/index.jsp");
            dispatcher.forward(request,response);
        }

        if (request.getParameter("submitProfile").equals("1")){
        /*
        * Louis please set bean here
        * */
        String theme = request.getParameter("theme");
        session.setAttribute("theme",theme);
        }


        dispatcher=request.getRequestDispatcher("/pages/UserProfilePage.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
