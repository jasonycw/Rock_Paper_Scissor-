package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PlayerBean;
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
        HttpSession session = request.getSession();

        /////////////////////////////////////////////

        /////////////////////////////////////////////
        /*
        Louis Workspace
        */
        boolean devMode = request.getParameter("test").equals("true");

        if (devMode || isUserValid(request.getParameter("j_username"), request.getParameter("j_password"))) {
            PlayerBean player = new PlayerBean();
            if (!devMode) {
                //grab info
                ResultSet rs = getUserInfo(request.getParameter("j_username"), request.getParameter("j_password"));
                player = updateUserInfo(player, rs);

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


    private PlayerBean updateUserInfo(PlayerBean player, ResultSet rs) {
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
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT username, password, theme, win, lose, playtime FROM PlayerAccount WHERE username = ? and password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private static boolean isUserValid(String username, String password) {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
