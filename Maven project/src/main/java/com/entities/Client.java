package com.entities;

import com.resources.ClientResource;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@SuppressWarnings("UnusedDeclaration")
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, updatable = false)
    private String login;
    private String name;
    private String password;
    private Integer balance;
/*
    @OneToMany
    private Set<Item> cart;
    */
    @OneToMany
    private List<Purchase> purchaseHistory;

    public Client(){ }

    public Client(Long id, String login, String name, String password, Integer balance) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public  Client(ClientResource resource) {
        id = null;
        login = resource.getLogin();
        name = resource.getName();
        password = resource.getPassword();
        balance = resource.getBalance();
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

    public void addPurchase(Purchase purchase) {
        purchaseHistory.add(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchaseHistory;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public ClientResource getResource(){
        return new ClientResource(login, name, password, balance);
    }
}