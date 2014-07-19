package cs4280.bean;

public class AckBean {
    private String mType;
    private String mMessage;

    public AckBean() {
        this.mType = "Success";
        this.mMessage = "Hi buddy~";
    }

    public AckBean(String mType, String mMessage) {
        this.mType = mType;
        this.mMessage = mMessage;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public void setmMessage(String mMessage) {

        if (mMessage != null) {
            this.mMessage = mMessage;
        }
    }

    public String getHTMLOutput() {
        if (mMessage == null) {
            return "";
        } else {
            return "<div class=\"ack-box " + mType + "\"><p>" + mMessage + "</p><div class=\"ack-progress\"></div></div>";
        }

    }

    public void clean() {
        setmMessage("");
    }
}
