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

    public int getPlayerScroe() {
        if (mPlayerDecision == PAPER_CODE) {
            if(mNPCDecision == ROCK_CODE)
                return 1;
            else if (mNPCDecision == PAPER_CODE)
                return 0;
        }
        if (mPlayerDecision == SCISSOR_CODE) {
            if(mNPCDecision == PAPER_CODE)
                return 1;
            else if (mNPCDecision == SCISSOR_CODE)
                return 0;
        }
        if (mPlayerDecision == ROCK_CODE) {
            if(mNPCDecision == SCISSOR_CODE)
                return 1;
            else if (mNPCDecision == ROCK_CODE)
                return 0;
        }
        return -1;
    }
}
