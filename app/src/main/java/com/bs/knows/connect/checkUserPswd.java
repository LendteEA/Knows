package com.bs.knows.connect;

public class checkUserPswd {

    /**
     * error : false
     * password : true
     */

    private boolean error;
    private boolean password;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isPassword() {
        return password;
    }

    public void setPassword(boolean password) {
        this.password = password;
    }
}
