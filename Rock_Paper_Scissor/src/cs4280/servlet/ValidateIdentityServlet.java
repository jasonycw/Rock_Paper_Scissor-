package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.WrongCredentialException;
import util.DBConnection;
import util.ProjectUrl;

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
        PlayerBean player = new PlayerBean();
        if (!request.getParameter("test").equals("true")) {
            try {
                //grab info
                ResultSet rs = getUserInfo(request.getParameter("j_username"), request.getParameter("j_password"));
                player = toPlayerBean(rs);

            } catch (WrongCredentialException e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("playerInfo", player);
        session.setAttribute("ackMsg", new AckBean());

        /////////////////////////////////////////////
                /*
                Renee Workspace, user is now authenticated, bean is ready, save any information if needed
                */

//                session.setAttribute("username", username);
//                session.setAttribute("isLoginedIn", "1");
//                session.setAttribute("Background", "1/2/3");
//                session.setAttribute("currentPage", "main");
        // when this page direct to another page, have to set attribute first.. when page is first loaded, check attribute see if is really this page ==> redirect
        // set time

        /////////////////////////////////////////////


        //Forward response to jsp for display
        response.sendRedirect(ProjectUrl.getBaseUrl(request) + "/main");
    }

    /////////////////////////////////////////////

    private PlayerBean toPlayerBean(ResultSet rs) {
        PlayerBean player = new PlayerBean();
        try {
            if (rs.next()) {
                player.setmUsername(rs.getString("username"));
                player.setmPreferredTheme(rs.getString("theme"));
                player.setmWinCount(rs.getInt("win"));
                player.setmLoseCount(rs.getInt("lose"));
                player.setmTotalPlayTime(rs.getInt("playtime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    private ResultSet getUserInfo(String username, String password) throws WrongCredentialException {
        ResultSet rs = null;
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement stmt = con.prepareStatement("SELECT username, password, theme, win, lose, playtime FROM PlayerAccount WHERE username = ? and password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                throw new WrongCredentialException();
            }

            stmt = con.prepareStatement("SELECT username, password, theme, win, lose, playtime FROM PlayerAccount WHERE username = ? and password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
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
