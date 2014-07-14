package cs4280.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RecordServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /////////////////////////////////////////////
        /*
        Renee Workspace, check session here, kick the user back if needed
        */

        HttpSession session = request.getSession();

        /////////////////////////////////////////////

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pages/RecordPage.jsp");
        dispatcher.forward(request,response);
    }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
