package com.bs.knows.connect.bean;

public class checkUserId {


    /**
     * error : false
     * userIsExist : true
     */

    private boolean error;
    private boolean userIsExist;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isUserIsExist() {
        return userIsExist;
    }

    public void setUserIsExist(boolean userIsExist) {
        this.userIsExist = userIsExist;
    }
}
