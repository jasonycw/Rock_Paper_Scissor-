package cs4280.bean;

public class PageProgressBean {
    private String mBreadcrumb = "/login";
    private boolean isLoggedIn = false;

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getmBreadcrumb() {
        return mBreadcrumb;
    }

    public void setmBreadcrumb(String mBreadcrumb) {
        this.mBreadcrumb = mBreadcrumb;
    }

    public static String getBeanName() {
        return "pageInfo";
    }
}
