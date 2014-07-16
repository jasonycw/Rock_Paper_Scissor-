package cs4280.bean;

import cs4280.model.RoundResult;

import java.util.Queue;

public class GameProgressBean {
    private final int MAXROUND = 3;
    private int mCurrentRound = 0;
    private final int PAPERCODE = 1;
    private final int SCISSORCODE = 2;
    private final int ROCKCODE = 3;
    private Queue<RoundResult> mResult;

    public int getMAXROUND() {
        return MAXROUND;
    }

    public int getmCurrentRound() {
        return mCurrentRound;
    }

    private void setmCurrentRound(int mCurrentRound) {
        this.mCurrentRound = mCurrentRound;
    }

    public Queue<RoundResult> getmResult() {
        return mResult;
    }

    public void updateCurrentRoundResult(int playerDecision, int npcDecision) {
        if ((playerDecision != ROCKCODE && playerDecision != PAPERCODE && playerDecision != SCISSORCODE) || (npcDecision != ROCKCODE && npcDecision != PAPERCODE && npcDecision != SCISSORCODE)) {
            return;
        }
        RoundResult temp = new RoundResult();
        temp.setmPlayerDecision(playerDecision);
        temp.setmNPCDecision(npcDecision);
        mResult.offer(temp);
        setmCurrentRound(mCurrentRound + 1);
    }
}
