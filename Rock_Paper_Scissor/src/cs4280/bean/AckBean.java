package cs4280.bean;

public class AckBean {
    private String mType = "warning";
    private String mMessage = "This is warning";

    public void setmType(String mType) {
        this.mType = mType;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    private String getHTMLOutput() {
        return "<p class=\"" + mType + "\">\"" + mMessage + "\"</p>";
    }
}