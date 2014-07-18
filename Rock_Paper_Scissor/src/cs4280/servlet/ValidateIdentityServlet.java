package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PlayerBean;
import util.DBConnection;
import util.ProjectUrl;
import util.SessionValidation;

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
        HttpSession session = request.getSession();

        /////////////////////////////////////////////

        /////////////////////////////////////////////
        /*
        Louis Workspace
        */
        if (isUserValid(request.getParameter("j_username"), request.getParameter("j_password")) || (request.getParameter("test").equals("true"))) {
            PlayerBean player = new PlayerBean();
            if (!request.getParameter("test").equals("true")) {
                //grab info
                ResultSet rs = getUserInfo(request.getParameter("j_username"), request.getParameter("j_password"));
                player = toPlayerBean(rs);

            }
            if (isSessionValid(player)) {
                session.setAttribute("playerInfo", player);
            } else {
                //break-in attempt
            }
        }


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
     /*
    Lewis Workspace,
        get time from bean
        grab db login time
        compare
     */
    private boolean isSessionValid(PlayerBean player) {

        return true;
    }

    private boolean isUserValid(String username, String password) {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM PlayerAccount WHERE username = ? and password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private PlayerBean toPlayerBean(ResultSet rs) {
        PlayerBean player = new PlayerBean();
        try {
            if (rs.next()) {
                player.setmUsername(rs.getString("username"));
                player.setmPreferredTheme(rs.getString("theme"));
                player.setmWinCount(rs.getInt("win"));
                player.setmLoseCount(rs.getInt("lose"));
                player.setmDrawCount(rs.getInt("draw"));
                player.setmLoginTime(rs.getString("login_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    private ResultSet getUserInfo(String username, String password) {
        ResultSet rs = null;
        try {
            Connection con = null;

            PreparedStatement stmt = con.prepareStatement("SELECT username, password, theme, win, lose, playtime FROM PlayerAccount WHERE username = ? and password=?");
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
