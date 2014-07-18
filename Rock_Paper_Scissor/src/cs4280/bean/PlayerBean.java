package cs4280.bean;

import util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerBean {
    private String mUsername;
    private String mPreferredTheme;
    private int mWinCount;
    private int mLoseCount;
    private int mDrawCount;
    private String mLoginTime;

    public PlayerBean() {
        mUsername = "Developer";
        mPreferredTheme = "Default";
        mWinCount = 100;
        mLoseCount = 1;
        mLoginTime=" 2009-10-02 16:52:30 ";
        /*
        grab current time through db
         */
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
        Statement stmt = con.createStatement();
        String sql = "UPDATE PlayerAccount SET  ";
        stmt.executeUpdate(sql);
    }
}
