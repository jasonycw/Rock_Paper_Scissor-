package cs4280.bean;

import cs4280.model.RoundResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GameProgressBean {
    private final int MAXROUND = 3;
    private int mCurrentRound = 0;
    private final int PAPERCODE = 1;
    private final int SCISSORCODE = 2;
    private final int ROCKCODE = 3;

    private int mScore = 0;
    private int npcScore = 0;

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
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public int getNpcScore() {
        return npcScore;
    }

    public void setNpcScore(int npcScore) {
        this.npcScore = npcScore;
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
        RoundResult temp = new RoundResult();
        temp.setmPlayerDecision(playerDecision);
        temp.setmNPCDecision(npcDecision);
        mResult.add(temp);
        if(temp.getPlayerScroe() == -1){
            npcScore += 1;
        }else if(temp.getPlayerScroe() == 1){
            mScore += 1;
        }
        setmCurrentRound(mCurrentRound + 1);
    }
}
