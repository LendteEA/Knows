package com.bs.knows.connect.bean;

public class HistoryDataFilePath {

    /**
     * error : false
     * scanfilepath : /home/scanData/15011111111/scandetail/Crop1586931064542.json
     */

    private boolean error;
    private String scanfilepath;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getScanfilepath() {
        return scanfilepath;
    }

    public void setScanfilepath(String scanfilepath) {
        this.scanfilepath = scanfilepath;
    }
}
