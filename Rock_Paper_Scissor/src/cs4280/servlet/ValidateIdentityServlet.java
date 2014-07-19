package cs4280.servlet;

import cs4280.bean.PageProgressBean;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ValidateIdentityServlet extends HttpServlet {
    String sec;
    long time;

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
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        if (devMode || isUserValid(username, password)) {
            PlayerBean player = new PlayerBean();
            Date date = new Date();
            time = date.getTime();
            sec = String.valueOf(time);
            player.setmLoginTime(sec);
            if (!devMode) {
                //grab info
                ResultSet rs = getUserInfo(username, password);
                player = updateUserInfo(player, rs);
            }
            long result = time + 100;
            session.setAttribute("playerInfo", player);
            session.setAttribute("sec", result);
            session.setAttribute("ackMsg", new AckBean());
            PageProgressBean pageProgress = new PageProgressBean();
            pageProgress.setIsLoggedIn(true);

            session.setAttribute("pageInfo", pageProgress);
        }

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
            PreparedStatement stmt = con.prepareStatement("SELECT username, password, theme, win, lose, draw, login_time FROM PlayerAccount WHERE username = ? AND password=?");
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

            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM PlayerAccount WHERE username = ? AND password=?");
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
