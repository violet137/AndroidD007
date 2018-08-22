package com.com.greenacademy.englishlearning.Model;

public class Account {
    private String username;
    private String password;
    private String fullname;
    private String email;

    public Account(String username, String password, String fullname, String email) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFullname(fullname);
        this.setEmail(email);
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
