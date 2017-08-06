package com.wy.lwl.volley.http;

/**
 * Created by lwl on 2017/8/6.
 */

public class RequestInfo {
    private String userName;

    private String pasword;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "userName='" + userName + '\'' +
                ", pasword='" + pasword + '\'' +
                '}';
    }
}
