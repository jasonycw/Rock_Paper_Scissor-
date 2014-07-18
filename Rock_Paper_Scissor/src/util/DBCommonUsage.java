package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCommonUsage {
    public String getLoginTime(String username, String password) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.prepareStatement("SELECT login_time FROM PlayerAccount WHERE username = ? and password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            return rs.getString("login_time");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
