package cs4280.bean;

import cs4280.model.RoundResult;

import java.util.ArrayList;

public class GameProgressBean {
    private final int MAXROUND = 3;
    private int mCurrentRound = 0;
    private final int PAPERCODE = 1;
    private final int SCISSORCODE = 2;
    private final int ROCKCODE = 3;

    private int mPlayerScore = 0;
    private int mNPCScore = 0;

    public int getPAPERCODE() {
        return PAPERCODE;
    }

    public int getROCKCODE() {
        return ROCKCODE;
    }

    public int getSCISSORCODE() {
        return SCISSORCODE;
    }


    private ArrayList<RoundResult> mResult = new ArrayList<RoundResult>();

    public int getmScore() {
        return mPlayerScore;
    }

    public void setmScore(int mScore) {
        this.mPlayerScore = mScore;
    }

    public int getNpcScore() {
        return mNPCScore;
    }

    public void setmNPCScore(int npcScore) {
        this.mNPCScore = npcScore;
    }

    public int getMAXROUND() {
        return MAXROUND;
    }

    public int getmCurrentRound() {
        return mCurrentRound;
    }

    private void setmCurrentRound(int mCurrentRound) {
        this.mCurrentRound = mCurrentRound;
    }

    public ArrayList<RoundResult> getmResult() {
        return mResult;
    }

    public void updateCurrentRoundResult(int playerDecision, int npcDecision) {

        if ((playerDecision != ROCKCODE && playerDecision != PAPERCODE && playerDecision != SCISSORCODE) || (npcDecision != ROCKCODE && npcDecision != PAPERCODE && npcDecision != SCISSORCODE)) {
            return;
        }
        if (mCurrentRound >= 3) {
            return;
        }
        RoundResult temp = new RoundResult();
        temp.setmPlayerDecision(playerDecision);
        temp.setmNPCDecision(npcDecision);
        mResult.add(temp);
        if (temp.getPlayerScore() == -1) {
            mNPCScore += 1;
        }else if (temp.getPlayerScore() == 1) {
            mPlayerScore += 1;
        }
        setmCurrentRound(mCurrentRound + 1);
    }
    public static String getBeanName(){
        return "gameInfo";
    }
}
