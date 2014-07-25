package util;

import cs4280.model.Rank;

import java.sql.*;
import java.util.ArrayList;

public class DBCommonUsage {
    public static String getLoginTime(String username, String password) throws SQLException {
        Connection con;
        ResultSet rs;
        PreparedStatement stmt;

        con = DBConnection.getConnection();
        stmt = con.prepareStatement("SELECT login_time FROM PlayerAccount WHERE username = ? and password=?");
        stmt.setString(1, username);
        stmt.setString(2, password);
        rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("login_time");
        }

        return "";
    }

    public static ArrayList<Rank> getWinRank() {
        ResultSet rs;
        ArrayList<Rank> rankList = new ArrayList<Rank>();
        try {
            int i = 1;
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT username, win from PlayerAccount ORDER BY win DESC";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rankList.add(new Rank(i, rs.getString("username"), rs.getInt("win") + ""));
                i++;
            }
        } catch (SQLException ignored) {
        }
        return rankList;
    }

    public static ArrayList<Rank> getLoseRank() {
        ResultSet rs;
        ArrayList<Rank> rankList = new ArrayList<Rank>();
        try {
            int i = 1;
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT username, lose from PlayerAccount ORDER BY lose DESC";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rankList.add(new Rank(i, rs.getString("username"), rs.getInt("lose") + ""));
                i++;
            }
        } catch (SQLException ignored) {
        }
        return rankList;
    }

    public static ArrayList<Rank> getWLRateRank() {
        ResultSet rs;
        ArrayList<Rank> rankList = new ArrayList<Rank>();
        try {
            int i = 1;
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT username, (win*100.0/(win+lose)) rate from PlayerAccount ORDER BY (win/(win+lose)*100.0) desc";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rankList.add(new Rank(i, rs.getString("username"), rs.getInt("rate") + ""));
                i++;
            }
        } catch (SQLException ignored) {
        }
        return rankList;
    }

    public static ArrayList<Rank> getNumberOfGameRank() {
        ResultSet rs;
        ArrayList<Rank> rankList = new ArrayList<Rank>();
        try {
            int i = 1;
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT username, win+lose+draw GameCount from PlayerAccount ORDER BY GameCount desc";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rankList.add(new Rank(i, rs.getString("username"), rs.getInt("GameCount") + ""));
                i++;
            }
        } catch (SQLException ignored) {
        }
        return rankList;
    }

    public static int getTotalGamePlayed() {
        ResultSet rs;
        try {
            int i = 1;
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT SUM(win+lose+draw) Count from PlayerAccount";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("Count");
            }
        } catch (SQLException ignored) {
        }
        return 0;
    }
}
