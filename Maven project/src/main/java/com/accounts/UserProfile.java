package com.accounts;

public class UserProfile {
    private String name;
    private String pass;
    private String email;
    private Integer balance = 500;

    public UserProfile(String name) {
        this.name = name;
        this.pass = name;
    }

    public UserProfile(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBalance() {
        return balance;
    }
}
