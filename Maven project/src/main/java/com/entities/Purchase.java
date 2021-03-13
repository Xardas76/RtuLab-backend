package com.entities;

import com.resources.ClientResource;
import com.resources.PurchaseResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private Integer cost;

    @OneToMany
    private List<Item> items;

    Purchase() {}

    public Purchase(List<Item> items) {
        date = Calendar.getInstance().getTime();
        this.items = items;
    }

    Purchase(Date date, List<Item> items) {
        this.date = date;
        for (Item i: items) {
            cost += i.getCost();
        }
    }

    public PurchaseResource getResource(){
        List<String> itemsStrings = new ArrayList<>();
        for (Item i: items) {
            itemsStrings.add(i.toString());
        }
        return new PurchaseResource(id, date.toString(), cost, itemsStrings);
    }
}
