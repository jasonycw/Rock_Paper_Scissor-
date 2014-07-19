package cs4280.bean;

import util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerBean {
    private String mUsername = "Developer";
    private String mPreferredTheme = "Default";
    private int mWinCount = 100;
    private int mLoseCount = 1;
    private int mTotalPlayTime = 6;
    private int theme = 1;

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public PlayerBean() {

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

    public int getmTotalPlayTime() {
        return mTotalPlayTime;
    }

    public void setmTotalPlayTime(int mTotalPlayTime) {
        this.mTotalPlayTime = mTotalPlayTime;
    }

    public void update() throws SQLException {
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "UPDATE PlayerAccount SET  ";
        stmt.executeUpdate(sql);
    }
}
