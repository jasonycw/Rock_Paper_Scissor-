package cs4280.model;

public class RoundResult {
    private final int PAPER_CODE = 1;
    private final int SCISSOR_CODE = 2;
    private final int ROCK_CODE = 3;

    private int mPlayerDecision = PAPER_CODE;
    private int mNPCDecision = SCISSOR_CODE;

    public RoundResult() {

    }

    public int getmPlayerDecision() {
        return mPlayerDecision;
    }

    public void setmPlayerDecision(int mPlayerDecision) {
        this.mPlayerDecision = mPlayerDecision;
    }

    public int getmNPCDecision() {
        return mNPCDecision;
    }

    public void setmNPCDecision(int mNPCDecision) {
        this.mNPCDecision = mNPCDecision;
    }

    public boolean isPlayerWin() {
        if (mPlayerDecision == PAPER_CODE) {
            return mNPCDecision == ROCK_CODE;
        }
        if (mPlayerDecision == SCISSOR_CODE) {
            return mNPCDecision == PAPER_CODE;
        }
        if (mPlayerDecision == ROCK_CODE) {
            return mNPCDecision == SCISSOR_CODE;
        }
        return false;
    }
}
