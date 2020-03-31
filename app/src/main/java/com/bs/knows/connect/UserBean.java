package com.bs.knows.connect;

import java.util.List;

public class UserBean {

    /**
     * error : false
     * users : [{"id":"1","username":"15011111111","password":"123456"}]
     */

    private boolean error;
    private List<UsersBean> users;
    private boolean passwordCorrect;
    private boolean userIsExist;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public boolean isPassword() {
        return passwordCorrect;
    }

    public void setPassword(boolean password) {
        this.passwordCorrect = password;
    }

    public boolean isUserIsExist() {
        return userIsExist;
    }

    public void setUserIsExist(boolean userIsExist) {
        this.userIsExist = userIsExist;
    }

    public static class UsersBean {
        /**
         * id : 1
         * username : 15011111111
         * password : 123456
         */

        private String id;
        private String username;
        private String password;

        public UsersBean(String username) {
            this.username = username;
        }

        public UsersBean(String username, String password) {
            this.username = username;
            this.password = password;
        }

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}