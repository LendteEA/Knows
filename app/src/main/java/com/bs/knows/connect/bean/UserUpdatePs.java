package com.bs.knows.connect.bean;

public class UserUpdatePs {

    /**
     * error : false
     * updateSuccess : true
     * old_password : true
     */

    private boolean error;
    private boolean updateSuccess;
    private boolean old_password;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isUpdateSuccess() {
        return updateSuccess;
    }

    public void setUpdateSuccess(boolean updateSuccess) {
        this.updateSuccess = updateSuccess;
    }

    public boolean isOld_password() {
        return old_password;
    }

    public void setOld_password(boolean old_password) {
        this.old_password = old_password;
    }
}
