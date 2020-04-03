package com.bs.knows.connect.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserHistoryData {

    /**
     * error : false
     * history : [{"id":"1","username":"15011111111","create_date":"2020-04-02","update_date":"2020-04-02","title":"识别1.1","abstract":"简介1.1","file_path":"/wanglijing/scandata/15011111111/file_path/a1.1.txt","img_path":"/wanglijing/scandata/15011111111/img_path/a1.1.img"},{"id":"3","username":"15011111111","create_date":"2020-04-02","update_date":"2020-04-02","title":"识别1.2","abstract":"简介1.2","file_path":"/wanglijing/scandata/15011111111/file_path/a1.2.txt","img_path":"/wanglijing/scandata/15011111111/img_path/a1.2.img"}]
     */

    private boolean error;
    private List<HistoryBean> history;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<HistoryBean> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryBean> history) {
        this.history = history;
    }

    public static class HistoryBean {
        /**
         * id : 1
         * username : 15011111111
         * create_date : 2020-04-02
         * update_date : 2020-04-02
         * title : 识别1.1
         * abstract : 简介1.1
         * file_path : /wanglijing/scandata/15011111111/file_path/a1.1.txt
         * img_path : /wanglijing/scandata/15011111111/img_path/a1.1.img
         */

        private String id;
        private String username;
        private String create_date;
        private String update_date;
        private String title;
        @SerializedName("abstract")
        private String abstractX;
        private String file_path;
        private String img_path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }
    }
}
