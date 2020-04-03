package com.bs.knows.connect.bean;

public class UploadNewPic {


    /**
     * error : false
     * uploadpic : true
     * savepath : /home/scanData/15011111112/scanimgfile/d5fa3467a44c653b5721ea9944b5a69a.jpg
     * date : 2020-04-03
     */

    private boolean error;
    private boolean uploadpic;
    private String savepath;
    private String date;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isUploadpic() {
        return uploadpic;
    }

    public void setUploadpic(boolean uploadpic) {
        this.uploadpic = uploadpic;
    }

    public String getSavepath() {
        return savepath;
    }

    public void setSavepath(String savepath) {
        this.savepath = savepath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
