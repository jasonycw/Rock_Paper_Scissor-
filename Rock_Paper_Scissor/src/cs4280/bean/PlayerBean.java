package cs4280.bean;

import util.DBConnection;
import util.Time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerBean {

    private String mUsername;
    private String mPassword;
    private String mPreferredTheme;
    private int mWinCount;
    private int mLoseCount;
    private int mDrawCount;
    private String mLoginTime;
    private String mTotalOnlineTime;

    public String getTotalOnlineTime() {
        return mTotalOnlineTime;
    }

    public void updateTotalOnlineTime() {
        mTotalOnlineTime = Long.parseLong(mTotalOnlineTime) + Long.parseLong(Time.getCurrentTimeInUnix()) - Long.parseLong(mLoginTime) + "";
    }

    public PlayerBean() {
        mUsername = "Developer";
        mPassword = "123";
        mPreferredTheme = "1";
        mWinCount = 100;
        mLoseCount = 1;
        mDrawCount = 1;
        mLoginTime = "1405641600";
        mTotalOnlineTime = "0";
        /*
        grab current time through db
         */
    }

    public PlayerBean(ResultSet rs) {
        try {
            if (rs.next()) {
                setmUsername(rs.getString("username"));
                setmPassword(rs.getString("password"));
                setmPreferredTheme(rs.getString("theme"));
                setmWinCount(rs.getInt("win"));
                setmLoseCount(rs.getInt("lose"));
                setmDrawCount(rs.getInt("draw"));
                setmLoginTime(rs.getString("login_time"));
                setmTotalOnlineTime(rs.getString("total_playtime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public void setmTotalOnlineTime(String mTotalPlaytime) {
        this.mTotalOnlineTime = mTotalOnlineTime;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPreferredTheme() {
        return mPreferredTheme;
    }

    public void setmPreferredTheme(String mPreferredTheme) {
        this.mPreferredTheme = mPreferredTheme;
    }

    public int getmWinCount() {
        return mWinCount;
    }

    public void setmWinCount(int mWinCount) {
        this.mWinCount = mWinCount;
    }

    public int getmLoseCount() {
        return mLoseCount;
    }

    public void setmLoseCount(int mLoseCount) {
        this.mLoseCount = mLoseCount;
    }

    public int getmDrawCount() {
        return mDrawCount;
    }

    public void setmDrawCount(int mDrawCount) {
        this.mDrawCount = mDrawCount;
    }

    public String getmLoginTime() {
        return mLoginTime;
    }

    public void setmLoginTime(String mLoginTime) {
        this.mLoginTime = mLoginTime;
    }

    public void update() throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE PlayerAccount SET password=? , win=?, lose=?, draw=?, theme=?, login_time=?, total_playtime=? where username=?");
        stmt.setString(1, mPassword);
        stmt.setInt(2, mWinCount);
        stmt.setInt(3, mLoseCount);
        stmt.setInt(4, mDrawCount);
        stmt.setString(5, mPreferredTheme);
        stmt.setString(6, mLoginTime);
        stmt.setString(7, mTotalOnlineTime);
        stmt.setString(8, mUsername);

        stmt.executeUpdate();
    }
}
