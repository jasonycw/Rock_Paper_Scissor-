package cs4280.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getRemoteUser();

        /*
            Louis, JDBC grab user data and save to bean
        */

         HttpSession session = request.getSession();
         session.setAttribute("username",username);
         session.setAttribute("isLoginedIn", "1");
         session.setAttribute("Background","1/2/3");
         session.setAttribute("currentPage","main");
           // when this page direct to another page, have to set attribute first.. when page is first loaded, check attribute see if is really this page ==> redirect
          // set time

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
