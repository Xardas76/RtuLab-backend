package com.accounts;

public class User {
    private String login;
    private String pass;

    public User(String login) {
        this.login = login;
        this.pass = login;
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}
