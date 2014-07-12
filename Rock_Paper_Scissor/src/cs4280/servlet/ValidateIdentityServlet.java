package cs4280.servlet;

import cs4280.bean.PlayerBean;
import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateIdentityServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getRemoteUser();

        /////////////////////////////////////////////
        /*
        Renee Workspace, check session here, kick the user back if needed
        */
        HttpSession session = request.getSession(false);

        /////////////////////////////////////////////

        /////////////////////////////////////////////
        /*
        Louis Workspace
        */
        try {
            //grab info
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM PlayerAccount WHERE username = ? and password=?");
            stmt.setString(1, request.getParameter("j_username"));
            stmt.setString(2, request.getParameter("j_password"));
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                //login attempt failed, kick the user out
                response.sendRedirect("/redirect");
            } else {
                //save to bean
                PlayerBean player = new PlayerBean();
                player.setmId(rs.getString("username"));
                player.setmTheme(rs.getString("theme"));
                player.setmTotalWin(rs.getInt("win"));
                player.setmTotalLose(rs.getInt("lose"));
                player.setmTotalPlayTime(rs.getInt("playtime"));

                session.setAttribute("playerInfo",player);
                /////////////////////////////////////////////
                /*
                Renee Workspace, user is now authenticated, bean is ready, save any information if needed
                */

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /////////////////////////////////////////////
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
