package com.bs.knows.connect.bean;

public class UserRegister {

    /**
     * error : false
     * registerSuccess : true
     */

    private boolean error;
    private boolean registerSuccess;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isRegisterSuccess() {
        return registerSuccess;
    }

    public void setRegisterSuccess(boolean registerSuccess) {
        this.registerSuccess = registerSuccess;
    }
}
