package com.bs.knows.utils;

import cn.bmob.v3.BmobUser;

public class UserBean extends BmobUser {
    private String photo;
    private String url;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
