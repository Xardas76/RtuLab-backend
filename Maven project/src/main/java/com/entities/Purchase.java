package com.entities;

import com.resources.PurchaseResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private Long id;
    private String date;
    private Integer cost;

    @OneToMany
    private List<Item> items;

    Purchase() {}

    public Purchase(List<Item> items) {
        date = Calendar.getInstance().getTime().toString(); //may be changed
        this.items = items;
        this.cost = 0;
        for (Item i: items) {
            cost += i.getCost();
        }
    }

    Purchase(List<Item> items, String date) {
        this.date = date;
        this.items = items;
        this.cost = 0;
        for (Item i: items) {
            cost += i.getCost();
        }
    }

    public PurchaseResource getResource(){
        List<String> itemsStrings = new ArrayList<>();
        for (Item i: items) {
            itemsStrings.add(i.toString());
        }
        return new PurchaseResource(date, cost, itemsStrings);
    }
}
