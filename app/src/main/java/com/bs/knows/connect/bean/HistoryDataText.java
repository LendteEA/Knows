package com.bs.knows.connect.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryDataText {


    /**
     * error : false
     * filedata : {"username":"http://39_107_119_177/a_downloadimg_php?path:/home/scanData/15011111111/scanimgfile/Crop1586931064542.jpg","ScanData":[{"data":[{"text":"框","prob":1,"chars":[{"char":"框","prob":1}],"box":[749,39,889,37,891,125,751,127],"textprob":0.86},{"text":"表","prob":1,"chars":[{"char":"表","prob":1}],"box":[577,36,725,35,726,131,578,133],"textprob":0.76},{"text":"票亮的A","prob":1,"chars":[{"char":"票","prob":1},{"char":"亮","prob":1},{"char":"的","prob":1},{"char":"A","prob":1}],"box":[77,44,362,44,362,131,77,131],"textprob":0.92}],"errCode":0}]}
     */

    private boolean error;
    private FiledataBean filedata;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public FiledataBean getFiledata() {
        return filedata;
    }

    public void setFiledata(FiledataBean filedata) {
        this.filedata = filedata;
    }

    public static class FiledataBean {
        /**
         * username : http://39_107_119_177/a_downloadimg_php?path:/home/scanData/15011111111/scanimgfile/Crop1586931064542.jpg
         * ScanData : [{"data":[{"text":"框","prob":1,"chars":[{"char":"框","prob":1}],"box":[749,39,889,37,891,125,751,127],"textprob":0.86},{"text":"表","prob":1,"chars":[{"char":"表","prob":1}],"box":[577,36,725,35,726,131,578,133],"textprob":0.76},{"text":"票亮的A","prob":1,"chars":[{"char":"票","prob":1},{"char":"亮","prob":1},{"char":"的","prob":1},{"char":"A","prob":1}],"box":[77,44,362,44,362,131,77,131],"textprob":0.92}],"errCode":0}]
         */

        private String username;
        private List<ScanDataBean> ScanData;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<ScanDataBean> getScanData() {
            return ScanData;
        }

        public void setScanData(List<ScanDataBean> ScanData) {
            this.ScanData = ScanData;
        }

        public static class ScanDataBean {
            /**
             * data : [{"text":"框","prob":1,"chars":[{"char":"框","prob":1}],"box":[749,39,889,37,891,125,751,127],"textprob":0.86},{"text":"表","prob":1,"chars":[{"char":"表","prob":1}],"box":[577,36,725,35,726,131,578,133],"textprob":0.76},{"text":"票亮的A","prob":1,"chars":[{"char":"票","prob":1},{"char":"亮","prob":1},{"char":"的","prob":1},{"char":"A","prob":1}],"box":[77,44,362,44,362,131,77,131],"textprob":0.92}]
             * errCode : 0
             */

            private int errCode;
            private List<DataBean> data;

            public int getErrCode() {
                return errCode;
            }

            public void setErrCode(int errCode) {
                this.errCode = errCode;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * text : 框
                 * prob : 1
                 * chars : [{"char":"框","prob":1}]
                 * box : [749,39,889,37,891,125,751,127]
                 * textprob : 0.86
                 */

                private String text;
                private int prob;
                private double textprob;
                private List<CharsBean> chars;
                private List<Integer> box;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getProb() {
                    return prob;
                }

                public void setProb(int prob) {
                    this.prob = prob;
                }

                public double getTextprob() {
                    return textprob;
                }

                public void setTextprob(double textprob) {
                    this.textprob = textprob;
                }

                public List<CharsBean> getChars() {
                    return chars;
                }

                public void setChars(List<CharsBean> chars) {
                    this.chars = chars;
                }

                public List<Integer> getBox() {
                    return box;
                }

                public void setBox(List<Integer> box) {
                    this.box = box;
                }

                public static class CharsBean {
                    /**
                     * char : 框
                     * prob : 1
                     */

                    @SerializedName("char")
                    private String charX;
                    private int prob;

                    public String getCharX() {
                        return charX;
                    }

                    public void setCharX(String charX) {
                        this.charX = charX;
                    }

                    public int getProb() {
                        return prob;
                    }

                    public void setProb(int prob) {
                        this.prob = prob;
                    }
                }
            }
        }
    }
}
