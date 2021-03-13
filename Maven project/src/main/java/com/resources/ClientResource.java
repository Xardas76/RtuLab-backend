package com.resources;

//Class, representing Client, which can always be serialized or deserialized.
public class ClientResource {
    private String login;
    private String name;
    private String password;
    private Integer balance;

    public ClientResource(String login, String name, String password, Integer balance) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public ClientResource(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.balance = 0;
    }

    public ClientResource(String login, String password) {
        this.login = login;
        this.name = login;
        this.password = password;
        this.balance = 0;
    }

    public ClientResource(String login) {
        this.login = login;
        this.name = login;
        this.password = login;
        this.balance = 0;
    }

    public ClientResource() {
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
