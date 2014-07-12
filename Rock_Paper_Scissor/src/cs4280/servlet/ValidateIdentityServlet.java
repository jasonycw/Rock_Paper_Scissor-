package cs4280.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ValidateIdentityServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getRemoteUser();

        /////////////////////////////////////////////
        /*
        Renee Workspace, check session here, kick the user back if needed
        */

        /////////////////////////////////////////////

        /////////////////////////////////////////////
        /*
        Louis Workspace, might verify identity, grab user info and save to bean, else kick the user back if wrong pw
        */
        try {
            if (isValidUser(request.getParameter("j_username"), request.getParameter("j_password"))) {

            } else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /////////////////////////////////////////////

        /////////////////////////////////////////////
        /*
        Renee Workspace, user is now authenticated, save any information if needed
        */

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("isLoginedIn", "1");
        session.setAttribute("Background", "1/2/3");
        session.setAttribute("currentPage", "main");
        // when this page direct to another page, have to set attribute first.. when page is first loaded, check attribute see if is really this page ==> redirect
        // set time

        /////////////////////////////////////////////


        //Forward response to jsp for display
        response.sendRedirect("/main");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private boolean isValidUser(String username, String password) throws SQLException {
//        PreparedStatement stmt = getConnection().prepareStatement("select * from UserSetting where username=? and password=?");
//        stmt.setString(1, username);
//        stmt.setString(2, password);
//        ResultSet rs = stmt.executeQuery();
//        return rs != null;
        return true;
    }
}
