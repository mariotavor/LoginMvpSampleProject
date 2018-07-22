package com.mario.example.loginsampleproject.user;

/**
 * Created by mario on 18/07/18.
 * User login basic entity, decoupled from implementation
 */


public class UserLoginInfo {
    private String email="";
    private String password="";

    public UserLoginInfo() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
