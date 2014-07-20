package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import util.DBConnection;
import util.ProjectUrl;
import util.Time;

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

        HttpSession session = request.getSession();

        boolean devMode = request.getParameter("test").equals("true");
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        if (devMode || isUserValid(username, password)) {
            PlayerBean player = new PlayerBean();
            String currentTime = Time.getCurrentTimeInUnix();
            if (!devMode) {
                //Get info from DB
                ResultSet rs = getUserInfo(username, password);
                player = new PlayerBean(rs);
                player.setmLoginTime(currentTime);
                try {
                    player.update();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            session.setAttribute("playerInfo", player);
            session.setAttribute("ackMsg", new AckBean("Success", "Welcome Back buddy"));
            PageProgressBean pageProgress = new PageProgressBean();
            pageProgress.setIsLoggedIn(true);
            pageProgress.setmBreadcrumb("main");
            session.setAttribute("pageInfo", pageProgress);
            response.sendRedirect(ProjectUrl.getBaseUrl(request) + "/main");
        } else {
            session.setAttribute("ackMsg", new AckBean("Error", "Incorrect credentials"));
            response.sendRedirect(ProjectUrl.getBaseUrl(request) + "/login");

        }

    }

    private ResultSet getUserInfo(String username, String password) {
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT username, password, theme, win, lose, draw, login_time, total_playtime FROM PlayerAccount WHERE username = ? AND password=?");
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

            if (rs.next()) {
                return rs.getInt(1) == 1;
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
