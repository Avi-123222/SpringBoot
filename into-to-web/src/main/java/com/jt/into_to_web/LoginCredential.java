package com.jt.into_to_web;

public class LoginCredential {
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        System.out.println("setUsername is called");
        this.username = username;
    }
    public void setPassword(String password) {
        System.out.println("setPassword is called");
        this.password = password;
    }

    
    
}
