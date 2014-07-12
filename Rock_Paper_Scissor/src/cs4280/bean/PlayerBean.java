package cs4280.bean;

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
        /*
        JDBC Update
         */
    }

    public int getmTotalWin() {
        return mTotalWin;
    }

    public void setmTotalWin(int mTotalWin) {
        this.mTotalWin = mTotalWin;
        /*
        JDBC Update
         */
    }

    public int getmTotalLose() {
        return mTotalLose;
    }

    public void setmTotalLose(int mTotalLose) {
        this.mTotalLose = mTotalLose;
        /*
        JDBC Update
         */
    }

    public int getmTotalPlayTime() {
        return mTotalPlayTime;
    }

    public void setmTotalPlayTime(int mTotalPlayTime) {
        this.mTotalPlayTime = mTotalPlayTime;
        /*
        JDBC Update
         */
    }
}
