package cs4280.servlet;

import util.ProjectUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
Servlet handling logout request, should forward to index page to relogin after session invalidation
 */
public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /////////////////////////////////////////////
        /*
        Renee Workspace, clean up everything
        */

        /////////////////////////////////////////////

        /////////////////////////////////////////////
        /*
        Louis Workspace, clean up db last login time
        */
        HttpSession session = request.getSession();
        session.invalidate();
        /////////////////////////////////////////////

        /////////////////////////////////////////////
        /*
        Lewis, create ackBean to tell user to login again
         */
        /////////////////////////////////////////////

        //Kick the user back to login page
        response.sendRedirect(ProjectUrl.getBaseUrl(request) + "/redirect");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
