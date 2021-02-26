package com.resources;

import com.entities.Client;

public class ClientResource {
    private String login;
    private String name;
    private String password;
    private Integer balance;

    ClientResource() {}

    public ClientResource(String login, String name, String password, Integer balance) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getBalance() {
        return balance;
    }
}
