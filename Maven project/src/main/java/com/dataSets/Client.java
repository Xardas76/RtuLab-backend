package com.dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@SuppressWarnings("UnusedDeclaration")
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String name;
    private String password;
    private Integer balance;

    public Client() {
    }

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Client(Long id, String login, String name, String password, Integer balance) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {return balance;}

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}