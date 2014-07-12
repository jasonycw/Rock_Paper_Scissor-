package cs4280.bean;

import util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerBean {
    private String mId;
    private String mTheme;
    private int mTotalWin;
    private int mTotalLose;
    private int mTotalPlayTime;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
        /*
        JDBC Update
         */
    }

    public String getmTheme() {
        return mTheme;
    }

    public void setmTheme(String mTheme) {
        this.mTheme = mTheme;
    }

    public int getmTotalWin() {
        return mTotalWin;
    }

    public void setmTotalWin(int mTotalWin) {
        this.mTotalWin = mTotalWin;
    }

    public int getmTotalLose() {
        return mTotalLose;
    }

    public void setmTotalLose(int mTotalLose) {
        this.mTotalLose = mTotalLose;
    }

    public int getmTotalPlayTime() {
        return mTotalPlayTime;
    }

    public void setmTotalPlayTime(int mTotalPlayTime) {
        this.mTotalPlayTime = mTotalPlayTime;
    }

    public void update() throws SQLException {
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        String sql="update ";
        stmt.executeUpdate(sql);
    }
}
