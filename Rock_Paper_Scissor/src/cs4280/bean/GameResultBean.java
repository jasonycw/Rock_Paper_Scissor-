package cs4280.bean;

public class GameResultBean {
    private final int MAXROUND=3;
    private int mCurrentRound=0;
    private int[] mGameResult={0};

    public int getMAXROUND() {
        return MAXROUND;
    }

    public int getmCurrentRound() {
        return mCurrentRound;
    }

    public void setmCurrentRound(int mCurrentRound) {
        this.mCurrentRound = mCurrentRound;
    }

    public int[] getmGameResult() {
        return mGameResult;
    }

    public void setmGameResult(int round, int mGameResult) {
        this.mGameResult[round] = mGameResult;
    }
}
