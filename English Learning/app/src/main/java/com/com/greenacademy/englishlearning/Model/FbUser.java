package com.com.greenacademy.englishlearning.Model;

public class FbUser {
    private String name;
    private String email;
    private String urlAvarta;

    public FbUser() {
    }

    public FbUser(String name, String email, String urlAvarta) {
        this.name = name;
        this.email = email;
        this.urlAvarta = urlAvarta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlAvarta() {
        return urlAvarta;
    }

    public void setUrlAvarta(String urlAvarta) {
        this.urlAvarta = urlAvarta;
    }

    @Override
    public String toString() {
        return "FbUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", urlAvarta='" + urlAvarta + '\'' +
                '}';
    }
}
