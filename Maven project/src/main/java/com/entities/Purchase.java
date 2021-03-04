package com.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private Integer cost;

    @OneToMany
    private Set<Item> items;

    Purchase() {}

    public Purchase(Set<Item> items) {
        date = Calendar.getInstance().getTime();
        this.items = items;
    }

    Purchase(Date date, Set<Item> items) {
        this.date = date;
        for (Item i: items) {
            cost += i.getCost();
        }
    }

}
